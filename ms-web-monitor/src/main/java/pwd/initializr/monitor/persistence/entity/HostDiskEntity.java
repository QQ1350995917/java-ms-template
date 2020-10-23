package pwd.initializr.monitor.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>host_disk数据表实体类</h2>
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
public class HostDiskEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
    * 主键
    * 主机名称
    */
  private String id;
  /**
    * 主键
    * 磁盘名称
    */
  private String devName;
  /**
    *
    * 磁盘挂载路径
    */
  private String dirName;
  /**
    *
    * 本地硬盘、光驱、网络文件系统等
    */
  private String typeName;
  /**
    *
    * FAT32、NTFS
    */
  private String sysTypeName;
  /**
    *
    * 磁盘选项
    */
  private String options;
  /**
    *
    * 磁盘类型，0：UNKNOWN；1：NONE；2：LOCAL_DISK；3：NETWORK；4：RAM_DISK；5：CDROM；6：SWAP；
    */
  private Integer type;
  /**
    *
    * 磁盘标记
    */
  private Long flags;
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
    if (!(obj instanceof HostDiskEntity)) {
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
