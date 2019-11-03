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
 * date 2019-11-03 10:14
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
@ApiModel(value = "orgCreateOutput", description = "组织创建响应参数")
public class CreateOrgOutput extends CreateOrgInput {

  @ApiModelProperty(name = "id", value = "组织ID", required = true, example = "123456")
  @NotNull(message = "0")
  private Long id;
  @ApiModelProperty(name = "level", value = "组织等级", required = true, example = "123456")
  @NotNull(message = "0")
  private Integer level;
  @ApiModelProperty(name = "sort", value = "组织排序", required = true, example = "123456")
  @NotNull(message = "0")
  private Integer sort;
  @ApiModelProperty(name = "members", value = "组织成员数", required = true, example = "123456")
  @NotNull(message = "0")
  private Integer members;
  @ApiModelProperty(name = "progress", value = "组织进度", required = true, example = "123456")
  @NotNull(message = "0")
  private Integer progress;
  @ApiModelProperty(name = "status", value = "组织状态", required = true, example = "123456")
  @NotNull(message = "0")
  private Integer status;
  @ApiModelProperty(name = "createTime", value = "组织创建时间", required = true, example = "123456")
  @NotNull(message = "0")
  private Long createTime;


}
