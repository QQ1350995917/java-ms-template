package pwd.initializr.organization.api.vo;

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
 * pwd-initializr-organization@ms-web-initializr
 *
 * <h1>请求参数封装</h1>
 *
 * date 2021-02-20 22:30
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
@ApiModel(value = "OrganizationInput", description = "Organization请求参数")
public class OrganizationInput implements Serializable {

   /**
    * 
    * …œº∂◊È÷Ø
    */
   @ApiModelProperty(name = "pid", value = "…œº∂◊È÷Ø", required = true, example = "0")
   @Digits(integer = 19, fraction = 0, message = "pid须为整数")
   private Long pid;

   /**
    * 
    * ◊È÷Ø√˚≥∆
    */
   @ApiModelProperty(name = "name", value = "◊È÷Ø√˚≥∆", required = true, example = "")
   @NotBlank(message = "name不能为空")
   private String name;

   /**
    * 
    * ◊È÷Ølogo
    */
   @ApiModelProperty(name = "logo", value = "◊È÷Ølogo", required = false, example = "")
   private String logo;

   /**
    * 
    * ◊È÷Ø√Ë ˆ
    */
   @ApiModelProperty(name = "description", value = "◊È÷Ø√Ë ˆ", required = false, example = "")
   private String description;

   /**
    * 
    * ◊È÷Øslogan
    */
   @ApiModelProperty(name = "slogan", value = "◊È÷Øslogan", required = false, example = "")
   private String slogan;

   /**
    * 
    * ◊È÷Øµ»º∂
    */
   @ApiModelProperty(name = "level", value = "◊È÷Øµ»º∂", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "level须为整数")
   private Integer level;

   /**
    * 
    * ◊È÷Ø≈≈–Ú
    */
   @ApiModelProperty(name = "sort", value = "◊È÷Ø≈≈–Ú", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "sort须为整数")
   private Integer sort;

   /**
    * 
    * ◊È÷Ø≥…‘± ˝¡ø
    */
   @ApiModelProperty(name = "members", value = "◊È÷Ø≥…‘± ˝¡ø", required = true, example = "1")
   @Digits(integer = 10, fraction = 0, message = "members须为整数")
   private Integer members;

   /**
    * 
    * ◊È÷Ø…Û∫ÀΩ¯∂»
    */
   @ApiModelProperty(name = "progress", value = "◊È÷Ø…Û∫ÀΩ¯∂»", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "progress须为整数")
   private Integer progress;

}
