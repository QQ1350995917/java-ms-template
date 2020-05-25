package pwd.initializr.account.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * (AdminEntity)实体类
 *
 * @author makejava
 * @since 2020-04-25 16:18:28
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminEntity implements Serializable {

  private static final long serialVersionUID = -54153826190401267L;
  /**
   * 主键
   */
  private Long id;
  /**
   * 登录名
   */
  private String loginName;
  /**
   * 登录密码
   */
  private String loginPassword;
  /**
   * 姓名
   */
  private String name;
  /**
   * 性别
   */
  private String gender;
  /**
   * 简介
   */
  private String summary;
  /**
   * 等级；0运维，1超管，2普通
   */
  private String level;

  private Integer ableStatus;

  private Integer delStatus;
  /**
   * 首次创建时间
   */
  private Date createTime;
  /**
   * 最近更新时间
   */
  private Date updateTime;


}