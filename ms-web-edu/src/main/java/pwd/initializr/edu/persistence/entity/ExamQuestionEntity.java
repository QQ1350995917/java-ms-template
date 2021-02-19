package pwd.initializr.edu.persistence.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>exam_question数据表实体类</h2>
 * date 2021-02-19 14:59
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since 0.0.1-SNAPSHOT
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ExamQuestionEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
    * 主键
    * 
    */
  private Long id;
  /**
    * 
    * 
    */
  private Long pid;
  /**
    * 
    * 
    */
  private String sysOrgCode;
  /**
    * 
    * 
    */
  private String sysCompanyCode;
  /**
    * 
    * 
    */
  private String bpmStatus;
  /**
    * 
    * 
    */
  private String title;
  /**
    * 
    * 
    */
  private String knowPoint;
  /**
    * 
    * 
    */
  private String remark;
  /**
    * 
    * 
    */
  private String choiceA;
  /**
    * 
    * 
    */
  private String choiceB;
  /**
    * 
    * 
    */
  private String choiceC;
  /**
    * 
    * 
    */
  private String choiceD;
  /**
    * 
    * 
    */
  private String choiceE;
  /**
    * 
    * 
    */
  private String answer;
  /**
    * 
    * 
    */
  private String studyLevel;
  /**
    * 
    * 
    */
  private String examType;
  /**
    * 
    * 
    */
  private String qusType;
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
    if (!(obj instanceof ExamQuestionEntity)) {
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
