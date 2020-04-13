package pwd.initializr.organization.persistence.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pwd.initializr.organization.persistence.dao.OrganizationEntity.Progress;

/**
 * pwd.initializr.organization.persistence.entity@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-03 17:31
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
public class OrganizationReviewEntity {

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
  private Long editorId;
  /**
   * 提交内容
   */
  private String content;
  /**
   * 关联ID
   */
  private Long refId;
  /**
   * 数据类型
   * see {@link Type}
   */
  private Integer type;
  /**
   * 审核进度 see {@link Progress}
   */
  private Integer progress;
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


  public enum Type {
    // 记录类型，1：审核，用户类型，2：答复，管理员类型
    USER(0),// 用户类型
    ADMIN(1); // 管理员类型

    private int value;

    Type(int value) {
      this.value = value;
    }

    public int value() {
      return value;
    }
  }

}
