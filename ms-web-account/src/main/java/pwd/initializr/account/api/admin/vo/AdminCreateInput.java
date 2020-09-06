package pwd.initializr.account.api.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
 * date 2019-11-04 22:48
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(value = "createAdminInput", description = "管理员创建请求参数")
public class CreateAdminInput implements Serializable {

  @ApiModelProperty(name = "user", value = "用户信息", required = true)
  @Valid
  @NotNull(message = "用户信息不能为空")
  private AdminUserInput user;
  @ApiModelProperty(name = "account", value = "账号信息", required = true)
  @Valid
  @NotNull(message = "账户信息并不能为空")
  private AdminAccountInput account;
}
