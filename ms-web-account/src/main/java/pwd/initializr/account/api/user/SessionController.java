package pwd.initializr.account.api.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import pwd.initializr.account.business.user.bo.Account;
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

  @Autowired
  private SessionService sessionService;

  @ApiOperation(value = "登录")
  @PutMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void login(@RequestBody LoginInput input) {
    Account  account= new Account();
    BeanUtils.copyProperties(input, account);
    String session = sessionService.login(account);
    if (session == null) {
      outputData(400);
    } else {
      outputData(new LoginOutput(session));
    }
  }

  @ApiOperation(value = "信息查询")
  @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void info() {
    Account account = sessionService.info("1");// TODO 获取登录信息
    outputData(account); // TODO 转化为VO
  }

  @ApiOperation(value = "退出")
  @DeleteMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void logout() {
    sessionService.logout("1");// TODO 获取登录信息
    outputData();
  }
}
