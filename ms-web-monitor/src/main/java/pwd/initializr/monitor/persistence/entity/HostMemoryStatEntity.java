package pwd.initializr.monitor.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>host_memory_stat数据表实体类</h2>
 * date 2020-10-29 11:44
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class HostMemoryStatEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
    * 
    * 逻辑组名
    */
  private String groupName;
  /**
    * 
    * 主机名
    */
  private String nodeName;
  /**
    * 
    * 
    */
  private Long memTotal;
  /**
    * 
    * 
    */
  private Long memFree;
  /**
    * 
    * 
    */
  private Long memAvailable;
  /**
    * 
    * 
    */
  private Long buffers;
  /**
    * 
    * 
    */
  private Long cached;
  /**
    * 
    * 
    */
  private Long swapCached;
  /**
    * 
    * 
    */
  private Long active;
  /**
    * 
    * 
    */
  private Long inactive;
  /**
    * 
    * 
    */
  private Long activeAnon;
  /**
    * 
    * 
    */
  private Long inactiveAnon;
  /**
    * 
    * 
    */
  private Long activeFile;
  /**
    * 
    * 
    */
  private Long inactiveFile;
  /**
    * 
    * 
    */
  private Long unevictable;
  /**
    * 
    * 
    */
  private Long mlocked;
  /**
    * 
    * 
    */
  private Long swapTotal;
  /**
    * 
    * 
    */
  private Long swapFree;
  /**
    * 
    * 
    */
  private Long dirty;
  /**
    * 
    * 
    */
  private Long writeback;
  /**
    * 
    * 
    */
  private Long anonPages;
  /**
    * 
    * 
    */
  private Long mapped;
  /**
    * 
    * 
    */
  private Long shmem;
  /**
    * 
    * 
    */
  private Long slab;
  /**
    * 
    * 
    */
  private Long sReclaimable;
  /**
    * 
    * 
    */
  private Long sUnreclaim;
  /**
    * 
    * 
    */
  private Long kernelStack;
  /**
    * 
    * 
    */
  private Long pageTables;
  /**
    * 
    * 
    */
  private Long nFsunstable;
  /**
    * 
    * 
    */
  private Long bounce;
  /**
    * 
    * 
    */
  private Long writebackTmp;
  /**
    * 
    * 
    */
  private Long commitLimit;
  /**
    * 
    * 
    */
  private Long committedAs;
  /**
    * 
    * 
    */
  private Long vmallocTotal;
  /**
    * 
    * 
    */
  private Long vmallocUsed;
  /**
    * 
    * 
    */
  private Long vmallocChunk;
  /**
    * 
    * 
    */
  private Long hardwareCorrupted;
  /**
    * 
    * 
    */
  private Long anonHugePages;
  /**
    * 
    * 
    */
  private Long hugePagesTotal;
  /**
    * 
    * 
    */
  private Long hugePagesFree;
  /**
    * 
    * 
    */
  private Long hugePagesRsvd;
  /**
    * 
    * 
    */
  private Long hugePagesSurp;
  /**
    * 
    * 
    */
  private Long hugePagesize;
  /**
    * 
    * 
    */
  private Long directMap4k;
  /**
    * 
    * 
    */
  private Long directMap2m;
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
    if (!(obj instanceof HostMemoryStatEntity)) {
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
