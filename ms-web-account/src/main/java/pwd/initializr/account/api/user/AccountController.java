package pwd.initializr.account.api.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.api.user.vo.LoginOutput;
import pwd.initializr.account.api.user.vo.SignUpByPhoneInput;
import pwd.initializr.account.business.user.SessionService;
import pwd.initializr.account.business.user.UserAccountService;
import pwd.initializr.account.business.user.bo.Account;
import pwd.initializr.account.business.user.bo.User;
import pwd.initializr.account.business.user.bo.UserAccount;
import pwd.initializr.account.persistence.dao.AccountEntity.Type;
import pwd.initializr.account.persistence.dao.ConstantStatus;
import pwd.initializr.common.web.api.user.UserController;
import pwd.initializr.common.web.api.vo.SMSCodeInput;
import pwd.initializr.common.web.business.bo.SMSCode;

/**
 * pwd.initializr.account.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
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
public class AccountController extends UserController implements AccountApi {

  private Logger logger = LoggerFactory.getLogger(getClass());
  @Autowired
  private UserAccountService userAccountService;

  @Autowired
  private SessionService sessionService;

  @ApiOperation(value = "手机号验证码")
  @GetMapping(value = {"/phone/code"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void getSMSCode(SMSCodeInput input) {
    super.verifyPhone(input.getPhoneNumber());
  }

  @ApiOperation(value = "手机号注册账号")
  @PutMapping(value = {"/phone"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void createByPhone(@RequestBody SignUpByPhoneInput input) {
    SMSCode smsCode = new SMSCode();
    BeanUtils.copyProperties(input, smsCode);
    Boolean match = smsCodeService.matchOnce(smsCode);
    match = true;
    if (match) {
      // TODO session设置，返回身份信息，跳转页面
      User user = new User(null, input.getUsername(), input.getPhoneNumber(), ConstantStatus.ENABLE.value(),
          System.currentTimeMillis(),
          System.currentTimeMillis());
      Account account = new Account(null, null, input.getPhoneNumber(), null, Type.PHONE.value(),
          ConstantStatus.ENABLE.value(), System.currentTimeMillis(), System.currentTimeMillis());
      UserAccount userAccount = userAccountService.createUserAccount(user, account);
      sessionService.createSession(account);
      String session = sessionService.genToken(account);
      // TODO addCookie
      super.outputData(new LoginOutput(session));
    } else {
      super.outputException(401);
    }
  }

}
