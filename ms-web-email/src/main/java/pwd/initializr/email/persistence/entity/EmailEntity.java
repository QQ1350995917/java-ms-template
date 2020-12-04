package pwd.initializr.email.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
public class EmailEntity implements Serializable {

  private static final long serialVersionUID = 864136679056019403L;
  /**
   * 自增主键
   */
  private Long id;
  /**
   * 发邮件的服务
   */
  private String app;

  /**
   * 发送人
   */
  private String from;

  /**
   * 收件人
   */
  private String to;

  /**
   * 抄送人
   */
  private String cc;

  /**
   * 密送人
   */
  private String bcc;

  /**
   * 主题
   */
  private String subject;

  /**
   * 发送状态：0:未发送；1：已发送；
   */
  private Integer sent;
  /**
   * 可用状态：0:不可用；1:可用
   */
  private Integer able;
  /**
   * 删除状态：0:未删除；1:已删除
   */
  private Integer del;
  /**
   * 数据创建时间
   */
  private Date createTime;
  /**
   * 最近更新时间
   */
  private Date updateTime;
  /**
   * 数据版本号
   */
  private Long version;

}
