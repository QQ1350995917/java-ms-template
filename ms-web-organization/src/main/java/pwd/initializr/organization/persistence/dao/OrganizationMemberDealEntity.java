package pwd.initializr.organization.persistence.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.organization.persistence.dao@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-07 12:50
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrganizationMemberDealEntity {

  /**
   * 组织ID
   */
  private Long orgId;
  /**
   * 用户ID
   */
  private Long userId;
  /**
   * 发起 0：组织发起，1:用户发起
   */
  private Integer type;
  /**
   * 0:未达成，1:已达成
   */
  private Integer deal;
  /**
   * 达成前的计数器
   */
  private Integer counter;
  /**
   * 状态，0正常1禁用2删除
   */
  private Integer status;
  /**
   * 首次创建时间
   */
  private Long createTime;
  /**
   * 最近更新时间
   */
  private Long updateTime;
}
