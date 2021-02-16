package pwd.initializr.edu.api.admin.vo;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

/**
 * pwd-initializr-app-20210215222834917@ms-web-initializr
 *
 * <h1>请求参数封装</h1>
 *
 * date 2021-02-15 22:28
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(value = "GrammarBookExamInput", description = "GrammarBookExam请求参数")
public class GrammarBookExamInput implements Serializable {

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "bpmStatus", value = "", required = false, example = "")
   private String bpmStatus;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "diff", value = "", required = false, example = "")
   private String diff;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "grade", value = "", required = false, example = "")
   private String grade;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "qusTitle", value = "", required = false, example = "")
   private String qusTitle;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "category", value = "", required = false, example = "")
   private String category;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "comments", value = "", required = false, example = "")
   private String comments;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "remark", value = "", required = false, example = "")
   private String remark;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "bread", value = "", required = false, example = "")
   private String bread;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "topic", value = "", required = false, example = "")
   private String topic;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "answerA", value = "", required = false, example = "")
   private String answerA;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "answerB", value = "", required = false, example = "")
   private String answerB;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "answerC", value = "", required = false, example = "")
   private String answerC;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "answerD", value = "", required = false, example = "")
   private String answerD;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "rightAnswer", value = "", required = false, example = "")
   private String rightAnswer;

}
