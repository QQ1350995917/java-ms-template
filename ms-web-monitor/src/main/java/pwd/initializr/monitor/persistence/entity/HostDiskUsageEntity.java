package pwd.initializr.monitor.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>host_disk_usage数据表实体类</h2>
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
public class HostDiskUsageEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
    *
    * 主机名称
    */
  private String id;
  /**
    *
    * 设备名称
    */
  private String devName;
  /**
    *
    * 文件系统总大小
    */
  private Long total;
  /**
    *
    * 剩余大小
    */
  private Long free;
  /**
    *
    * 已经使用量
    */
  private Long used;
  /**
    *
    * 可用大小
    */
  private Long avail;
  /**
    *
    *
    */
  private Long files;
  /**
    *
    *
    */
  private Long freeFiles;
  /**
    *
    *
    */
  private Long diskReads;
  /**
    *
    *
    */
  private Long diskWrites;
  /**
    *
    *
    */
  private Long diskReadBytes;
  /**
    *
    *
    */
  private Long diskWriteBytes;
  /**
    *
    *
    */
  private Long diskQueue;
  /**
    *
    *
    */
  private Long diskServiceTime;
  /**
    *
    * 资源的利用率
    */
  private Long usePercent;
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
    if (!(obj instanceof HostDiskUsageEntity)) {
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
