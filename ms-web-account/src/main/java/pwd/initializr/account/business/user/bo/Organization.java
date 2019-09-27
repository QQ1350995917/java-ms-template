package pwd.initializr.account.business.user.bo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.account.business.user.bo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-27 22:38
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
public class Organization {

  /**
   * 主键ID
   */
  @NotNull
  private Long id;
  /**
   * 外键上级组织ID
   */
  @NotNull
  private Long pid;
  /**
   * 组织名称
   */
  @Null
  private String name;
  /**
   * 组织logo
   */
  @Null
  private String logo;
  /**
   * 组织描述
   */
  @Null
  private String description;
  /**
   * 组织口号
   */
  @Null
  private String slogan;
  /**
   * 组织等级
   */
  @NotNull
  private Integer level = 0;
  /**
   * 组织排序
   */
  @NotNull
  private Integer sort = 0;
  /**
   * 成员数量
   */
  @NotNull
  private Integer members = 1;
  /**
   * 状态
   * see {@link pwd.initializr.account.persistence.dao.ConstantStatus}
   */
  @NotNull
  private Integer status = 0;
  /**
   * 创建时间
   */
  @NotNull
  private Long createTime;
  /**
   * 更新时间
   */
  @NotNull
  private Long updateTime;
}
