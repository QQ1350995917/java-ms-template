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
 * <h2>email_attachment数据表实体类</h2>
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
public class EmailAttachmentEntity implements Serializable {

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
    * 附件名称
    */
  private String fileName;
  /**
    *
    * 邮件显示附件关联ID
    */
  private String cid;
  /**
    *
    * 附件MEMI类型
    */
  private String contentType;
  /**
    *
    * 附件地址
    */
  private String url;
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
    if (!(obj instanceof EmailAttachmentEntity)) {
      return false;
    }
    EmailAttachmentEntity entity = (EmailAttachmentEntity) obj;
    if (StringUtils.equals(this.getUrl(),entity.getUrl())) {
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    if (StringUtils.isNotBlank(this.getUrl())) {
      return this.getUrl().hashCode();
    }
    return super.hashCode();
  }
}
