package pwd.initializr.organization.persistence.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>organization数据表实体类</h2>
 * date 2021-02-22 21:33
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class OrganizationEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
    * 主键
    * 主键
    */
  private Long id;
  /**
    * 
    * 上级组织
    */
  private Long pid;
  /**
    * 
    * 组织名称
    */
  private String name;
  /**
    * 
    * 组织logo
    */
  private String logo;
  /**
    * 
    * 组织描述
    */
  private String description;
  /**
    * 
    * 组织slogan
    */
  private String slogan;
  /**
    * 
    * 组织排序
    */
  private Integer sort;
  /**
    * 
    * 组织成员数量
    */
  private Integer members;
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
    if (!(obj instanceof OrganizationEntity)) {
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
