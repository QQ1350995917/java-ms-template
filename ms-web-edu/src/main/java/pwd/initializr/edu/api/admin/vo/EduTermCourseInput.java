package pwd.initializr.edu.api.admin.vo;

  import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
  import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd-initializr-edu@ms-web-initializr
 *
 * <h1>请求参数封装</h1>
 *
 * date 2021-03-01 22:01
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
@ApiModel(value = "EduTermCourseInput", description = "EduTermCourse请求参数")
public class EduTermCourseInput implements Serializable {

   /**
    * 
    * 外键，term表
    */
   @ApiModelProperty(name = "tid", value = "外键，term表", required = false, example = "")
   private Long tid;

   /**
    * 
    * 课程名称
    */
   @ApiModelProperty(name = "name", value = "课程名称", required = false, example = "")
   private String name;

   /**
    * 
    * 排序
    */
   @ApiModelProperty(name = "order", value = "排序", required = false, example = "")
   private Integer order;

}
