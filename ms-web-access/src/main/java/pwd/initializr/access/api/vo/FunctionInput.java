package pwd.initializr.access.api.vo;

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
 * pwd-initializr-access@ms-web-initializr
 *
 * <h1>请求参数封装</h1>
 *
 * date 2021-08-08 15:20
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
@ApiModel(value = "FunctionInput", description = "Function请求参数")
public class FunctionInput implements Serializable {

   /**
    * 
    * 权限名称
    */
   @ApiModelProperty(name = "name", value = "权限名称", required = true, example = "")
   @NotBlank(message = "name不能为空")
   private String name;

   /**
    * 
    * 资源URI
    */
   @ApiModelProperty(name = "uri", value = "资源URI", required = true, example = "")
   @NotBlank(message = "uri不能为空")
   private String uri;

   /**
    * 
    * 请求方式
    */
   @ApiModelProperty(name = "method", value = "请求方式", required = true, example = "")
   @NotBlank(message = "method不能为空")
   private String method;

   /**
    * 
    * 描述
    */
   @ApiModelProperty(name = "summary", value = "描述", required = true, example = "")
   @NotBlank(message = "summary不能为空")
   private String summary;

}
