package pwd.initializr.organization.persistence.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>organization_member数据表实体类</h2>
 * date 2021-02-20 18:58
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class OrganizationMemberEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
    * 主键
    * 外键，组织ID
    */
  private Long orgId;
  /**
    * 主键
    * 外键，成员ID
    */
  private Long memId;
  /**
    * 
    * 排序
    */
  private Integer sort;
  /**
    * 
    * 状态，0禁用，1可用
    */
  private Integer able;
  /**
    * 
    * 状态，0正常，1删除
    */
  private Integer del;
  /**
    * 
    * 首次创建时间
    */
  private Date createTime;
  /**
    * 
    * 最近修改时间
    */
  private Date updateTime;

  @Override
  public boolean equals(Object obj) {
    // fixme: 视情况而定是否修改或删除equals
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof OrganizationMemberEntity)) {
      return false;
    }
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    // fixme: 视情况而定是否修改或删除hashCode
    return super.hashCode();
  }
}
