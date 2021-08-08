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
@ApiModel(value = "AccessInput", description = "Access请求参数")
public class AccessInput implements Serializable {

   /**
    * 
    * 权限类型
    */
   @ApiModelProperty(name = "type", value = "权限类型", required = true, example = "")
   @Digits(integer = 19, fraction = 0, message = "type须为整数")
   private Long type;

   /**
    * 
    * 权限名称
    */
   @ApiModelProperty(name = "name", value = "权限名称", required = true, example = "")
   @NotBlank(message = "name不能为空")
   private String name;

}
