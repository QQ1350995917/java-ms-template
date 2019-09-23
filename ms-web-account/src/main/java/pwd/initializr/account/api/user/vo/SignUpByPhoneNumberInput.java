package pwd.initializr.account.api.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.account.api.user.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
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
@ApiModel(value = "signUpByPhoneNumber", description = "使用手机号码注册")
public class SignUpByPhoneNumberInput {

  @ApiModelProperty(name = "phoneNumber", value = "手机号码", required = true, example = "18511694468")
  @NotNull(message = "0")
  private String phoneNumber;

  @ApiModelProperty(name = "smsCode", value = "短信验证码", required = true, example = "123456")
  @NotNull(message = "0")
  private String smsCode;

  @ApiModelProperty(name = "vCode", value = "图形验证码(登录响应无验证码时可为空)", required = true, example = "123456")
  @Null(message = "0")
  private String vCode;
}
