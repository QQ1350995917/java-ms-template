package pwd.initializr.monitor.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>host_cpu数据表实体类</h2>
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
public class HostCpuEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
    * 主键
    * 主机名称
    */
  private String id;
  /**
    *
    * 执行用户进程的耗时，包括ni耗时
    */
  private Long user;
  /**
    *
    * 内核运行耗时，包括IRQ和softirq耗时
    */
  private Long sys;
  /**
    *
    * 调整进程优先级耗时
    */
  private Long nice;
  /**
    *
    * 空闲期
    */
  private Long idle;
  /**
    *
    * 等待I/O操作完成耗时
    */
  private Long wait;
  /**
    *
    * 处理硬中断耗时
    */
  private Long irq;
  /**
    *
    * 处理软中断耗时
    */
  private Long softIrq;
  /**
    *
    * 等待虚拟CPU的耗时，此时hypervisor在为另一个虚拟处理器服务
    */
  private Long stolen;
  /**
    *
    * 总使用量
    */
  private Long total;
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
    if (!(obj instanceof HostCpuEntity)) {
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
