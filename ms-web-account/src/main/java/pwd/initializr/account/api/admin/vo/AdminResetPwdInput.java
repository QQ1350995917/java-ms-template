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
 * date 2021-08-21 17:14
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
@ApiModel(value = "adminResetPwdInput", description = "重置管理员密码参数")
public class AdminResetPwdInput implements Serializable {

  @ApiModelProperty(name = "uid", value = "用户ID", required = true)
  @Valid
  @NotNull(message = "用户ID不能为空")
  private Long uid;
  @ApiModelProperty(name = "aid", value = "账号ID", required = true)
  @Valid
  @NotNull(message = "账户ID不能为空")
  private Long aid;

}
