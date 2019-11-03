package pwd.initializr.organization.api.user.vo;

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
 * pwd.initializr.organization.api.user.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-03 16:33
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
public class ReviewPendingOutput extends ReviewPendingInput{
  @ApiModelProperty(name = "id", value = "工单ID", required = true, example = "1")
  @NotNull(message = "0")
  private Long id;
  @ApiModelProperty(name = "applicantId", value = "申请者ID", required = true, example = "1")
  @NotNull(message = "0")
  private Long applicantId;
  @ApiModelProperty(name = "auditorId", value = "审核者ID", required = true, example = "1")
  @Null(message = "0")
  private Long auditorId;
  @ApiModelProperty(name = "auditorContent", value = "审核备注", required = true, example = "1")
  @Null(message = "0")
  private String auditorContent;
  @ApiModelProperty(name = "auditorResult", value = "审核结果", required = true, example = "1")
  @Null(message = "0")
  private Integer auditorResult;
  @ApiModelProperty(name = "auditorTime", value = "审核时间", required = true, example = "1")
  @Null(message = "0")
  private Long auditorTime;
  @ApiModelProperty(name = "orgId", value = "组织ID", required = true, example = "1")
  @NotNull(message = "0")
  private Integer status;
  @ApiModelProperty(name = "orgId", value = "组织ID", required = true, example = "1")
  @NotNull(message = "0")
  private Long createTime;

}
