package pwd.initializr.account.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * (UserUserEntity)实体类
 *
 * @author makejava
 * @since 2020-07-18 22:35:25
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class UserUserEntity implements Serializable {

  private static final long serialVersionUID = -41446625353827807L;
  /**
   * 自增主键
   */
  private Long id;
  /**
   * 身份证号
   */
  private String pin;
  /**
   * 姓名
   */
  private String name;
  /**
   * 性别
   */
  private String gender;
  /**
   * 工号
   */
  private String empNo;
  /**
   * 简介
   */
  private String summary;
  /**
   * 可用性：0:不可用；1:可用
   */
  private Integer able;
  /**
   * 状态：0:未删除；1:已删除
   */
  private Integer del;
  /**
   * 首次创建时间
   */
  private Date createTime;
  /**
   * 最近更新时间
   */
  private Date updateTime;
}