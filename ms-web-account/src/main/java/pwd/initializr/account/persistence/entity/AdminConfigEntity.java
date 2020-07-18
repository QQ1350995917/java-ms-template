package pwd.initializr.account.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * (AdminConfigEntity)实体类
 *
 * @author makejava
 * @since 2020-07-18 22:19:33
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class AdminConfigEntity implements Serializable {

  private static final long serialVersionUID = 229513581655806049L;
  /**
   * 自增主键
   */
  private Long id;
  /**
   * 配置key
   */
  private String key;
  /**
   * 配置value
   */
  private String value;
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


}