package pwd.initializr.edu.persistence.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>reading_question数据表实体类</h2>
 * date 2021-02-18 15:15
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ReadingQuestionEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
    * 主键
    * ID
    */
  private Long id;
  /**
    * 
    * 上级ID
    */
  private Long pid;
  /**
    * 
    * 标签名称
    */
  private String title;
  /**
    * 
    * 正确答案
    */
  private String a;
  /**
    * 
    * 备选答案
    */
  private String a1;
  /**
    * 
    * 备选答案
    */
  private String a2;
  /**
    * 
    * 备选答案
    */
  private String a3;
  /**
    * 
    * 备选答案
    */
  private String a4;
  /**
    * 
    * 备选答案
    */
  private String a5;
  /**
    * 
    * 可用性状态，0：不可用；1：可用；
    */
  private Integer able;
  /**
    * 
    * 删除状态，0：未删除，1：已删除；
    */
  private Integer del;
  /**
    * 
    * 创建时间
    */
  private Date createTime;
  /**
    * 
    * 修改时间
    */
  private Date updateTime;

  @Override
  public boolean equals(Object obj) {
    // fixme: 视情况而定是否修改或删除equals
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof ReadingQuestionEntity)) {
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
