package pwd.initializr.organization.api.admin.vo;

import io.swagger.annotations.ApiModel;
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

  /**
   * 组织ID
   */
  private Long orgId;

  /**
   * 提交内容
   */
  private String content;
  /**
   * 关联ID
   */
  private Long refId;
  /**
   * 审核进度 see {@link Progress}
   */
  private Integer progress;

}
