package pwd.initializr.organization.business.user.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.organization.business.user.bo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-03 20:09
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrganizationProgress {

  /**
   * 主键
   */
  private Long id;
  /**
   * 组织ID
   */
  private Long orgId;
  /**
   * 提交人ID
   */
  private Long applicantId;
  /**
   * 提交内容
   */
  private String applicantContent;
  /**
   * 关联ID
   */
  private Long refId;
  /**
   * 审核人ID
   */
  private Long auditorId;
  /**
   * 审核答复
   */
  private String auditorContent;
  /**
   * 审核答复结果
   */
  private Integer auditorResult;
  /**
   * 审核答复时间
   */
  private Long auditorTime;
  /**
   * 数据状态
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
