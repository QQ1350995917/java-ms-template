package pwd.initializr.account.api.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.api.user.vo.SignUpByNamePwdInput;
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

  @Override
  public void createByNameAndPwd(
      @RequestBody @Validated @ApiParam(required = true) SignUpByNamePwdInput input) {
    System.out.println();
  }

  @Override
  public void get(
      @NotBlank(message = "参数不能为空") @RequestParam(value = "name", required = false) String name,
      @Max(value = 9, message = "不能大于9岁") @Min(value = 7, message = "不能小于7岁") @RequestParam(value = "age", required = false) Integer age) {
    System.out.println();
  }

}
