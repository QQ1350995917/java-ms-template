package pwd.initializr.account.api.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.api.vo.CaptchaOutput;
import pwd.initializr.account.api.vo.SessionInitFailOutput;
import pwd.initializr.account.api.vo.SessionInitInput;
import pwd.initializr.account.api.vo.SessionInitOutput;
import pwd.initializr.account.business.admin.AdminAccountService;
import pwd.initializr.account.business.admin.AdminKeyService;
import pwd.initializr.account.business.admin.AdminUserService;
import pwd.initializr.account.business.session.SessionService;
import pwd.initializr.account.business.admin.bo.AdminAccountBO;
import pwd.initializr.account.business.admin.bo.AdminUserBO;
import pwd.initializr.account.business.session.bo.CaptchaBO;
import pwd.initializr.account.business.session.bo.SessionBO;
import pwd.initializr.account.business.session.bo.SessionType;
import pwd.initializr.account.rpc.RPCToken;
import pwd.initializr.common.utils.CryptographerPbkdf;
import pwd.initializr.common.utils.CryptographerRsa;
import pwd.initializr.common.web.api.admin.AdminController;
import pwd.initializr.common.web.api.vo.Meta;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.common.web.persistence.entity.EntityDel;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>控制层逻辑：管理员session生命周期接口</h1>
 * <p>fixme: 逻辑漏洞，无法拦截机器登录情况，执行方式为：无 token 刷新页面（生成新的token）</p>
 * date 2019-10-25 20:18
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "会话管理",
    value = "adminSessionManageApi",
    description = "[登录，信息查询，退出]"
)
@RestController(value = "adminSessionApi")
@RequestMapping(value = "/api/admin/session")
@Slf4j
public class SessionController extends AdminController implements SessionApi {


  @Value("${account.admin.session.anonymous.expires.seconds}")
  private Integer anonymousSessionExpiresSeconds;

  @Value("${account.admin.session.anonymous.captcha.threshold}")
  private Integer anonymousSessionCaptchaThreshold;

  @Value("${account.admin.session.named.secret}")
  private String namedSessionSecret;

  @Autowired
  private SessionService sessionService;

  @Autowired
  private AdminAccountService adminAccountService;

  @Autowired
  private AdminUserService adminUserService;

  @Autowired
  private AdminKeyService adminKeyService;

  @Override
  public void loginByNameAndPwd(@Valid @NotNull(message = "参数不能为空") SessionInitInput input) {
    log.info(JSON.toJSONString(input));
    SessionBO sessionBO = sessionService.querySession(getUid());
    if (sessionBO == null) {
      // fixme: 在已经登录的但已过期的session中，请求这个接口，原则上应该相应登录错误和登录正确，此处只能先刷新登录页面即，请求的登录初始化接口
      // session 过期
      outputException(408, "会话已经过期");
      return;
    }

    if (SessionType.NAMED.getType().equals(sessionBO.getType())) {
      // TODO: session延期
      // 具名的生效中的session
      outputException(304);
      return;
    }

    // 匿名session
    if (sessionBO.getTimes() >= anonymousSessionCaptchaThreshold) {
      // 需要校验验证码
      if (StringUtils.isBlank(input.getCaptcha())) {
        // 识别输入的验证码为空
        outputException(407, "验证码为空");
        return;
      }
      if (!input.getCaptcha().equals(sessionBO.getCaptcha())) {
        // 验证码错误
        outputException(407, "验证码错误");
        return;
      }
    }

    // RSA 解密密码，解密的密码不能记录日志
    String loginPwd = input.getLoginPwd();
    try {
      loginPwd = CryptographerRsa.decryptByRsa(loginPwd,adminKeyService.getPrivateKey());
    } catch (Exception e) {
      log.error(e.getMessage());
      SessionInitFailOutput sessionInitFailOutput = refreshSessionBOWhenLoginError(sessionBO);
      outputException(400, "用户名或密码错误", sessionInitFailOutput);
      return;
    }

    // 由于登录名唯一，密码加密长度较大，故密码不参与查询
    AdminAccountBO adminAccountBO = adminAccountService.queryByName(input.getLoginName());
    if (adminAccountBO == null) {
      SessionInitFailOutput sessionInitFailOutput = refreshSessionBOWhenLoginError(sessionBO);
      outputException(400, "用户名或密码错误", sessionInitFailOutput);
      return;
    }

    // 由于数据库密码不可逆，故进行正向验证
    String loginPwdCryptographer = adminAccountBO.getLoginPwd();
    String pwdSalt = adminAccountBO.getPwdSalt();
    boolean authenticate = false;
    try {
      authenticate = CryptographerPbkdf
          .authenticate(loginPwd, loginPwdCryptographer, pwdSalt);
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    if (!authenticate) {
      SessionInitFailOutput sessionInitFailOutput = refreshSessionBOWhenLoginError(sessionBO);
      outputException(400, "用户名或密码错误", sessionInitFailOutput);
      return;
    }

    // 登录成功
    if (adminAccountBO.getAble() == EntityAble.DISABLE.getNumber()) {
      outputData(new Meta(403,"该账号已禁用，请联系管理员开启"));
      return;
    }
    if (adminAccountBO.getDel() == EntityDel.YES.getNumber()) {
      outputData(new Meta(410,"该账号已删除"));
      return;
    }
    // 查询user信息
    AdminUserBO adminUserBO = adminUserService.queryById(adminAccountBO.getUid());
    if (adminUserBO == null) {
      outputException(500);
      return;
    }
    if (adminUserBO.getAble() == EntityAble.DISABLE.getNumber()) {
      outputData(new Meta(403,"该用户已禁用，请联系管理员开启"));
      return;
    }
    if (adminUserBO.getDel() == EntityDel.YES.getNumber()) {
      outputData(new Meta(410,"该用户已删除"));
      return;
    }

    // 删除对应的匿名session
    sessionService.deleteSession(getUid());
    // 构建具名session
    SessionBO namedSessionBO = new SessionBO(SessionType.NAMED.getType(), 0,null);
    namedSessionBO.setUid(adminUserBO.getId());
    namedSessionBO.setUName(adminUserBO.getName());
    namedSessionBO.setAid(adminAccountBO.getId());
    namedSessionBO.setAName(adminAccountBO.getLoginName());
    namedSessionBO.setTimestamp(System.currentTimeMillis());
    // 构建具名sessionToken
    String namedToken = RPCToken.generateToken(namedSessionBO, namedSessionSecret);
    namedSessionBO.setToken(namedToken);
    // 创建对应的具名session
    sessionService.createNamedSession(namedSessionBO);

    SessionInitOutput sessionInitOutput = new SessionInitOutput();
    BeanUtils.copyProperties(namedSessionBO,sessionInitOutput);

    outputData(sessionInitOutput);
  }

  private SessionInitFailOutput refreshSessionBOWhenLoginError(SessionBO sessionBO){
    sessionBO.setTimes(sessionBO.getTimes() + 1);
    sessionService.updateAnonymousSession(sessionBO);
    if (sessionBO.getTimes() >= anonymousSessionCaptchaThreshold) {
      sessionService.createLoginCaptcha(sessionBO.getUid());
      return new SessionInitFailOutput(true,"");
    } else {
      return new SessionInitFailOutput(false,"");
    }
  }


  @Override
  public void loginCaptchaRefresh(
      @RequestHeader(value = "x-uid", required = false) Long uid,
      @RequestHeader(value = "x-aid", required = false) Long aid,
      @RequestHeader(value = "x-token", required = false) String token) {
    if (StringUtils.isBlank(token)) {
      outputException(417);
      return;
    }
    SessionBO sessionBO = sessionService.querySession(uid);
    if (sessionBO == null) {
      // token 过期
      outputException(417);
      return;
    }
    if (sessionBO.getTimes() < anonymousSessionCaptchaThreshold) {
      // 无需验证码
      outputException(417);
      return;
    }
    CaptchaBO captchaBO = sessionService.createLoginCaptcha(uid);
    if (captchaBO == null) {
      outputException(500);
      return;
    }

    CaptchaOutput sessionCaptchaOutput = new CaptchaOutput();
    BeanUtils.copyProperties(captchaBO, sessionCaptchaOutput);
    outputData(sessionCaptchaOutput);
  }

  @Override
  public void loginInitializr(Long uid, String token) {
    SessionBO sessionBO = null;
    if (uid != null) {
      // 可能是匿名session，也可能是在已经登录状态下访问该接口，提交了具名session
      sessionBO = sessionService.querySession(uid);
    }

    if (sessionBO == null) {
      // uid为空或者session已经过期，则创建匿名session
      SessionBO anonymousSession = sessionService.createAnonymousSession();
      SessionInitOutput sessionInitOutput = new SessionInitOutput();
      BeanUtils.copyProperties(anonymousSession,sessionInitOutput);
      outputException(410, sessionInitOutput);
      return;
    }

    if (SessionType.ANONYMOUS.getType().equals(sessionBO.getType())) {
      // 匿名session
      boolean captchaRequired = false;
      // 是否对该匿名 token 产生验证码
      if (sessionBO.getTimes() >= anonymousSessionCaptchaThreshold) {
        captchaRequired = true;
        sessionService.createLoginCaptcha(sessionBO.getUid());
      }
      SessionInitOutput sessionInitOutput = new SessionInitOutput();
      BeanUtils.copyProperties(sessionBO, sessionInitOutput);
      sessionInitOutput.setExpires(anonymousSessionExpiresSeconds);
      sessionInitOutput.setCaptchaRequired(captchaRequired);
      // TODO 登录方式列表
      outputData(sessionInitOutput);
      return;
    }

    if (!sessionBO.getToken().equals(token)) {
      outputException(416);
      return;
    }

    // 未过期的具名session
    outputException(304);
  }

  @Override
  public void logout() {
    // 会话过期校验，uid和token的匹配有网关控制
    if (sessionService.deleteSession(getUid())) {
      outputData(200);
    } else {
      outputException(500);
    }
  }

  @Override
  public void querySessionInfo() {
    // TODO 基本信息
    // TODO 权限信息
    // 会话过期校验，uid和token的匹配有网关控制
    JSONObject content = new JSONObject();
    content.put("roles", new String[]{"admin"});
    content.put("introduction", "I am a super administrator");
    content.put("avatar",
        "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    content.put("name", "Super Admin");
    super.outputData(content);
  }
}
