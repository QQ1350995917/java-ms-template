package pwd.initializr.email.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * (EmailEntity)实体类
 *
 * @author makejava
 * @since 2020-07-18 22:19:55
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Document(collection = "article")
public class EmailEntity implements Serializable {

  private static final long serialVersionUID = 864136679056019403L;
  /**
   * 主键
   */
  @Field("_id")
  private Long id;
  /**
   * 发邮件的服务
   */
  @Field("app")
  private String app;

  /**
   * 发送人
   */
  @Field("from")
  private String from;

  /**
   * 收件人
   */
  @Field("to")
  private String to;

  /**
   * 抄送人
   */
  @Field("cc")
  private String cc;

  /**
   * 密送人
   */
  @Field("bcc")
  private String bcc;

  /**
   * 主题
   */
  @Field("subject")
  private String subject;

  /**
   * 内容
   */
  @Field("content")
  private String content;

  /**
   * 附件内容
   */
  @Field("attachments")
  private List<EmailAttachmentEntity> attachments;

  /**
   * 扩展内容
   */
  @Field("extend")
  private Map<String,String> extend;

  /**
   * 发送状态：0:发送失败；1：发送成功；
   */
  @Field("sent")
  private Integer sent;
  /**
   * 删除状态：0:未删除；1:已删除
   */
  @Field("del")
  private Integer del;
  /**
   * 数据创建时间
   */
  @Field("ct")
  private Date createTime;
  /**
   * 最近更新时间
   */
  @Field("ut")
  private Date updateTime;
}
