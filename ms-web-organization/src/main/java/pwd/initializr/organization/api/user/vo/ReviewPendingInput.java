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
@ApiModel(value = "reviewPendingInput", description = "组织审核申请工单")
public class ReviewPendingInput {
  @ApiModelProperty(name = "orgId", value = "组织ID", required = true, example = "1")
  @NotNull(message = "0")
  private Long orgId;
  @ApiModelProperty(name = "applicantContent", value = "工单内容", required = true, example = "这是一个工单内容")
  @NotNull(message = "0")
  private String content;
  @ApiModelProperty(name = "refId", value = "相关的工单ID", required = false, example = "0")
  @Null(message = "0")
  private Long refId;

}
