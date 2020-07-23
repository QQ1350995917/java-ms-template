package pwd.initializr.account.api.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.api.admin.vo.LoginInput;
import pwd.initializr.account.api.admin.vo.SessionCaptchaOutput;
import pwd.initializr.account.api.admin.vo.SessionTokenOutput;
import pwd.initializr.account.business.admin.AdminService;
import pwd.initializr.account.business.admin.SessionService;
import pwd.initializr.account.business.admin.bo.AdminAccountBO;
import pwd.initializr.account.business.admin.bo.SessionCaptchaBO;
import pwd.initializr.account.business.admin.bo.SessionCookieBO;
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
    description = "[管理员登录，登录信息，管理员退出]"
)
@RestController(value = "adminSessionApi")
@RequestMapping(value = "/api/admin/session")
public class SessionController extends AdminController implements SessionApi {

  @Value("${account_secret}")
  private String ACCOUNT_SECRET;

  @Value("${account.admin.cookie.expires.seconds}")
  private Integer cookieExpiresSeconds;

  @Value("${account.admin.cookie.captcha.threshold}")
  private Integer cookieCaptchaThreshold;


  @Autowired
  private AdminService adminService;

  @Autowired
  private SessionService sessionService;


//  @ApiOperation(value = "登录")
//  @PutMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public void login() {
//    AdminUserBO loginAdminUserBO = adminService
//        .queryByLoginNameAndLoginPassword(input.getLoginName(), input.getLoginPwd());
//    if (loginAdminUserBO == null) {
//      super.outputData(401);
//    } else {
//      SessionBO sessionBO = new SessionBO(loginAdminUserBO.getId(), loginAdminUserBO.getLoginName(),
//          loginAdminUserBO.getLoginName());
//      String cookie = RPCToken.generateToken(sessionBO, ACCOUNT_SECRET);
//      if (sessionService.getSession(loginAdminUserBO.getId()) == null) {
//        sessionService.replaceSession(sessionBO);
//      }
//      super.outputData(new LoginOutput(loginAdminUserBO.getId(), cookie));
//    }
  }

  @ApiOperation(value = "登录页面初始化")
  @GetMapping(value = {"/init"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void loginInitializr() {
    String cookie = getToken();
    Boolean captchaRequired = false;
    SessionCookieBO sessionCookieBO = null;
    // 初次访问没有携带cookie，需要生成新的cookie
    if (cookie == null) {
      sessionCookieBO = sessionService.createCookie();
    } else {
      sessionCookieBO = sessionService.queryCookie(new SessionCookieBO(cookie));
    }
    if (sessionCookieBO == null) {
        // cookie 比较旧，得更新
        outputException(401);
        return;
    }
    cookie = sessionCookieBO.getCookie();
    if (cookie == null) {
      // 生成新的cookie失败
      outputException(500);
      return;
    }
    if (sessionCookieBO.getTimes() >= cookieCaptchaThreshold) {
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

  @ApiOperation(value = "登录")
  @PutMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void loginByNameAndPwd(LoginInput input) {
    String cookie = getToken();
    if (StringUtils.isBlank(cookie)) {
      outputException(401);
      return;
    }
    if (input == null || StringUtils.isBlank(input.getLoginName()) || StringUtils.isBlank(input.getLoginName())) {
      outputException(401);
      return;
    }
    SessionCookieBO sessionCookieBO = sessionService.queryCookie(new SessionCookieBO(cookie));
    if (sessionCookieBO == null) {
      outputException(401);
      return;
    }
    AdminAccountBO sessionByNameAndPwd = sessionService
        .createSessionByNameAndPwd(input.getLoginName(), input.getLoginPwd());
    if (sessionByNameAndPwd == null) { sessionCookieBO = sessionService.updateCookie(new SessionCookieBO(cookie));
      if (sessionCookieBO.getTimes() >= cookieCaptchaThreshold){
        // TODO 如何响应是否需要验证码
      }
      outputException(401);
      return;
    }
    sessionService.deleteCookie(new SessionCookieBO(cookie));
  }

  @Override
  public void loginCaptchaRefresh() {
    String cookie = getToken();
    if (cookie != null
        && sessionService.queryCookie(new SessionCookieBO(cookie)) != null) {
      SessionCaptchaBO sessionCaptchaBO = sessionService.createCaptcha(new SessionCookieBO(cookie));
      if (sessionCaptchaBO == null) {
        outputException(500);
      } else {
        SessionCaptchaOutput sessionCaptchaOutput = new SessionCaptchaOutput();
        BeanUtils.copyProperties(sessionCaptchaBO,sessionCaptchaOutput);
        outputData(sessionCaptchaOutput);
      }
    } else {
      outputException(401);
    }
  }

  @ApiOperation(value = "信息查询")
  @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void querySessionInfo() {
//    SessionBO session = sessionService.getSession(getUid());
//    if (session == null) {
//      super.outputException(401);
//    } else {
//      JSONObject content = new JSONObject();
//      content.put("roles", new String[]{"admin"});
//      content.put("introduction", "I am a super administrator");
//      content.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
//      content.put("name", "Super Admin");
//      super.outputData(content);
//    }
  }

  @ApiOperation(value = "退出")
  @DeleteMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void logout() {
    sessionService.deleteSession(getUid());
  }
}
