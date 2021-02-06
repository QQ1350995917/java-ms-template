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
import pwd.initializr.account.api.vo.SessionCreateOkOutput;
import pwd.initializr.account.api.admin.vo.UserAccountOutput;
import pwd.initializr.account.api.vo.SessionInitOutput;
import pwd.initializr.account.api.user.vo.SignUpByNamePwdInput;
import pwd.initializr.account.api.vo.SessionStatus;
import pwd.initializr.account.business.session.SessionService;
import pwd.initializr.account.business.session.bo.SessionBO;
import pwd.initializr.account.business.session.bo.SessionBOAnonymous;
import pwd.initializr.account.business.session.bo.SessionBONamed;
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

  @Value("${account.user.session.anonymous.expires.seconds}")
  private Integer anonymousSessionExpiresSeconds;

  @Value("${account.user.session.anonymous.captcha.threshold}")
  private Integer anonymousSessionCaptchaThreshold;

  @Value("${account.user.session.named.secret}")
  private String namedSessionSecret;


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
    userAccountBO.setLoginName(input.getLoginName());
    userAccountBO.setLoginPwd(input.getLoginPwd());
    userAccountBO.setType(AccountType.LoginNameAndLoginPwd.getType());
    UserAccountBO insertedUserAccountBO = userUserServiceWrap.insert(userUserBO, userAccountBO);
    if (insertedUserAccountBO == null) {
      // 账号创建失败
      outputException(500);
      return;
    }

    // TODO: 不一定要登录
    // 账号创建完成后自动登录
    SessionBONamed sessionBONamed = new SessionBONamed(insertedUserAccountBO.getUid(), userUserBO.getName(),
        insertedUserAccountBO.getId(), userAccountBO.getLoginName(),
        System.currentTimeMillis());
    String token = RPCToken.generateToken(sessionBONamed, namedSessionSecret);
    //sessionService.createSession(token, sessionBONamed);
    outputData(new SessionCreateOkOutput(sessionBONamed.getUid(), sessionBONamed.getAid(), token));
  }

  @Override
  public void createInitializr(Long aid, Long uid, String token) {
    SessionBO session = sessionService.createSession(token, uid);
    if (session instanceof SessionBONamed) {
      SessionInitOutput loginCookieOutput = new SessionInitOutput();
      loginCookieOutput.setStatus(SessionStatus.NAMED.getNumber());
      outputData(202,loginCookieOutput);
      return;
    }
    if (session instanceof SessionBOAnonymous) {
      Boolean captchaRequired = false;
      // 是否对该匿名 token 产生验证码
      if (((SessionBOAnonymous) session).getTimes() >= anonymousSessionCaptchaThreshold) {
        captchaRequired = true;
        sessionService.createCaptcha(token);
      }
      SessionInitOutput loginInitOutput = new SessionInitOutput();
      loginInitOutput.setStatus(SessionStatus.ANONYMOUS.getNumber());
      loginInitOutput.setToken(token);
      loginInitOutput.setExpires(anonymousSessionExpiresSeconds);
      loginInitOutput.setCaptchaRequired(captchaRequired);
      // TODO 登录方式列表
      outputData(loginInitOutput);
      return;
    }
    // 生成匿名token失败
    outputException(500);
  }

  @Override
  public void deleteById(
      @Valid @NotNull(message = "参数不能为空") @Min(value = 1, message = "参数不能小于1") Long id) {
    Integer result = userAccountService.deleteById(id,getUid());
    outputData(new Meta(), result);
  }

  @Override
  public void disableById(
      @Valid @NotNull(message = "参数不能为空") @Min(value = 1, message = "参数不能小于1") Long id) {
    Integer result = userAccountService.ableById(Arrays.asList(id), EntityAble.DISABLE);
    outputData(new Meta(), result);
  }

  @Override
  public void enableById(
      @Valid @NotNull(message = "参数不能为空") @Min(value = 1, message = "参数不能小于1") Long id) {
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

  }

  @Override
  public void usabilityCheck(
      @Valid @NotBlank(message = "参数不能为空") @Size(min = 4, max = 18, message = "账号长度必须在[4,18]之间") String loginName) {
    if (userAccountService.existLoginName(loginName)) {
      // 账号已被占用
      outputException(401);
      return;
    }
    outputData(loginName);
  }
}
