package pwd.initializr.email.persistence.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;

/**
 * <h2>email_extend数据表实体类</h2>
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
public class EmailExtendEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
    * 主键
    * 主键
    */
  private Long id;
  /**
    *
    * email表外键
    */
  private Long emailId;
  /**
    *
    * 扩展内容的key值
    */
  private String key;
  /**
    *
    * 扩展内容的value值
    */
  private String value;
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
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof EmailExtendEntity)) {
      return false;
    }
    EmailExtendEntity entity = (EmailExtendEntity) obj;
    if (StringUtils.equalsIgnoreCase(this.getKey(),entity.getKey())) {
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    if (StringUtils.isNotBlank(this.getKey())) {
      return this.getKey().hashCode();
    }
    return super.hashCode();
  }
}
