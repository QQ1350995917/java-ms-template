package pwd.initializr.account.api.user;

import io.swagger.annotations.Api;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
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
  public void createByNameAndPwd(@NotNull(message = "参数不能为空") SignUpByNamePwdInput input) {

  }

  @Override
  public void deleteById(
      @NotNull(message = "参数不能为空") @Min(value = 1, message = "参数不能小于1") Long id) {

  }

  @Override
  public void disableById(
      @NotNull(message = "参数不能为空") @Min(value = 1, message = "参数不能小于1") Long id) {

  }

  @Override
  public void enableById(
      @NotNull(message = "参数不能为空") @Min(value = 1, message = "参数不能小于1") Long id) {

  }

  @Override
  public void findByUserId() {

  }

  @Override
  public void usabilityCheck(
      @NotBlank(message = "参数不能为空") @Size(min = 6, max = 18, message = "账号长度必须在[6,18]之间") String loginName) {

  }
}
