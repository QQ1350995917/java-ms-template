package pwd.initializr.organization.api.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
 * date 2019-10-16 20:07
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(value = "orgListInput", description = "组织列表请求参数")
public class OrgListInput {

  @ApiModelProperty(name = "status", value = "状态", required = true, example = "0")
  @NotNull(message = "0")
  private Integer status;

  @ApiModelProperty(name = "lastId", value = "最后一条数据的ID", required = false, example = "0")
  @NotNull(message = "0")
  private Long lastId;

  @ApiModelProperty(name = "pageSize", value = "页面展示条数", required = true, example = "12")
  @NotNull(message = "0")
  private Integer pageSize = 12;

}
