package pwd.initializr.access.persistence.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>role_menu_operation数据表实体类</h2>
 * date 2021-02-22 22:48
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class RoleMenuOperationEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
    * 主键
    * 外键，角色表主键
    */
  private Long rid;
  /**
    * 主键
    * 外键，菜单表主键
    */
  private Long mid;
  /**
    * 主键
    * 外键，操作表主键
    */
  private Long oid;
  /**
    * 
    * 可用状态：0:不可用；1:可用
    */
  private Integer able;
  /**
    * 
    * 删除状态：0:未删除；1:已删除
    */
  private Integer del;
  /**
    * 
    * 数据创建时间
    */
  private Date createTime;
  /**
    * 
    * 最近更新时间
    */
  private Date updateTime;
  /**
    * 
    * 数据版本号
    */
  private Long version;

  @Override
  public boolean equals(Object obj) {
    // fixme: 视情况而定是否修改或删除equals
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof RoleMenuOperationEntity)) {
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