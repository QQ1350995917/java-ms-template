package pwd.initializr.monitor.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>host_ethernet数据表实体类</h2>
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
public class HostEthernetEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
    * 主键
    * 主机名称
    */
  private String id;
  /**
    * 主键
    * 网卡MAC地址
    */
  private String hwaddr;
  /**
    *
    * 网络设备名
    */
  private String name;
  /**
    *
    * 网卡类型
    */
  private String type;
  /**
    *
    * 网卡描述信息
    */
  private String description;
  /**
    *
    * IP地址
    */
  private String address;
  /**
    *
    * 目标地址
    */
  private String destination;
  /**
    *
    * 网关广播地址
    */
  private String broadcast;
  /**
    *
    * 子网掩码
    */
  private String netmask;
  /**
    *
    * 网卡标记
    */
  private Long flags;
  /**
    *
    * 网卡MTU
    */
  private Long mtu;
  /**
    *
    * 跃点数量
    */
  private Long metric;
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
    if (!(obj instanceof HostEthernetEntity)) {
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
