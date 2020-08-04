package pwd.initializr.account.api.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.account.api.user.vo@ms-web-initializr
 *
 * <h1>使用账号密码注册结构声明</h1>
 *
 * date 2019-09-14 21:30
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(value = "signUpByNamePwd", description = "使用账号密码注册")
public class SignUpByNamePwdInput {

  @ApiModelProperty(name = "loginName", value = "账号", required = true, example = "18511694468")
  @NotBlank(message = "账号不能为空")
  @Size(min = 6, max = 18, message = "账号长度必须在[6,18]之间")
  private String loginName;
  @ApiModelProperty(name = "loginPwd", value = "密码", required = true, example = "pwd")
  @NotBlank(message = "密码不能为空")
  @Size(min = 8, max = 24, message = "密码长度必须在[6,24]之间")
  private String loginPwd;
  @ApiModelProperty(name = "captcha", value = "图形验证码", required = true, example = "turing")
  @NotBlank(message = "验证码不能为空")
  @Size(min = 1, max = 6, message = "验证码长度必须在[1,6]之间")
  private String captcha;
}
