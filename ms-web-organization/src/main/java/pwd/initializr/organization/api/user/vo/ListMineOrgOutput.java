package pwd.initializr.organization.api.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.organization.api.user.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-03 15:20
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
@ApiModel(value = "listMineOrg", description = "我的组织请求参数")
public class ListMineOrgOutput extends CreateOrgOutput {


  @ApiModelProperty(name = "myLevel", value = "组织中我的等级", required = true, example = "0")
  @NotNull(message = "0")
  private Integer myLevel;

  @ApiModelProperty(name = "myStatus", value = "组织中我的状态", required = true, example = "0")
  @NotNull(message = "0")
  private Integer myStatus;

}
