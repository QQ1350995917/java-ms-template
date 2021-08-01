package pwd.initializr.account.api.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
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
@ApiModel(value = "adminCreateInput", description = "创建管理员请求参数")
public class AdminCreateInput implements Serializable {

  @ApiModelProperty(name = "user", value = "用户信息", required = true)
  @Valid
  @NotNull(message = "用户信息不能为空")
  private AdminUserCreateInput user;
  @ApiModelProperty(name = "account", value = "账号信息", required = true)
  @Valid
  @NotNull(message = "账户信息不能为空")
  private AdminAccountCreateInput account;
  @ApiModelProperty(name = "contacts", value = "联系方式", required = true)
  @Valid
  @NotNull(message = "联系方式不能为空")
  private List<AdminContactCreateInput> contacts;
}
