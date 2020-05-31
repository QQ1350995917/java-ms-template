package pwd.initializr.account.api.admin;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.api.admin.vo.LoginInput;
import pwd.initializr.account.api.admin.vo.LoginOutput;
import pwd.initializr.account.business.admin.AdminService;
import pwd.initializr.account.business.admin.SessionService;
import pwd.initializr.account.business.admin.bo.AdminBO;
import pwd.initializr.account.business.admin.bo.SessionBO;
import pwd.initializr.account.rpc.RPCToken;
import pwd.initializr.common.web.api.admin.AdminController;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
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

  @Autowired
  private AdminService adminService;

  @Autowired
  private SessionService sessionService;


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
      content.put("introduction", "I am a super administrator");
      content.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
      content.put("name", "Super Admin");
      super.outputData(content);
    }
  }

  @ApiOperation(value = "登录")
  @PutMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void login(@RequestBody LoginInput input) {
    AdminBO loginAdminBO = adminService
        .queryByLoginNameAndLoginPassword(input.getLoginName(), input.getLoginPassword());
    if (loginAdminBO == null) {
      super.outputData(401);
    } else {
      SessionBO sessionBO = new SessionBO(loginAdminBO.getId(), loginAdminBO.getLoginName(),
          loginAdminBO.getLoginName());
      String token = RPCToken.generateToken(sessionBO, ACCOUNT_SECRET);
      if (sessionService.getSession(loginAdminBO.getId()) == null) {
        sessionService.replaceSession(sessionBO);
      }
      super.outputData(new LoginOutput(loginAdminBO.getId(), token));
    }
  }

  @ApiOperation(value = "退出")
  @DeleteMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void logout() {
    sessionService.delSession(getUid());
  }
}
