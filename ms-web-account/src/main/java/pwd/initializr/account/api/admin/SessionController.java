package pwd.initializr.account.api.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.api.admin.vo.LoginInput;
import pwd.initializr.account.api.admin.vo.SessionCookieOutput;
import pwd.initializr.account.business.admin.AdminService;
import pwd.initializr.account.business.admin.SessionService;
import pwd.initializr.account.business.admin.bo.SessionCookieBO;
import pwd.initializr.common.web.api.admin.AdminController;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>控制层逻辑：管理员session生命周期接口</h1>
 * <p>fixme: 逻辑漏洞，无法拦截机器登录情况，执行方式为：刷新页面（生成新的cookie）-> 执行登录（新的cookie无需图形验证码）</p>
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


  @ApiOperation(value = "登录")
  @PutMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public void login() {
//    AdminUserBO loginAdminUserBO = adminService
//        .queryByLoginNameAndLoginPassword(input.getLoginName(), input.getLoginPwd());
//    if (loginAdminUserBO == null) {
//      super.outputData(401);
//    } else {
//      SessionBO sessionBO = new SessionBO(loginAdminUserBO.getId(), loginAdminUserBO.getLoginName(),
//          loginAdminUserBO.getLoginName());
//      String token = RPCToken.generateToken(sessionBO, ACCOUNT_SECRET);
//      if (sessionService.getSession(loginAdminUserBO.getId()) == null) {
//        sessionService.replaceSession(sessionBO);
//      }
//      super.outputData(new LoginOutput(loginAdminUserBO.getId(), token));
//    }
  }

  @Override
  public void loginInitializr() {
    String cookieValue = null;
    // 识别访问是否携带cookie
    Cookie[] cookies = getRequest().getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("cookie")) {
          cookieValue = cookie.getValue();
        }
      }
    }

    boolean captchaRequired = false;
    if (cookieValue == null
        || sessionService.queryCookie(new SessionCookieBO(cookieValue, null)) == null) {
      // 初次访问或者再次访问的时候cookie已经过期,此时需要生成新的cookie
      SessionCookieBO sessionCookieBO = sessionService.produceCookie();
      if (sessionCookieBO != null) {
        cookieValue = sessionCookieBO.getCookie();
        if (sessionCookieBO.getTimes() >= cookieCaptchaThreshold) {
          captchaRequired = true;
        }
      }
    }

    if (cookieValue == null) {
      // 生成新的cookie失败
      outputException(500);
    } else {
      SessionCookieOutput loginCookieOutput = new SessionCookieOutput();
      loginCookieOutput.setCookie(cookieValue);
      loginCookieOutput.setExpires(cookieExpiresSeconds);
      loginCookieOutput.setCaptchaRequired(captchaRequired);
      outputData(loginCookieOutput);
    }
  }

  @Override
  public void loginByNameAndPwd(LoginInput input) {

  }

  @Override
  public void loginCaptchaRefresh(String cookie) {

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
