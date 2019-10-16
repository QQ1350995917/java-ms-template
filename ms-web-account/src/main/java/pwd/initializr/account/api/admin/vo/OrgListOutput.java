package pwd.initializr.account.api.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
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
 * date 2019-10-16 20:15
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
@ApiModel(value = "orgListOutput", description = "组织列表响应参数")
public class OrgListOutput {

  @ApiModelProperty(name = "hasNext", value = "是否还有下一页", required = true, example = "false")
  @NotNull(message = "0")
  private Boolean hasNext;

  @ApiModelProperty(name = "org", value = "组织数据集", required = false, example = "")
  @NotNull(message = "0")
  private List<OrgListItem> org;
}
