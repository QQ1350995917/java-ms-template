package pwd.initializr.edu.persistence.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>grammar_content数据表实体类</h2>
 * date 2021-02-14 21:34
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class GrammarContentEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
    * 主键
    * 数据ID
    */
  private Long id;
  /**
    * 
    * 课文内容
    */
  private String text;
  /**
    * 
    * 线上地址
    */
  private String url;
  /**
    * 
    * 
    */
  private String remark;
  /**
    * 
    * 存储路径
    */
  private String path;
  /**
    * 
    * 
    */
  private Integer able;
  /**
    * 
    * 
    */
  private Integer del;
  /**
    * 
    * 
    */
  private Date createTime;
  /**
    * 
    * 
    */
  private Date updateTime;

  @Override
  public boolean equals(Object obj) {
    // fixme: 视情况而定是否修改或删除equals
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof GrammarContentEntity)) {
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
