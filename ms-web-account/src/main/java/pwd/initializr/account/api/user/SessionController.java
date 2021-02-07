package pwd.initializr.account.api.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.api.vo.SessionInitFailOutput;
import pwd.initializr.account.api.vo.CaptchaOutput;
import pwd.initializr.account.api.vo.SessionInitOutput;
import pwd.initializr.account.api.user.vo.LoginInput;
import pwd.initializr.account.business.session.SessionService;
import pwd.initializr.account.business.session.bo.SessionBO;
import pwd.initializr.account.business.session.bo.CaptchaBO;
import pwd.initializr.account.business.session.bo.SessionType;
import pwd.initializr.account.business.user.UserAccountService;
import pwd.initializr.account.business.user.UserKeyService;
import pwd.initializr.account.business.user.UserUserService;
import pwd.initializr.account.business.user.bo.UserAccountBO;
import pwd.initializr.account.business.user.bo.UserUserBO;
import pwd.initializr.account.rpc.RPCToken;
import pwd.initializr.common.utils.CryptographerPbkdf;
import pwd.initializr.common.utils.CryptographerRsa;
import pwd.initializr.common.web.api.user.UserController;
import pwd.initializr.common.web.api.vo.Meta;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.common.web.persistence.entity.EntityDel;

/**
 * pwd.initializr.account.api.user@ms-web-initializr
 *
 * <h1>控制层接口：session信息</h1>
 *
 * date 2019-11-02 21:59
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "系统会话管理",
    value = "sessionApi",
    description = "[用户登录，登录信息，用户退出]"
)
@RestController(value = "sessionApi")
@RequestMapping(value = "/api/session")
@Slf4j
public class SessionController extends UserController implements SessionApi {

  @Value("${account.admin.session.anonymous.expires.seconds}")
  private Integer anonymousSessionExpiresSeconds;

  @Value("${account.admin.session.anonymous.captcha.threshold}")
  private Integer anonymousSessionCaptchaThreshold;

  @Value("${account.admin.session.named.secret}")
  private String namedSessionSecret;

  @Resource
  private SessionService sessionService;

  @Resource
  private UserAccountService userAccountService;

  @Resource
  private UserUserService userUserService;

  @Resource
  private UserKeyService userKeyService;

  @Override
  public void loginByNameAndPwd(@Valid @NotNull(message = "参数不能为空") LoginInput input) {
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
      loginPwd = CryptographerRsa.decryptByRsa(loginPwd,userKeyService.getPrivateKey());
    } catch (Exception e) {
      log.error(e.getMessage());
      SessionInitFailOutput sessionInitFailOutput = refreshSessionBOWhenLoginError(sessionBO);
      outputException(400, sessionInitFailOutput);
      return;
    }

    // 由于登录名唯一，密码加密长度较大，故密码不参与查询
    UserAccountBO userAccountBO = userAccountService.queryByName(input.getLoginName());
    if (userAccountBO == null) {
      SessionInitFailOutput sessionInitFailOutput = refreshSessionBOWhenLoginError(sessionBO);
      outputException(400, sessionInitFailOutput);
      return;
    }

    // 由于数据库密码不可逆，故进行正向验证
    String loginPwdCryptographer = userAccountBO.getLoginPwd();
    String pwdSalt = userAccountBO.getPwdSalt();
    boolean authenticate = false;
    try {
      authenticate = CryptographerPbkdf
          .authenticate(loginPwd, loginPwdCryptographer, pwdSalt);
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    if (!authenticate) {
      SessionInitFailOutput sessionInitFailOutput = refreshSessionBOWhenLoginError(sessionBO);
      outputException(407, sessionInitFailOutput);
      return;
    }

    // 登录成功
    if (userAccountBO.getAble() == EntityAble.DISABLE.getNumber()) {
      outputData(new Meta(403,"该账号已禁用，请联系管理员开启"));
      return;
    }
    if (userAccountBO.getDel() == EntityDel.YES.getNumber()) {
      outputData(new Meta(410,"该账号已删除"));
      return;
    }
    // 查询user信息
    UserUserBO adminUserBO = userUserService.queryById(userAccountBO.getUid());
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
    namedSessionBO.setAid(userAccountBO.getId());
    namedSessionBO.setAName(userAccountBO.getLoginName());
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
      return new SessionInitFailOutput(true,"用户名或密码错误");
    } else {
      return new SessionInitFailOutput(false,"用户名或密码错误");
    }
  }


  @Override
  public void loginCaptchaRefresh(
      @RequestHeader(value = "x-uid", required = false) Long uid,
      @RequestHeader(value = "x-aid", required = false) Long aid,
      @RequestHeader(value = "x-token", required = false) String token
  ) {
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
  public void loginInitializr(Long aid, Long uid, String token) {
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

    // 未过期的具名session
    outputException(304);
  }

  @Override
  public void logout() {
    if (sessionService.deleteSession(getUid())) {
      outputData(200);
    } else {
      outputException(500);
    }
  }

  @Override
  public void querySessionInfo() {
    SessionBO session = sessionService.querySession(getUid());
    if (session == null) {
      super.outputException(401);
      return;
    } else {
      JSONObject content = new JSONObject();
      content.put("roles", new String[]{"admin"});
      content.put("introduction", "I am a super administrator");
      content.put("avatar",
          "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
      content.put("name", "Super Admin");
      super.outputData(content);
    }
  }
}
