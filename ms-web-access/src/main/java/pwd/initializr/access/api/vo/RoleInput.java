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
@ApiModel(value = "RoleInput", description = "Role请求参数")
public class RoleInput implements Serializable {

   /**
    * 
    * 上级角色
    */
   @ApiModelProperty(name = "pid", value = "上级角色", required = true, example = "")
   @Digits(integer = 19, fraction = 0, message = "pid须为整数")
   private Long pid;

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
