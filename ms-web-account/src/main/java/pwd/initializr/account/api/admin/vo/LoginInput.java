package pwd.initializr.account.api.admin.vo;

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
 * pwd.initializr.account.api.admin.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-02 09:13
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
@ApiModel(value = "loginInput", description = "管理员登录请求参数")
public class LoginInput {

  @ApiModelProperty(name = "name", value = "登录名称", required = true, example = "DingPengwei")
  @NotNull(message = "0")
  private String name;
  @ApiModelProperty(name = "password", value = "登录密码", required = true, example = "123456")
  @NotNull(message = "0")
  private String password;
  @ApiModelProperty(name = "vCode", value = "图形验证码", required = false, example = "pwdxyz")
  @Null(message = "0")
  private String vCode;
  @ApiModelProperty(name = "smsCode", value = "短信验证码", required = false, example = "123456")
  @Null(message = "0")
  private String smsCode;

}