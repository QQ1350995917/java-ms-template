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
 * date 2019-11-07 13:04
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrganizationMemberDeal {

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
   * 首次创建时间
   */
  private Long createTime;
  /**
   * 最近更新时间
   */
  private Long updateTime;
}
