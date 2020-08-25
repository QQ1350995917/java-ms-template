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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.api.vo.CaptchaOutput;
import pwd.initializr.account.api.admin.vo.LoginFailOutput;
import pwd.initializr.account.api.admin.vo.LoginFailOutput.FailType;
import pwd.initializr.account.api.admin.vo.LoginInput;
import pwd.initializr.account.api.admin.vo.LoginOutput;
import pwd.initializr.account.api.vo.SessionInitOutput;
import pwd.initializr.account.api.vo.SessionInitOutput.Status;
import pwd.initializr.account.business.admin.AdminAccountService;
import pwd.initializr.account.business.admin.AdminUserService;
import pwd.initializr.account.business.admin.SessionService;
import pwd.initializr.account.business.admin.bo.AdminAccountBO;
import pwd.initializr.account.business.admin.bo.AdminUserBO;
import pwd.initializr.account.business.bo.AnonymousSessionBO;
import pwd.initializr.account.business.bo.CaptchaBO;
import pwd.initializr.account.business.bo.NamedSessionBO;
import pwd.initializr.account.rpc.RPCToken;
import pwd.initializr.common.web.api.admin.AdminController;

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

  @Override
  public void loginByNameAndPwd(@Valid @NotNull(message = "参数不能为空") LoginInput input) {
    log.info(JSON.toJSONString(input));

    String anonymousToken = getToken();
    if (StringUtils.isBlank(anonymousToken)) {
      // token 不能为空
      outputException(401, new LoginFailOutput(FailType.TokenISNull));
      return;
    }
    if (input == null || StringUtils.isBlank(input.getLoginName()) || StringUtils
        .isBlank(input.getLoginName())) {
      // 输入不能为空
      outputException(401, new LoginFailOutput(FailType.ParamsISNull));
      return;
    }
    AnonymousSessionBO anonymousSessionBO = sessionService.queryAnonymousToken(anonymousToken);
    if (anonymousSessionBO == null) {
      // sessionCookie 过期
      outputException(401, (Object) new LoginFailOutput(FailType.TokenISExpires));
      return;
    }
    if (anonymousSessionBO.getTimes() >= anonymousSessionCaptchaThreshold) {
      // 需要校验验证码
      if (StringUtils.isBlank(input.getCaptcha())) {
        // 识别输入的验证码为空
        outputException(401, new LoginFailOutput(FailType.CaptchaISNull));
        return;
      }
      if (!input.getCaptcha().equals(anonymousSessionBO.getCaptcha())) {
        // 验证码错误
        outputException(401, new LoginFailOutput(FailType.CaptchaISError));
        return;
      }
    }

    AdminAccountBO accountByNameAndPwd = adminAccountService
        .queryByNameAndPwd(input.getLoginName(), input.getLoginPwd());
    if (accountByNameAndPwd == null) {
      // 登录失败，更新错误登录次数
      anonymousSessionBO.setTimes(anonymousSessionBO.getTimes() + 1);
      sessionService.updateAnonymousSession(anonymousToken, anonymousSessionBO);
      if (anonymousSessionBO.getTimes() >= anonymousSessionCaptchaThreshold) {
        outputException(401, new LoginFailOutput(FailType.CaptchaISNull));
      } else {
        outputException(401, new LoginFailOutput(FailType.ParamsISError));
      }
      return;
    }

    // 生成session信息
    AdminUserBO adminUserBO = adminUserService.queryById(accountByNameAndPwd.getUid());
    if (adminUserBO == null) {
      outputException(500);
      return;
    }

    NamedSessionBO namedSessionBO = new NamedSessionBO(adminUserBO.getId(), adminUserBO.getName(),
        accountByNameAndPwd.getId(), accountByNameAndPwd.getLoginName(),
        System.currentTimeMillis());
    String namedToken = RPCToken.generateToken(namedSessionBO, namedSessionSecret);
    sessionService.createNamedSession(namedToken, namedSessionBO);
    sessionService.deleteAnonymousToken(namedToken);
    outputData(new LoginOutput(namedSessionBO.getUid(), namedToken));
  }


  @Override
  public void loginCaptchaRefresh() {
    String token = getToken();
    if (StringUtils.isBlank(token)) {
      // 参数不合规
      outputException(401);
      return;
    }
    AnonymousSessionBO anonymousSessionBO = sessionService.queryAnonymousToken(token);
    if (anonymousSessionBO == null) {
      // token 过期
      outputException(401);
      return;
    }
    if (anonymousSessionBO.getTimes() < anonymousSessionCaptchaThreshold) {
      // 无需验证码
      outputException(401);
      return;
    }
    CaptchaBO captchaBO = sessionService.createCaptcha(token);
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
    Boolean captchaRequired = false;
    AnonymousSessionBO anonymousSessionBO = null;
    if (StringUtils.isNotBlank(token)) {
      // 该请求携带 token，认为二次登陆，检验匿名token是否存在
      anonymousSessionBO = sessionService.queryAnonymousToken(token);
      if (anonymousSessionBO != null) {
        // 匿名 token 存在，就延长其在redis的有效期，然后返回
        sessionService.updateAnonymousSession(token,anonymousSessionBO);
        sessionService.createCaptcha(token);
      } else {
        // 匿名 token 不存在，检验提交的 token 是否是具名token
        NamedSessionBO namedSessionBO = sessionService.queryNamedSession(getUid());
        if (namedSessionBO != null) {
          // 当前提交的 token 是具名 token 表示该 token 已经登录，无需再次登录
          SessionInitOutput loginCookieOutput = new SessionInitOutput();
          loginCookieOutput.setStatus(Status.NAMED.getNumber());
          outputData(loginCookieOutput);
          return;
        } else {
          // 当前提交的 token 非有效的匿名 token 也非有效的具名 token ,当做提交的 token 为空处理
        }
      }
    }

    if (anonymousSessionBO == null) {
      // 当前提交的 token 非有效的匿名 token 也非有效的具名 token ,当做提交的 token 为空处理
      // 该请求没有携带 token，认为初次登陆，生成匿名token
      token = sessionService.createAnonymousSession();
      if (token == null) {
        // 生成匿名token失败
        outputException(500);
        return;
      }
      anonymousSessionBO = new AnonymousSessionBO(0, null);
    }

    // 是否对该匿名 token 产生验证码
    if (anonymousSessionBO.getTimes() >= anonymousSessionCaptchaThreshold) {
      captchaRequired = true;
    }
    // 生成新的 token 成，并设置是否需要图形验证码
    SessionInitOutput loginInitOutput = new SessionInitOutput();
    loginInitOutput.setStatus(Status.ANONYMOUS.getNumber());
    loginInitOutput.setToken(token);
    loginInitOutput.setExpires(anonymousSessionExpiresSeconds);
    loginInitOutput.setCaptchaRequired(captchaRequired);
    // TODO 登录方式列表
    outputData(loginInitOutput);
  }

  @Override
  public void logout() {
    if (sessionService.deleteNamedSession(getUid())) {
      outputData(200);
    } else {
      outputException(500);
    }
  }

  @Override
  public void querySessionInfo() {
    NamedSessionBO session = sessionService.queryNamedSession(getUid());
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
