package pwd.initializr.email.persistence.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>email_content数据表实体类</h2>
 * date 2020-12-14 16:13
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class EmailContentEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
    * 主键
    * email表外键，同时作为主键
    */
  private Long emailId;
  /**
    * 
    * 邮件标题
    */
  private String subject;
  /**
    * 
    * 邮件内容
    */
  private String content;
  /**
    * 
    * 创建时间
    */
  private Date createTime;
  /**
    * 
    * 更新时间
    */
  private Date updateTime;

  @Override
  public boolean equals(Object obj) {
    // fixme: 视情况而定是否修改或删除equals
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof EmailContentEntity)) {
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
