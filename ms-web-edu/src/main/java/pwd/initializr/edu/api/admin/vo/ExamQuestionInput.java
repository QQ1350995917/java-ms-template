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
 * pwd-initializr-edu-20210219145908119@ms-web-initializr
 *
 * <h1>请求参数封装</h1>
 *
 * date 2021-02-19 14:59
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
@ApiModel(value = "ExamQuestionInput", description = "ExamQuestion请求参数")
public class ExamQuestionInput implements Serializable {

   /**
    *
    *
    */
   @ApiModelProperty(name = "pid", value = "", required = false, example = "")
   private Long pid;

   /**
    *
    *
    */
   @ApiModelProperty(name = "sysOrgCode", value = "", required = false, example = "")
   private String sysOrgCode;

   /**
    *
    *
    */
   @ApiModelProperty(name = "sysCompanyCode", value = "", required = false, example = "")
   private String sysCompanyCode;

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
   @ApiModelProperty(name = "title", value = "", required = false, example = "")
   private String title;

   /**
    *
    *
    */
   @ApiModelProperty(name = "knowPoint", value = "", required = false, example = "")
   private String knowPoint;

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
   @ApiModelProperty(name = "choiceA", value = "", required = false, example = "")
   private String choiceA;

   /**
    *
    *
    */
   @ApiModelProperty(name = "choiceB", value = "", required = false, example = "")
   private String choiceB;

   /**
    *
    *
    */
   @ApiModelProperty(name = "choiceC", value = "", required = false, example = "")
   private String choiceC;

   /**
    *
    *
    */
   @ApiModelProperty(name = "choiceD", value = "", required = false, example = "")
   private String choiceD;

   /**
    *
    *
    */
   @ApiModelProperty(name = "choiceE", value = "", required = false, example = "")
   private String choiceE;

   /**
    *
    *
    */
   @ApiModelProperty(name = "answer", value = "", required = false, example = "")
   private String answer;

   /**
    *
    *
    */
   @ApiModelProperty(name = "studyLevel", value = "", required = false, example = "")
   private String studyLevel;

   /**
    *
    *
    */
   @ApiModelProperty(name = "examType", value = "", required = false, example = "")
   private String examType;

   /**
    *
    *
    */
   @ApiModelProperty(name = "qusType", value = "", required = false, example = "")
   private String qusType;

}
