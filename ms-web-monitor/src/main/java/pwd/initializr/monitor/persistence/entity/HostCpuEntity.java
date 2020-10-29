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
public class HostCpuEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
    * 主键
    * 逻辑组名
    */
  private String groupName;
  /**
    * 主键
    * 主机名
    */
  private String nodeName;
  /**
    * 
    * 
    */
  private String processor;
  /**
    * 
    * 
    */
  private String vendorId;
  /**
    * 
    * 
    */
  private String cpuFamily;
  /**
    * 
    * 
    */
  private String model;
  /**
    * 
    * 
    */
  private String modelName;
  /**
    * 
    * 
    */
  private String stepping;
  /**
    * 
    * 
    */
  private String microcode;
  /**
    * 
    * 
    */
  private String cpuMhz;
  /**
    * 
    * 
    */
  private String cacheSize;
  /**
    * 
    * 
    */
  private String physicalId;
  /**
    * 
    * 
    */
  private String siblings;
  /**
    * 
    * 
    */
  private String coreId;
  /**
    * 
    * 
    */
  private String cpuCores;
  /**
    * 
    * 
    */
  private String apicid;
  /**
    * 
    * 
    */
  private String initialApicid;
  /**
    * 
    * 
    */
  private String fpu;
  /**
    * 
    * 
    */
  private String fpuException;
  /**
    * 
    * 
    */
  private String cpuidLevel;
  /**
    * 
    * 
    */
  private String wp;
  /**
    * 
    * 
    */
  private String flags;
  /**
    * 
    * 
    */
  private String bogomips;
  /**
    * 
    * 
    */
  private String clflushSize;
  /**
    * 
    * 
    */
  private String cacheAlignment;
  /**
    * 
    * 
    */
  private String addressSizes;
  /**
    * 
    * 
    */
  private String powerManagement;
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
