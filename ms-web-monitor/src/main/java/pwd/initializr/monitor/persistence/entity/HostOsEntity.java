package pwd.initializr.monitor.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>host_os数据表实体类</h2>
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
public class HostOsEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
    * 主键
    * 主机名称
    */
  private String id;
  /**
    *
    * 操作系统名称
    */
  private String name;
  /**
    *
    * 操作系统的版本号
    */
  private String version;
  /**
    *
    * 操作系统内核架构如： 386、486、586等x86
    */
  private String arch;
  /**
    *
    * machine
    */
  private String machine;
  /**
    *
    * description
    */
  private String description;
  /**
    *
    * patchLevel
    */
  private String patchLevel;
  /**
    *
    * 操作系统的卖主
    */
  private String vendor;
  /**
    *
    * 操作系统卖主类型
    */
  private String vendorVersion;
  /**
    *
    * 操作系统名称
    */
  private String vendorName;
  /**
    *
    * 卖主名称
    */
  private String vendorCodeName;
  /**
    *
    * 操作系统位数
    */
  private String dataModel;
  /**
    *
    * 操作系统CPU大端/小端
    */
  private String cpuEndian;
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
    if (!(obj instanceof HostOsEntity)) {
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
