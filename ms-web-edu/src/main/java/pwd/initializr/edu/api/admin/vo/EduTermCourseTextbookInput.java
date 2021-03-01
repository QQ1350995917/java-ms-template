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
@ApiModel(value = "EduTermCourseTextbookInput", description = "EduTermCourseTextbook请求参数")
public class EduTermCourseTextbookInput implements Serializable {

   /**
    * 
    * 外键，course表
    */
   @ApiModelProperty(name = "cid", value = "外键，course表", required = false, example = "")
   private Long cid;

   /**
    * 
    * 外键，term表
    */
   @ApiModelProperty(name = "tid", value = "外键，term表", required = false, example = "")
   private Long tid;

   /**
    * 
    * 教材名称
    */
   @ApiModelProperty(name = "name", value = "教材名称", required = false, example = "")
   private String name;

   /**
    * 
    * 出版社名称
    */
   @ApiModelProperty(name = "publisher", value = "出版社名称", required = false, example = "")
   private String publisher;

}
