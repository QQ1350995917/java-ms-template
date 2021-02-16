package pwd.initializr.edu.persistence.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>grammar_book_exam数据表实体类</h2>
 * date 2021-02-15 22:28
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class GrammarBookExamEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
    * 主键
    * 
    */
  private Long editionId;
  /**
    * 主键
    * 
    */
  private Long unitId;
  /**
    * 主键
    * 
    */
  private Long id;
  /**
    * 
    * 
    */
  private String bpmStatus;
  /**
    * 
    * 
    */
  private String diff;
  /**
    * 
    * 
    */
  private String grade;
  /**
    * 
    * 
    */
  private String qusTitle;
  /**
    * 
    * 
    */
  private String category;
  /**
    * 
    * 
    */
  private String comments;
  /**
    * 
    * 
    */
  private String remark;
  /**
    * 
    * 
    */
  private String bread;
  /**
    * 
    * 
    */
  private String topic;
  /**
    * 
    * 
    */
  private String answerA;
  /**
    * 
    * 
    */
  private String answerB;
  /**
    * 
    * 
    */
  private String answerC;
  /**
    * 
    * 
    */
  private String answerD;
  /**
    * 
    * 
    */
  private String rightAnswer;
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
    if (!(obj instanceof GrammarBookExamEntity)) {
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
