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
import pwd.initializr.account.api.admin.vo.LoginFailOutput;
import pwd.initializr.account.api.admin.vo.LoginFailOutput.FailType;
import pwd.initializr.account.api.admin.vo.LoginInput;
import pwd.initializr.account.api.admin.vo.LoginOutput;
import pwd.initializr.account.api.admin.vo.SessionCaptchaOutput;
import pwd.initializr.account.api.admin.vo.SessionTokenOutput;
import pwd.initializr.account.business.admin.AdminAccountService;
import pwd.initializr.account.business.admin.AdminUserService;
import pwd.initializr.account.business.admin.SessionService;
import pwd.initializr.account.business.admin.bo.AdminAccountBO;
import pwd.initializr.account.business.admin.bo.AdminUserBO;
import pwd.initializr.account.business.common.bo.SessionBO;
import pwd.initializr.account.business.common.bo.SessionCaptchaBO;
import pwd.initializr.account.business.common.bo.SessionTokenBO;
import pwd.initializr.account.rpc.RPCToken;
import pwd.initializr.common.web.api.admin.AdminController;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>控制层逻辑：管理员session生命周期接口</h1>
 * <p>fixme: 逻辑漏洞，无法拦截机器登录情况，执行方式为：刷新页面（生成新的token）-> 执行登录（新的token无需图形验证码）</p>
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


  @Value("${account.admin.cookie.expires.seconds}")
  private Integer cookieExpiresSeconds;

  @Value("${account.admin.cookie.captcha.threshold}")
  private Integer cookieCaptchaThreshold;

  @Value("${account.admin.session.secret}")
  private String sessionSecret;

  @Autowired
  private SessionService sessionService;

  @Autowired
  private AdminAccountService adminAccountService;

  @Autowired
  private AdminUserService adminUserService;

  @Override
  public void loginByNameAndPwd(@Valid @NotNull(message = "参数不能为空") LoginInput input) {
    log.info(JSON.toJSONString(input));

    String cookie = getToken();
    if (StringUtils.isBlank(cookie)) {
      // cookie 不能为空
      outputException(401, new LoginFailOutput(FailType.CookieISNull));
      return;
    }
    if (input == null || StringUtils.isBlank(input.getLoginName()) || StringUtils
        .isBlank(input.getLoginName())) {
      // 输入不能为空
      outputException(401, new LoginFailOutput(FailType.ParamsISNull));
      return;
    }
    SessionTokenBO sessionTokenBO = sessionService.queryCookie(cookie);
    if (sessionTokenBO == null) {
      // sessionCookie 过期
      outputException(401, (Object) new LoginFailOutput(FailType.CookieISExpires));
      return;
    }
    if (sessionTokenBO.getTimes() >= cookieCaptchaThreshold) {
      // 需要校验验证码
      if (StringUtils.isBlank(input.getCaptcha())) {
        // 识别输入的验证码为空
        outputException(401, new LoginFailOutput(FailType.CaptchaISNull));
        return;
      }
      if (!input.getCaptcha().equals(sessionTokenBO.getCaptcha())) {
        // 验证码错误
        outputException(401, new LoginFailOutput(FailType.CaptchaISError));
        return;
      }
    }

    AdminAccountBO accountByNameAndPwd = adminAccountService
        .queryByNameAndPwd(input.getLoginName(), input.getLoginPwd());
    if (accountByNameAndPwd == null) {
      // 登录失败，更新错误登录次数
      sessionTokenBO.setTimes(sessionTokenBO.getTimes() + 1);
      sessionService.updateCookie(cookie, sessionTokenBO);
      if (sessionTokenBO.getTimes() >= cookieCaptchaThreshold) {
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

    SessionBO sessionBO = new SessionBO(adminUserBO.getId(), adminUserBO.getName(),
        accountByNameAndPwd.getId(), accountByNameAndPwd.getLoginName(),
        System.currentTimeMillis());
    String token = RPCToken.generateToken(sessionBO, sessionSecret);
    sessionService.createSession(token, sessionBO);
    sessionService.deleteCookie(cookie);
    outputData(new LoginOutput(sessionBO.getUid(), token));
  }


  @Override
  public void loginCaptchaRefresh() {
    String cookie = getToken();
    if (StringUtils.isBlank(cookie)) {
      // 参数不合规
      outputException(401);
      return;
    }
    SessionTokenBO sessionTokenBO = sessionService.queryCookie(cookie);
    if (sessionTokenBO == null) {
      // cookie 过期
      outputException(401);
      return;
    }
    if (sessionTokenBO.getTimes() < cookieCaptchaThreshold) {
      // 无需验证码
      outputException(401);
      return;
    }
    SessionCaptchaBO sessionCaptchaBO = sessionService.createCaptcha(cookie);
    if (sessionCaptchaBO == null) {
      outputException(500);
      return;
    }
    SessionCaptchaOutput sessionCaptchaOutput = new SessionCaptchaOutput();
    BeanUtils.copyProperties(sessionCaptchaBO, sessionCaptchaOutput);
    outputData(sessionCaptchaOutput);
  }

  @Override
  public void loginInitializr(String aid, String uid, String token) {
    String cookie = getToken();
    Boolean captchaRequired = false;
    SessionTokenBO sessionTokenBO = null;
    // 初次访问没有携带token，需要生成新的匿名token
    if (StringUtils.isBlank(cookie)) {
      cookie = sessionService.createCookie();
      if (cookie == null) {
        // 生成匿名token失败
        outputException(500);
        return;
      }
      sessionTokenBO = new SessionTokenBO(0, null);
    } else {
      sessionTokenBO = sessionService.queryCookie(cookie);
      if (sessionTokenBO == null) {
        // cookie 比较旧，得更新
        outputException(401, new LoginFailOutput(FailType.CookieISExpires));
        return;
      }
    }
    if (sessionTokenBO.getTimes() >= cookieCaptchaThreshold) {
      captchaRequired = true;
    }
    // 生成新的cookie成，并设置是否需要图形验证码
    SessionTokenOutput loginCookieOutput = new SessionTokenOutput();
    loginCookieOutput.setCookie(cookie);
    loginCookieOutput.setExpires(cookieExpiresSeconds);
    loginCookieOutput.setCaptchaRequired(captchaRequired);
    // TODO 登录方式列表
    outputData(loginCookieOutput);
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
