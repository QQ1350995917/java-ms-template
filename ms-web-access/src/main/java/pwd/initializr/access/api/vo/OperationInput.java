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
 * date 2021-02-22 22:48
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
@ApiModel(value = "OperationInput", description = "Operation请求参数")
public class OperationInput implements Serializable {

   /**
    * 
    * 所属菜单
    */
   @ApiModelProperty(name = "mid", value = "所属菜单", required = true, example = "")
   @Digits(integer = 19, fraction = 0, message = "mid须为整数")
   private Long mid;

   /**
    * 
    * 按钮名称
    */
   @ApiModelProperty(name = "name", value = "按钮名称", required = true, example = "")
   @NotBlank(message = "name不能为空")
   private String name;

   /**
    * 
    * 权限类别；C：创建；U：更新；R：读取；D：删除
    */
   @ApiModelProperty(name = "curd", value = "权限类别；C：创建；U：更新；R：读取；D：删除", required = true, example = "")
   @NotBlank(message = "curd不能为空")
   private String curd;

   /**
    * 
    * 资源路径
    */
   @ApiModelProperty(name = "path", value = "资源路径", required = true, example = "")
   @NotBlank(message = "path不能为空")
   private String path;

   /**
    * 
    * 简介
    */
   @ApiModelProperty(name = "summary", value = "简介", required = false, example = "")
   private String summary;

   /**
    * 
    * 排序
    */
   @ApiModelProperty(name = "order", value = "排序", required = true, example = "")
   @Digits(integer = 10, fraction = 0, message = "order须为整数")
   private Integer order;

}
