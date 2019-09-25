package pwd.initializr.account.api.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.api.user.vo.ListSignUpByWaysInput;
import pwd.initializr.account.api.user.vo.ListSignUpByWaysOutput;
import pwd.initializr.account.api.user.vo.SignUpByApplyInput;
import pwd.initializr.account.api.user.vo.SignUpByEmailInput;
import pwd.initializr.account.api.user.vo.SignUpByPhoneNumberInput;
import pwd.initializr.account.api.user.vo.SignUpByPhoneNumberOutput;
import pwd.initializr.account.business.user.EntranceService;
import pwd.initializr.account.business.user.UserAccountService;
import pwd.initializr.account.business.user.bo.Account;
import pwd.initializr.account.business.user.bo.Entrance;
import pwd.initializr.account.business.user.bo.User;
import pwd.initializr.account.business.user.bo.UserAccount;
import pwd.initializr.account.persistence.dao.AccountEntity.Type;
import pwd.initializr.account.persistence.dao.ConstantStatus;
import pwd.initializr.common.web.api.user.UserController;
import pwd.initializr.common.web.api.vo.SMSCodeInput;
import pwd.initializr.common.web.business.bo.ObjectList;
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
    tags = "注册系统账号",
    value = "accountCreateApi",
    description = "用户注册系统账号的API"
)
@RestController(value = "accountCreateApi")
@RequestMapping(value = "/api/signup")
public class SignUpController extends UserController implements SignUpApi, SignUpWayApi {

  private Logger logger = LoggerFactory.getLogger(getClass());
  @Autowired
  private UserAccountService userAccountService;

  @Autowired
  private EntranceService entranceService;

  @ApiOperation(value = "注册方式清单")
  @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void listSignUpWays(ListSignUpByWaysInput input) {
    ObjectList<Entrance> userEntranceByType = entranceService.findUserEntranceByType(0);
    ObjectList<ListSignUpByWaysOutput> result = new ObjectList<>();
    BeanUtils.copyProperties(userEntranceByType, result);
    List<Entrance> userEntranceByTypeElements = userEntranceByType.getElements();
    List<ListSignUpByWaysOutput> resultElements = new LinkedList<>();
    for (Entrance userEntranceByTypeElement : userEntranceByTypeElements) {
      ListSignUpByWaysOutput listSignUpByWaysOutput = new ListSignUpByWaysOutput();
      BeanUtils.copyProperties(userEntranceByTypeElement, listSignUpByWaysOutput);
      resultElements.add(listSignUpByWaysOutput);
    }
    result.setElements(resultElements);
    super.outputData(result);
  }

  @ApiOperation(value = "申请账号")
  @PostMapping(value = {"/apply"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void signUpByApply(SignUpByApplyInput input) {
    super.outputData();
  }

  @ApiOperation(value = "手机号验证")
  @PostMapping(value = {"/phone/code"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void verifySignUpPhoneNumber(SMSCodeInput input) {
    super.verifyPhone(input.getPhoneNumber());
  }

  @ApiOperation(value = "手机号注册账号")
  @PostMapping(value = {"/phone"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void signUpByPhoneNumber(SignUpByPhoneNumberInput input) {
    SMSCode smsCode = new SMSCode();
    BeanUtils.copyProperties(input, smsCode);
    Boolean match = smsCodeService.matchOnce(smsCode);
    if (match) {
      // TODO session设置，返回身份信息，跳转页面
      User user = new User(null, input.getPhoneNumber(), ConstantStatus.ENABLE.value(),
          System.currentTimeMillis(),
          System.currentTimeMillis());
      Account account = new Account(null, null, input.getPhoneNumber(), null, Type.PHONE.value(),
          ConstantStatus.ENABLE.value(), System.currentTimeMillis(), System.currentTimeMillis());
      UserAccount userAccount = userAccountService.createUserAccount(user, account);
      SignUpByPhoneNumberOutput signUpByPhoneNumberOutput = new SignUpByPhoneNumberOutput();
      BeanUtils.copyProperties(userAccount.getAccounts().get(0), signUpByPhoneNumberOutput);
      BeanUtils.copyProperties(userAccount.getUser(), signUpByPhoneNumberOutput);
      super.outputData(signUpByPhoneNumberOutput);
    } else {
      super.outputException(401);
    }
  }

  @ApiOperation(value = "邮箱注册账号")
  @PostMapping(value = {"/email"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void signUpByEmail(SignUpByEmailInput input) {

    super.outputData();
  }

  @Override
  public void signUpByWeChat() {

    super.outputData();
  }

  @Override
  public void signUpByAlipay() {

    super.outputData();
  }

  @Override
  public void signUpBySinaBlog() {

    super.outputData();
  }


}
