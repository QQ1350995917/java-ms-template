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
 * pwd-initializr-edu-20210218151557967@ms-web-initializr
 *
 * <h1>请求参数封装</h1>
 *
 * date 2021-02-18 15:15
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
@ApiModel(value = "ReadingQuestionInput", description = "ReadingQuestion请求参数")
public class ReadingQuestionInput implements Serializable {

   /**
    *
    * 上级ID
    */
   @ApiModelProperty(name = "pid", value = "上级ID", required = true, example = "")
   @Digits(integer = 19, fraction = 0, message = "pid须为整数")
   private Long pid;

   /**
    *
    * 标签名称
    */
   @ApiModelProperty(name = "title", value = "标签名称", required = true, example = "")
   @NotBlank(message = "title不能为空")
   private String title;

   /**
    *
    * 正确答案
    */
   @ApiModelProperty(name = "a", value = "正确答案", required = false, example = "")
   private String a;

   /**
    *
    * 备选答案
    */
   @ApiModelProperty(name = "a1", value = "备选答案", required = false, example = "")
   private String a1;

   /**
    *
    * 备选答案
    */
   @ApiModelProperty(name = "a2", value = "备选答案", required = false, example = "")
   private String a2;

   /**
    *
    * 备选答案
    */
   @ApiModelProperty(name = "a3", value = "备选答案", required = false, example = "")
   private String a3;

   /**
    *
    * 备选答案
    */
   @ApiModelProperty(name = "a4", value = "备选答案", required = false, example = "")
   private String a4;

   /**
    *
    * 备选答案
    */
   @ApiModelProperty(name = "a5", value = "备选答案", required = false, example = "")
   private String a5;

}
