package pwd.initializr.organization.api.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pwd.initializr.organization.persistence.dao.OrganizationEntity.Progress;
import pwd.initializr.organization.persistence.dao.OrganizationProgressEntity.Type;

/**
 * pwd.initializr.organization.api.admin.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-04 14:15
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
@ApiModel(value = "orgReviewInput", description = "组织审核请求参数")
public class OrgReviewInput {

  @ApiModelProperty(name = "orgId", value = "组织ID", required = true, example = "1")
  @NotNull(message = "0")
  private Long orgId;
  @ApiModelProperty(name = "content", value = "审核结果描述", required = true, example = "要加油哦")
  @Null(message = "0")
  private String content;
  @ApiModelProperty(name = "refId", value = "相关审核记录ID", required = true, example = "1")
  @NotNull(message = "0")
  private Long refId;
  @ApiModelProperty(name = "progress", value = "审核进度", required = true, example = "1")
  @NotNull(message = "0")
  private Integer progress;

}
