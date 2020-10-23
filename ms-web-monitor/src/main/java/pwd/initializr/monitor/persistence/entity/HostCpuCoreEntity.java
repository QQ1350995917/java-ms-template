package pwd.initializr.monitor.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>host_cpu_core数据表实体类</h2>
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
public class HostCpuCoreEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
    * 主键
    * 主机名称
    */
  private String id;
  /**
    * 主键
    * 核心编号
    */
  private String index;
  /**
    *
    * CPU生产商
    */
  private String vendor;
  /**
    *
    * CPU类别
    */
  private String model;
  /**
    *
    * CPU缓存数量
    */
  private Long cacheSize;
  /**
    *
    * CPU的总量MHz
    */
  private Integer mhz;
  /**
    *
    * 总核心数
    */
  private Integer totalCores;
  /**
    *
    * 主板总路数
    */
  private Integer totalSockets;
  /**
    *
    * 主板每路支持最大核心数
    */
  private Integer coresPerSocket;
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
    if (!(obj instanceof HostCpuCoreEntity)) {
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
