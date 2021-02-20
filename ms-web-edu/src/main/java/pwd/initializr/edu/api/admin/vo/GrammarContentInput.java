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
 * pwd-initializr-app-20210214213416272@ms-web-initializr
 *
 * <h1>请求参数封装</h1>
 *
 * date 2021-02-14 21:34
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
@ApiModel(value = "GrammarContentInput", description = "GrammarContent请求参数")
public class GrammarContentInput implements Serializable {

   /**
    * 
    * 课文内容
    */
   @ApiModelProperty(name = "text", value = "课文内容", required = true, example = "")
   @NotBlank(message = "text不能为空")
   private String text;

   /**
    * 
    * 线上地址
    */
   @ApiModelProperty(name = "url", value = "线上地址", required = false, example = "")
   private String url;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "remark", value = "", required = false, example = "")
   private String remark;

   /**
    * 
    * 存储路径
    */
   @ApiModelProperty(name = "path", value = "存储路径", required = false, example = "")
   private String path;

}