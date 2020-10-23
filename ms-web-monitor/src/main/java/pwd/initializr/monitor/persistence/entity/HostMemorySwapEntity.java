package pwd.initializr.monitor.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>host_swap数据表实体类</h2>
 * date 2020-10-23 11:58
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class HostMemorySwapEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
    * 主键
    * 主机名称
    */
  private String id;
  /**
    *
    * swap总量
    */
  private Long total;
  /**
    *
    * swap使用量
    */
  private Long used;
  /**
    *
    * swap空闲量
    */
  private Long free;
  /**
    *
    * swapPageIn
    */
  private Long pageIn;
  /**
    *
    * swapPageOut
    */
  private Long pageOut;
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

  @Override
  public boolean equals(Object obj) {
    // fixme: 视情况而定是否修改或删除equals
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof HostMemorySwapEntity)) {
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
