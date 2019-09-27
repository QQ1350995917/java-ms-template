package pwd.initializr.account.persistence.dao;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.account.persistence.dao@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-27 17:46
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
public class UserOrgEntity {

  /**
   * 联合主键用户ID
   */
  @NotNull
  private Long userId;
  /**
   * 联合主键组织ID
   */
  @NotNull
  private Long orgId;
  /**
   * 成员等级 see {@link pwd.initializr.account.persistence.dao.UserOrgEntity.Level}
   */
  @NotNull
  private Integer level;
  /**
   * 数据状态 see {@link pwd.initializr.account.persistence.dao.ConstantStatus}
   */
  @NotNull
  private Integer status;
  /**
   * 首次创建时间
   */
  @NotNull
  private Long createTime;
  /**
   * 最近修改时间
   */
  @NotNull
  private Long updateTime;

  public enum Level {
    MEMBER(0),
    MANAGER(1),
    OWNER(2);

    private int value;

    Level(int value) {
      this.value = value;
    }

    public int value() {
      return value;
    }
  }

}
