package pwd.initializr.account.api.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.api.user.vo.LoginInput;
import pwd.initializr.account.api.user.vo.LoginOutput;
import pwd.initializr.account.business.user.SessionService;
import pwd.initializr.account.business.user.UserAccountService;
import pwd.initializr.account.business.user.bo.User;
import pwd.initializr.account.business.user.bo.UserAccount;
import pwd.initializr.account.rpc.RPCToken;
import pwd.initializr.account.rpc.RPCUserSession;
import pwd.initializr.common.web.api.user.UserController;

/**
 * pwd.initializr.account.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
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

  @Value("${account_secret}")
  private String ACCOUNT_SECRET;
  @Autowired
  private SessionService sessionService;
  @Autowired
  private UserAccountService userAccountService;

  @ApiOperation(value = "信息查询")
  @GetMapping(value = {"/{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void info(@PathVariable("id") Long id) {
    if ((getUid() != id) || id == 0) {
      outputException(400);
    } else {
      User session = sessionService.getSession(id);
      if (session == null) {
        outputException(401);
      } else {
        outputData(session); // TODO 转化为VO
      }
    }
  }

  @ApiOperation(value = "登录")
  @PutMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void login(@RequestBody LoginInput input) {
    UserAccount account = new UserAccount();
    BeanUtils.copyProperties(input, account);
    UserAccount accountByLoginNameAndPassword = userAccountService
        .findAccountByLoginNameAndPassword(input.getLoginName(), input.getPassword());
    User userByUserId = userAccountService
        .findUserByUserId(accountByLoginNameAndPassword.getUserId());
    userByUserId.setAccounts(Arrays.asList(new UserAccount[]{accountByLoginNameAndPassword}));
    if (accountByLoginNameAndPassword == null) {
      outputData(400);
    } else {
      RPCUserSession RPCUserSession = new RPCUserSession();
      BeanUtils.copyProperties(userByUserId, RPCUserSession);

      String token = RPCToken.generateToken(RPCUserSession, ACCOUNT_SECRET);
      if (sessionService.getSession(accountByLoginNameAndPassword.getUserId()) == null) {
        sessionService.updateSession(RPCUserSession);
      }
      outputData(new LoginOutput(userByUserId.getId(), token));
    }
  }

  @ApiOperation(value = "退出")
  @DeleteMapping(value = {"/{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void logout(@PathVariable("id") Long id) {
    if ((getUid() != id) || id == 0) {
      outputException(400);
    }
    sessionService.delSession(id);
    outputData();
  }
}
