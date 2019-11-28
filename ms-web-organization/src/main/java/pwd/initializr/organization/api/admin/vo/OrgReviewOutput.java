package pwd.initializr.organization.api.admin.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pwd.initializr.organization.persistence.dao.OrganizationReviewEntity.Type;

/**
 * pwd.initializr.organization.api.admin.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-04 14:15
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
@ApiModel(value = "orgReviewOutput", description = "组织审核响应参数")
public class OrgReviewOutput extends OrgReviewInput {

  /**
   * 主键
   */
  private Long id;
  /**
   * 提交人ID
   */
  private Long editorId;
  /**
   * 数据类型
   * see {@link Type}
   */
  private Integer type;
  /**
   * 审核答复时间
   */
  private Long auditorTime;

  /**
   * 数据状态 see {@link pwd.initializr.organization.persistence.dao.ConstantStatus}
   */
  private Integer status;
  /**
   * 创建时间
   */
  private Long createTime;
  /**
   * 更新时间
   */
  private Long updateTime;
}
