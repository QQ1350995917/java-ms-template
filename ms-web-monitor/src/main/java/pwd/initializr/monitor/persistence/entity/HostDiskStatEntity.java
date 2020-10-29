package pwd.initializr.monitor.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>host_disk_stat数据表实体类</h2>
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
public class HostDiskStatEntity implements Serializable {

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
  private Integer majorDeviceNumber;
  /**
    * 
    * 
    */
  private Integer minorDeviceNumber;
  /**
    * 
    * 
    */
  private String deviceName;
  /**
    * 
    * 
    */
  private Integer read;
  /**
    * 
    * 
    */
  private Integer readMerge;
  /**
    * 
    * 
    */
  private Integer readSector;
  /**
    * 
    * 
    */
  private Integer readSpentMilliseconds;
  /**
    * 
    * 
    */
  private Integer write;
  /**
    * 
    * 
    */
  private Integer writeMerge;
  /**
    * 
    * 
    */
  private Integer writeSector;
  /**
    * 
    * 
    */
  private Integer writeSpentMilliseconds;
  /**
    * 
    * 
    */
  private Integer ioRequest;
  /**
    * 
    * 
    */
  private Integer ioSpentMilliseconds;
  /**
    * 
    * 
    */
  private Integer ioSpentAllMilliseconds;
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
    if (!(obj instanceof HostDiskStatEntity)) {
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
