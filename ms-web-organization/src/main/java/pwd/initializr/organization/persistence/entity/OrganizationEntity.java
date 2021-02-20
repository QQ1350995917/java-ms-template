package pwd.initializr.organization.persistence.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>organization数据表实体类</h2>
 * date 2021-02-20 22:30
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class OrganizationEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
    * 主键
    * ÷˜º¸
    */
  private Long id;
  /**
    * 
    * …œº∂◊È÷Ø
    */
  private Long pid;
  /**
    * 
    * ◊È÷Ø√˚≥∆
    */
  private String name;
  /**
    * 
    * ◊È÷Ølogo
    */
  private String logo;
  /**
    * 
    * ◊È÷Ø√Ë ˆ
    */
  private String description;
  /**
    * 
    * ◊È÷Øslogan
    */
  private String slogan;
  /**
    * 
    * ◊È÷Øµ»º∂
    */
  private Integer level;
  /**
    * 
    * ◊È÷Ø≈≈–Ú
    */
  private Integer sort;
  /**
    * 
    * ◊È÷Ø≥…‘± ˝¡ø
    */
  private Integer members;
  /**
    * 
    * ◊È÷Ø…Û∫ÀΩ¯∂»
    */
  private Integer progress;
  /**
    * 
    * ◊¥Ã¨£¨0Ω˚”√£¨1ø…”√
    */
  private Integer able;
  /**
    * 
    * ◊¥Ã¨£¨0’˝≥££¨1…æ≥˝
    */
  private Integer del;
  /**
    * 
    *  ◊¥Œ¥¥Ω® ±º‰
    */
  private Date createTime;
  /**
    * 
    * ◊ÓΩ¸–ﬁ∏ƒ ±º‰
    */
  private Date updateTime;

  @Override
  public boolean equals(Object obj) {
    // fixme: 视情况而定是否修改或删除equals
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof OrganizationEntity)) {
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
