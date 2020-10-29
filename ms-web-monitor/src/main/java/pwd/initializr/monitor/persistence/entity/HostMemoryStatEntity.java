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
  private Integer memTotal;
  /**
    * 
    * 
    */
  private Integer memFree;
  /**
    * 
    * 
    */
  private Integer memAvailable;
  /**
    * 
    * 
    */
  private Integer buffers;
  /**
    * 
    * 
    */
  private Integer cached;
  /**
    * 
    * 
    */
  private Integer swapCached;
  /**
    * 
    * 
    */
  private Integer active;
  /**
    * 
    * 
    */
  private Integer inactive;
  /**
    * 
    * 
    */
  private Integer activeAnon;
  /**
    * 
    * 
    */
  private Integer inactiveAnon;
  /**
    * 
    * 
    */
  private Integer activeFile;
  /**
    * 
    * 
    */
  private Integer inactiveFile;
  /**
    * 
    * 
    */
  private Integer unevictable;
  /**
    * 
    * 
    */
  private Integer mlocked;
  /**
    * 
    * 
    */
  private Integer swapTotal;
  /**
    * 
    * 
    */
  private Integer swapFree;
  /**
    * 
    * 
    */
  private Integer dirty;
  /**
    * 
    * 
    */
  private Integer writeback;
  /**
    * 
    * 
    */
  private Integer anonPages;
  /**
    * 
    * 
    */
  private Integer mapped;
  /**
    * 
    * 
    */
  private Integer shmem;
  /**
    * 
    * 
    */
  private Integer slab;
  /**
    * 
    * 
    */
  private Integer sReclaimable;
  /**
    * 
    * 
    */
  private Integer sUnreclaim;
  /**
    * 
    * 
    */
  private Integer kernelStack;
  /**
    * 
    * 
    */
  private Integer pageTables;
  /**
    * 
    * 
    */
  private Integer nFsunstable;
  /**
    * 
    * 
    */
  private Integer bounce;
  /**
    * 
    * 
    */
  private Integer writebackTmp;
  /**
    * 
    * 
    */
  private Integer commitLimit;
  /**
    * 
    * 
    */
  private Integer committedAs;
  /**
    * 
    * 
    */
  private Integer vmallocTotal;
  /**
    * 
    * 
    */
  private Integer vmallocUsed;
  /**
    * 
    * 
    */
  private Integer vmallocChunk;
  /**
    * 
    * 
    */
  private Integer hardwareCorrupted;
  /**
    * 
    * 
    */
  private Integer anonHugePages;
  /**
    * 
    * 
    */
  private Integer hugePagesTotal;
  /**
    * 
    * 
    */
  private Integer hugePagesFree;
  /**
    * 
    * 
    */
  private Integer hugePagesRsvd;
  /**
    * 
    * 
    */
  private Integer hugePagesSurp;
  /**
    * 
    * 
    */
  private Integer hugePagesize;
  /**
    * 
    * 
    */
  private Integer directMap4k;
  /**
    * 
    * 
    */
  private Integer directMap2m;
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
