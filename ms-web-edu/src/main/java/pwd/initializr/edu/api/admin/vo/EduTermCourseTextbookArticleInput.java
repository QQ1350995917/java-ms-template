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
 * pwd-initializr-app@ms-web-initializr
 *
 * <h1>请求参数封装</h1>
 *
 * date 2021-03-08 17:38
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
@ApiModel(value = "EduTermCourseTextbookArticleInput", description = "EduTermCourseTextbookArticle请求参数")
public class EduTermCourseTextbookArticleInput implements Serializable {

   /**
    *
    * 外键，edu_term_course_textbook.id，图书ID
    */
   @ApiModelProperty(name = "pid", value = "外键，edu_term_course_textbook.id，图书ID", required = false, example = "")
   private Long pid;

   /**
    *
    * 标题
    */
   @ApiModelProperty(name = "title", value = "标题", required = false, example = "")
   private String title;

   /**
    *
    * 课文内容
    */
   @ApiModelProperty(name = "text", value = "课文内容", required = true, example = "")
   @NotBlank(message = "text不能为空")
   private String text;

   /**
    *
    * 存储路径
    */
   @ApiModelProperty(name = "mediaPath", value = "存储路径", required = false, example = "")
   private String mediaPath;

}
