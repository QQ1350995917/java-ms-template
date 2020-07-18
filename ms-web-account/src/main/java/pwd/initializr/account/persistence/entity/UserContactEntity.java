package pwd.initializr.account.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * (UserContactEntity)实体类
 *
 * @author makejava
 * @since 2020-07-18 22:35:25
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class UserContactEntity implements Serializable {

  private static final long serialVersionUID = 917623373019849115L;
  /**
   * 自增主键
   */
  private Long id;
  /**
   * 逻辑外键:admin_user.id
   */
  private Long uid;
  /**
   * 1:电话号码；2：地址；3：邮箱；4：微信；5：微博；6：QQ；
   */
  private Integer type;
  /**
   * Type字段对应的值
   */
  private String value;
  /**
   * 描述
   */
  private String mark;
  /**
   * 可用状态：0:不可用；1:可用
   */
  private Integer enable;
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

}