package pwd.initializr.account.business.admin.bo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pwd.initializr.account.persistence.dao.OrganizationEntity.Progress;

/**
 * pwd.initializr.account.business.admin.bo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-10-12 18:36
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
   * 处理过程 see {@link Progress}
   */
  @NotNull
  private Integer progress = 0;
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
