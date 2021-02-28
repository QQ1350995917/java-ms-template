package pwd.initializr.edu.api.admin.vo;

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

/**
 * pwd-initializr-edu@ms-web-initializr
 *
 * <h1>请求参数封装</h1>
 *
 * date 2021-02-28 22:45
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
@ApiModel(value = "EduTermInput", description = "EduTerm请求参数")
public class EduTermInput implements Serializable {

   /**
    * 
    * 父节点ID
    */
   @ApiModelProperty(name = "pid", value = "父节点ID", required = false, example = "")
   private Long pid;

   /**
    * 
    * 中文名
    */
   @ApiModelProperty(name = "zhcnName", value = "中文名", required = true, example = "")
   @NotBlank(message = "zhcnName不能为空")
   private String zhcnName;

   /**
    * 
    * 英文名
    */
   @ApiModelProperty(name = "enusName", value = "英文名", required = true, example = "")
   @NotBlank(message = "enusName不能为空")
   private String enusName;

   /**
    * 
    * 学校的
    */
   @ApiModelProperty(name = "scholastic", value = "学校的", required = true, example = "")
   @NotBlank(message = "scholastic不能为空")
   private String scholastic;

   /**
    * 
    * 排序
    */
   @ApiModelProperty(name = "order", value = "排序", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "order须为整数")
   private Integer order;

   /**
    * 
    * 叶子节点，0：否；1是
    */
   @ApiModelProperty(name = "leaf", value = "叶子节点，0：否；1是", required = false, example = "")
   private Integer leaf;

}
