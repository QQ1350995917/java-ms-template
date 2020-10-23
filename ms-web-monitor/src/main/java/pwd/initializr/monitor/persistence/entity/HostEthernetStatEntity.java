package pwd.initializr.monitor.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>host_ethernet_stat数据表实体类</h2>
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
public class HostEthernetStatEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
    *
    * 主机名称
    */
  private String id;
  /**
    *
    * 网卡名称
    */
  private String hwaddr;
  /**
    *
    * 接收到的总字节数
    */
  private Long rxBytes;
  /**
    *
    * 接收的总包裹数
    */
  private Long rxPackets;
  /**
    *
    * 接收到的错误包数
    */
  private Long rxErrors;
  /**
    *
    * 系统接收时丢弃的包数，未进入系统内核
    */
  private Long rxDropped;
  /**
    *
    * 网卡接收时丢弃的包数，未进入网卡缓存
    */
  private Long rxOverruns;
  /**
    *
    * 网卡接收最大巨帧
    */
  private Long rxFrame;
  /**
    *
    * 发送的总字节数
    */
  private Long txBytes;
  /**
    *
    * 发送的总包裹数
    */
  private Long txPackets;
  /**
    *
    * 发送的错误包数
    */
  private Long txErrors;
  /**
    *
    * 系统发送时丢弃的包数，未进入系统内核
    */
  private Long txDropped;
  /**
    *
    * 网卡发送时丢弃的包数，未进入网卡缓存
    */
  private Long txOverruns;
  /**
    *
    *
    */
  private Long txCollisions;
  /**
    *
    *
    */
  private Long txCarrier;
  /**
    *
    *
    */
  private Long speed;
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
    if (!(obj instanceof HostEthernetStatEntity)) {
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
