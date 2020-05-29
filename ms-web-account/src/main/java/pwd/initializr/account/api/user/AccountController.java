package pwd.initializr.account.api.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.api.user.vo.LoginOutput;
import pwd.initializr.account.api.user.vo.SignUpByPhoneInput;
import pwd.initializr.account.business.user.SessionService;
import pwd.initializr.account.business.user.UserAccountService;
import pwd.initializr.account.business.user.bo.UserBO;
import pwd.initializr.account.business.user.bo.UserAccountBO;
import pwd.initializr.account.persistence.entity.UserAccountType;
import pwd.initializr.account.rpc.RPCToken;
import pwd.initializr.account.rpc.RPCUserSession;
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
@Slf4j
public class AccountController extends UserController implements AccountApi {

  @Value("${account_secret}")
  private String ACCOUNT_SECRET;

  @Autowired
  private SessionService sessionService;

  @Autowired
  private UserAccountService userAccountService;

  @ApiOperation(value = "手机号注册账号")
  @PutMapping(value = {"/phone"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void createByPhone(@RequestBody SignUpByPhoneInput input) {
    SMSCode smsCode = new SMSCode();
    BeanUtils.copyProperties(input, smsCode);
    Boolean match = smsCodeService.matchOnce(smsCode);
    // TODO 对接短信后删除 或者修改为插件形式
    match = true;
    if (match) {
      // TODO session设置，返回身份信息，跳转页面
      UserBO userBO = new UserBO(input.getUsername(), input.getPhoneNumber());
      UserAccountBO account = new UserAccountBO(input.getPhoneNumber(), null,
          UserAccountType.ByPhoneNumber);
      userBO.setAccounts(Arrays.asList(new UserAccountBO[]{account}));
      // TODO 优化password业务
      String password = smsCode.getSmsCode();
      UserBO userBOAndAccount = userAccountService.createUserAndAccount(userBO);

      RPCUserSession RPCUserSession = new RPCUserSession();
      BeanUtils.copyProperties(userBOAndAccount, RPCUserSession);

      sessionService.updateSession(RPCUserSession);

      String token = RPCToken.generateToken(RPCUserSession, ACCOUNT_SECRET);
      // TODO addCookie
      super.outputData(new LoginOutput(userBO.getId(), token));
    } else {
      super.outputException(401);
    }
  }

  @ApiOperation(value = "手机号验证码")
  @GetMapping(value = {"/phone/code"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void getSMSCode(SMSCodeInput input) {
    super.verifyPhone(input.getPhoneNumber());
  }

}
