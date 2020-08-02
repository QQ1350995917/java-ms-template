package pwd.initializr.account.api.user;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import pwd.initializr.account.api.user.vo.SignUpByNamePwdInput;

/**
 * pwd.initializr.account.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-14 21:17
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface AccountApi {

  @ApiOperation(value = "通过用户名和密码注册注册账号")
  @PutMapping(value = {"/primeval"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void createByNameAndPwd(
      @RequestBody
      @Validated
      @ApiParam(required = true)
      @NotBlank(message = "参数不能为空")
          SignUpByNamePwdInput input);

  @ApiOperation(value = "test")
  @GetMapping(value = {"/test"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void get(
      @NotBlank(message = "参数不能为空")
      @RequestParam(value = "name", required = false)
          String name,
      @Max(value = 9, message = "不能大于9岁")
      @Min(value = 7, message = "不能小于7岁")
      @RequestParam(value = "age", required = false)
          Integer age);
}
