package pwd.initializr.account.api.user;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.api.vo.SessionCreateFailOutput;
import pwd.initializr.account.api.vo.SessionCreateFailOutput.FailType;
import pwd.initializr.account.api.vo.SessionCreateOkOutput;
import pwd.initializr.account.api.vo.CaptchaOutput;
import pwd.initializr.account.api.vo.SessionInitOutput;
import pwd.initializr.account.api.user.vo.LoginInput;
import pwd.initializr.account.business.session.SessionService;
import pwd.initializr.account.business.session.bo.SessionBOAnonymous;
import pwd.initializr.account.business.session.bo.CaptchaBO;
import pwd.initializr.account.business.session.bo.SessionBONamed;
import pwd.initializr.account.business.user.UserAccountService;
import pwd.initializr.account.business.user.UserUserService;
import pwd.initializr.account.business.user.bo.UserAccountBO;
import pwd.initializr.account.business.user.bo.UserUserBO;
import pwd.initializr.account.rpc.RPCToken;
import pwd.initializr.common.web.api.user.UserController;

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
public class SessionController extends UserController implements SessionApi {

  @Value("${account.admin.session.anonymous.expires.seconds}")
  private Integer anonymousSessionExpiresSeconds;

  @Value("${account.admin.session.anonymous.captcha.threshold}")
  private Integer anonymousSessionCaptchaThreshold;

  @Value("${account.admin.session.named.secret}")
  private String namedSessionSecret;

  @Autowired
  private SessionService sessionService;

  @Autowired
  private UserAccountService userAccountService;

  @Autowired
  private UserUserService userUserService;

  @Override
  public void loginByNameAndPwd(@Valid @NotNull(message = "参数不能为空") LoginInput input) {
    String anonymousToken = getToken();
    if (StringUtils.isBlank(anonymousToken)) {
      // token 不能为空
      outputException(401, new SessionCreateFailOutput(FailType.TokenISNull));
      return;
    }
    if (input == null || StringUtils.isBlank(input.getLoginName()) || StringUtils
        .isBlank(input.getLoginName())) {
      // 输入不能为空
      outputException(401, new SessionCreateFailOutput(FailType.ParamsISNull));
      return;
    }
    SessionBOAnonymous sessionBOAnonymous = sessionService.querySessionAnonymous(anonymousToken);
    if (sessionBOAnonymous == null) {
      // sessionCookie 过期
      outputException(401, (Object) new SessionCreateFailOutput(FailType.TokenISExpires));
      return;
    }
    if (sessionBOAnonymous.getTimes() >= anonymousSessionCaptchaThreshold) {
      // 需要校验验证码
      if (StringUtils.isBlank(input.getCaptcha())) {
        // 识别输入的验证码为空
        outputException(401, new SessionCreateFailOutput(FailType.CaptchaISNull));
        return;
      }
      if (!input.getCaptcha().equals(sessionBOAnonymous.getCaptcha())) {
        // 验证码错误
        outputException(401, new SessionCreateFailOutput(FailType.CaptchaISError));
        return;
      }
    }

    UserAccountBO accountByNameAndPwd = userAccountService
        .queryByNameAndPwd(input.getLoginName(), input.getLoginPwd());
    if (accountByNameAndPwd == null) {
      // 登录失败，更新错误登录次数
      sessionBOAnonymous.setTimes(sessionBOAnonymous.getTimes() + 1);
      sessionService.updateAnonymousSession(anonymousToken, sessionBOAnonymous);
      if (sessionBOAnonymous.getTimes() >= anonymousSessionCaptchaThreshold) {
        outputException(401, new SessionCreateFailOutput(FailType.CaptchaISNull));
      } else {
        outputException(401, new SessionCreateFailOutput(FailType.ParamsISError));
      }
      return;
    }

    // 生成session信息
    UserUserBO userUserBO = userUserService.queryById(accountByNameAndPwd.getUid());
    if (userUserBO == null) {
      outputException(500);
      return;
    }

    SessionBONamed sessionBONamed = new SessionBONamed(userUserBO.getId(), userUserBO.getName(),
        accountByNameAndPwd.getId(), accountByNameAndPwd.getLoginName(),
        System.currentTimeMillis());
    String token = RPCToken.generateToken(sessionBONamed, namedSessionSecret);
    sessionService.createNamedSession(token, sessionBONamed);
    sessionService.deleteAnonymousToken(anonymousToken);
    outputData(new SessionCreateOkOutput(sessionBONamed.getUid(), sessionBONamed.getAccountId(), token));
  }


  @Override
  public void loginCaptchaRefresh() {
    String anonymousToken = getToken();
    if (StringUtils.isBlank(anonymousToken)) {
      // 参数不合规
      outputException(401);
      return;
    }
    SessionBOAnonymous sessionBOAnonymous = sessionService.querySessionAnonymous(anonymousToken);
    if (sessionBOAnonymous == null) {
      // token 过期
      outputException(401);
      return;
    }
    if (sessionBOAnonymous.getTimes() < anonymousSessionCaptchaThreshold) {
      // 无需验证码
      outputException(401);
      return;
    }
    CaptchaBO captchaBO = sessionService.createCaptcha(anonymousToken);
    if (captchaBO == null) {
      outputException(500);
      return;
    }
    CaptchaOutput sessionCaptchaOutput = new CaptchaOutput();
    BeanUtils.copyProperties(captchaBO, sessionCaptchaOutput);
    outputData(sessionCaptchaOutput);
  }

  @Override
  public void loginInitializr(String token) {
    String anonymousToken = getToken();
    Boolean captchaRequired = false;
    SessionBOAnonymous sessionBOAnonymous = null;
    // 初次访问没有携带 token，需要生成新的 token
    if (StringUtils.isBlank(anonymousToken)) {
      anonymousToken = sessionService.createAnonymousSession();
      if (anonymousToken == null) {
        // 生成新的token失败
        outputException(500);
        return;
      }
      sessionBOAnonymous = new SessionBOAnonymous(0, null);
    } else {
      sessionBOAnonymous = sessionService.querySessionAnonymous(anonymousToken);
      if (sessionBOAnonymous == null) {
        // token 比较旧，得更新
        outputException(401, new SessionCreateFailOutput(FailType.TokenISExpires));
        return;
      }
    }
    if (sessionBOAnonymous.getTimes() >= anonymousSessionCaptchaThreshold) {
      captchaRequired = true;
    }
    // 生成新的 token，并设置是否需要图形验证码
    SessionInitOutput loginCookieOutput = new SessionInitOutput();
    loginCookieOutput.setToken(anonymousToken);
    loginCookieOutput.setExpires(anonymousSessionExpiresSeconds);
    loginCookieOutput.setCaptchaRequired(captchaRequired);
    // TODO 登录方式列表
    outputData(loginCookieOutput);
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
    SessionBONamed session = sessionService.querySessionNamed(getUid());
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
