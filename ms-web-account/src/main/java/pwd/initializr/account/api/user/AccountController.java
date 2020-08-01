package pwd.initializr.account.api.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.api.user.vo.SignUpByNamePwdInput;
import pwd.initializr.account.business.user.SessionService;
import pwd.initializr.account.business.user.UserAccountService;
import pwd.initializr.common.web.api.user.UserController;

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

  @Autowired
  private SessionService sessionService;

  @Autowired
  private UserAccountService userAccountService;

  @ApiOperation(value = "通过用户名和密码注册注册账号")
  @PutMapping(value = {"/primeval"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void createByNameAndPwd(@NotNull SignUpByNamePwdInput input) {
    System.out.println();
  }

  @ApiOperation(value = "test")
  @GetMapping(value = {"/test"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void get(
      @NotBlank(message = "参数不能为空") @RequestParam(value = "test", required = false) String string) {

  }


  @ApiOperation(value = "test2")
  @RequestMapping(value = {
      "/test2"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public void get2(
      @NotBlank(message = "参数不能为空") @RequestParam(value = "test", required = false) String string) {

  }
}
