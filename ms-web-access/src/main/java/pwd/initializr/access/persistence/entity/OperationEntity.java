package pwd.initializr.access.persistence.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>operation数据表实体类</h2>
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
public class OperationEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
    * 主键
    * 主键
    */
  private Long id;
  /**
    * 
    * 所属菜单
    */
  private Long mid;
  /**
    * 
    * 按钮名称
    */
  private String name;
  /**
    * 
    * 权限类别；C：创建；U：更新；R：读取；D：删除
    */
  private String curd;
  /**
    * 
    * 资源路径
    */
  private String path;
  /**
    * 
    * 简介
    */
  private String summary;
  /**
    * 
    * 排序
    */
  private Integer order;
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
    if (!(obj instanceof OperationEntity)) {
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
