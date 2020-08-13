package pwd.initializr.account.api.user;

import io.swagger.annotations.Api;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.api.admin.vo.LoginOutput;
import pwd.initializr.account.api.admin.vo.UserAccountOutput;
import pwd.initializr.account.api.user.vo.SessionCaptchaOutput;
import pwd.initializr.account.api.user.vo.SessionTokenOutput;
import pwd.initializr.account.api.user.vo.SignUpByNamePwdInput;
import pwd.initializr.account.api.user.vo.SignUpFailOutput;
import pwd.initializr.account.api.user.vo.SignUpFailOutput.FailType;
import pwd.initializr.account.business.common.bo.SessionBO;
import pwd.initializr.account.business.common.bo.SessionCaptchaBO;
import pwd.initializr.account.business.common.bo.SessionCookieBO;
import pwd.initializr.account.business.user.SessionService;
import pwd.initializr.account.business.user.UserAccountService;
import pwd.initializr.account.business.user.UserUserServiceWrap;
import pwd.initializr.account.business.user.bo.UserAccountBO;
import pwd.initializr.account.business.user.bo.UserUserBO;
import pwd.initializr.account.persistence.entity.AccountType;
import pwd.initializr.account.rpc.RPCToken;
import pwd.initializr.common.web.api.user.UserController;
import pwd.initializr.common.web.api.vo.Meta;
import pwd.initializr.common.web.api.vo.PageOutput;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.persistence.entity.EntityAble;

/**
 * pwd.initializr.account.api.user@ms-web-initializr
 *
 * <h1>控制层接口：账号信息</h1>
 *
 * date 2019-09-14 21:15
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "系统账号管理",
    value = "accountApi",
    description = "[手机验证码获取，手机号码注册]"
)
@RestController(value = "accountApi")
@RequestMapping(value = "/api/account")
@Slf4j
public class AccountController extends UserController implements AccountApi {

  @Value("${account.user.cookie.expires.seconds}")
  private Integer cookieExpiresSeconds;

  @Value("${account.user.cookie.captcha.threshold}")
  private Integer cookieCaptchaThreshold;

  @Value("${account.user.session.secret}")
  private String sessionSecret;


  @Autowired
  private UserAccountService userAccountService;

  @Autowired
  private UserUserServiceWrap userUserServiceWrap;

  @Autowired
  private SessionService sessionService;

  @Override
  public void createByNameAndPwd(@Valid @NotNull(message = "参数不能为空") SignUpByNamePwdInput input) {
    // TODO 校验验证码

    if (userAccountService.existLoginName(input.getLoginName())) {
      // 账号已被占用
      outputException(401);
      return;
    }

    UserUserBO userUserBO = new UserUserBO();
    UserAccountBO userAccountBO = new UserAccountBO();
    userAccountBO.setType(AccountType.ByNamePwd.getType());
    UserUserBO insertedUserUserBO = userUserServiceWrap.insert(userUserBO, userAccountBO);
    if (insertedUserUserBO == null) {
      // 账号创建失败
      outputException(500);
      return;
    }

    // 账号创建完成后自动登录
    SessionBO sessionBO = new SessionBO(userUserBO.getId(), userUserBO.getName(),
        userAccountBO.getId(), userAccountBO.getLoginName(),
        System.currentTimeMillis());
    String token = RPCToken.generateToken(sessionBO, sessionSecret);
    sessionService.createSession(token, sessionBO);
    outputData(new LoginOutput(sessionBO.getUid(), token));
  }

  @Override
  public void createInitializr(String token) {
    String cookie = getToken();
    // TODO 配置化
    Boolean captchaRequired = true;
    SessionCookieBO sessionCookieBO = null;
    // 初次访问没有携带cookie，需要生成新的cookie
    if (StringUtils.isBlank(cookie)) {
      cookie = sessionService.createCookie();
      if (cookie == null) {
        // 生成新的cookie失败
        outputException(500);
        return;
      }
      sessionCookieBO = new SessionCookieBO(0, null);
    } else {
      sessionCookieBO = sessionService.queryCookie(cookie);
      if (sessionCookieBO == null) {
        // cookie 比较旧，得更新
        outputException(401, new SignUpFailOutput(FailType.CookieISExpires));
        return;
      }
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

  @Override
  public void deleteById(
      @Valid @NotNull(message = "参数不能为空") @Min(value = 1, message = "参数不能小于1") Long id) {
    // TODO 检查是否是自己的账号
    Integer result = userAccountService.deleteById(id,getUid());
    outputData(new Meta(), result);
  }

  @Override
  public void disableById(
      @Valid @NotNull(message = "参数不能为空") @Min(value = 1, message = "参数不能小于1") Long id) {
    // TODO 检查是否是自己的账号
    Integer result = userAccountService.ableById(Arrays.asList(id), EntityAble.DISABLE);
    outputData(new Meta(), result);
  }

  @Override
  public void enableById(
      @Valid @NotNull(message = "参数不能为空") @Min(value = 1, message = "参数不能小于1") Long id) {
    // TODO 检查是否是自己的账号
    Integer result = userAccountService.ableById(Arrays.asList(id), EntityAble.ENABLE);
    outputData(new Meta(), result);
  }

  @Override
  public void findByUserId() {
    Long userId = getUid();
    PageableQueryResult<UserAccountBO> userAccountBOPageableQueryResult = userAccountService
        .queryAllByUserId(userId);
    PageOutput<UserAccountOutput> userAccountOutputPageOutput = new PageOutput<>();
    userAccountOutputPageOutput.setTotal(userAccountBOPageableQueryResult.getTotal());
    userAccountOutputPageOutput.setIndex(userAccountBOPageableQueryResult.getIndex());
    userAccountOutputPageOutput.setSize(userAccountBOPageableQueryResult.getSize());
    List<UserAccountBO> elements = userAccountBOPageableQueryResult.getElements();
    elements.forEach(userAccountBO -> {
      UserAccountOutput userAccountOutput = new UserAccountOutput();
      BeanUtils.copyProperties(userAccountBO, userAccountOutput);
      userAccountOutputPageOutput.getElements().add(userAccountOutput);
    });
    outputData(userAccountOutputPageOutput);
  }

  @Override
  public void loginCaptchaRefresh() {
    String cookie = getToken();
    if (StringUtils.isBlank(cookie)) {
      // 参数不合规
      outputException(401);
      return;
    }
    SessionCookieBO sessionCookieBO = sessionService.queryCookie(cookie);
    if (sessionCookieBO == null) {
      // cookie 过期
      outputException(401);
      return;
    }
    if (sessionCookieBO.getTimes() < cookieCaptchaThreshold) {
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
  public void usabilityCheck(
      @Valid @NotBlank(message = "参数不能为空") @Size(min = 6, max = 18, message = "账号长度必须在[6,18]之间") String loginName) {
    if (userAccountService.existLoginName(loginName)) {
      // 账号已被占用
      outputException(401);
      return;
    }
    outputData(loginName);
  }
}
