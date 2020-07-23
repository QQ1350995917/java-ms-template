package pwd.initializr.account.api.user;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.api.user.vo.LoginInput;
import pwd.initializr.account.api.user.vo.LoginOutput;
import pwd.initializr.account.business.user.SessionService;
import pwd.initializr.account.business.user.UserAccountService;
import pwd.initializr.account.business.user.bo.SessionBO;
import pwd.initializr.account.business.user.bo.UserAccountBO;
import pwd.initializr.account.business.user.bo.UserBO;
import pwd.initializr.account.rpc.RPCToken;
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
  @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void getInfo() {
    SessionBO session = sessionService.getSession(getUid());
    if (session == null) {
      super.outputException(401);
    } else {
      JSONObject content = new JSONObject();
      content.put("roles", new String[]{"admin"});
      content.put("introduction", "My Space");
      content.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
      content.put("name", session.getLoginName());
      super.outputData(content);
    }
  }

  @ApiOperation(value = "登录")
  @PutMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void login(@RequestBody LoginInput input) {
//    UserAccountBO account = new UserAccountBO();
//    BeanUtils.copyProperties(input, account);
//    UserAccountBO loginUserAccountBo = userAccountService
//        .findAccountByLoginNameAndPassword(input.getLoginName(), input.getLoginPwd());
//    UserBO userByUserBOId = userAccountService
//        .findUserByUserId(loginUserAccountBo.getUserId());
//    userByUserBOId.setAccounts(Arrays.asList(new UserAccountBO[]{loginUserAccountBo}));
//    if (loginUserAccountBo == null) {
//      outputData(400);
//    } else {
//      SessionBO sessionBO = new SessionBO();
//      BeanUtils.copyProperties(userByUserBOId, sessionBO);
//      String cookie = RPCToken.generateToken(sessionBO, ACCOUNT_SECRET);
//      if (sessionService.getSession(loginUserAccountBo.getUserId()) == null) {
//        sessionService.replaceSession(sessionBO);
//      }
//      outputData(new LoginOutput(userByUserBOId.getId(), cookie));
//    }
  }

  @ApiOperation(value = "退出")
  @DeleteMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void logout() {
    sessionService.delSession(getUid());
    outputData();
  }
}
