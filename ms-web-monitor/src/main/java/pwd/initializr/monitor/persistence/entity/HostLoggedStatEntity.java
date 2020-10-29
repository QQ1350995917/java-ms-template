package pwd.initializr.monitor.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>host_logged_stat数据表实体类</h2>
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
public class HostLoggedStatEntity implements Serializable {

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
    * 登录用户名称
    */
  private String user;
  /**
    * 
    * 登录用户使用的终端名
    */
  private String tty;
  /**
    * 
    * 登录用户来源的主机名或IP地址
    */
  private String from;
  /**
    * 
    * 用户登录时长
    */
  private String login;
  /**
    * 
    * 自用户上一次与终端进行交互以来的空闲时间
    */
  private String idle;
  /**
    * 
    * 用户当前进程所用的时间
    */
  private String jcpu;
  /**
    * 
    * 用户当前的进程及选项/参数
    */
  private String pcpu;
  /**
    * 
    * 用户当前的进程及选项
    */
  private String what;
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
    if (!(obj instanceof HostLoggedStatEntity)) {
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
