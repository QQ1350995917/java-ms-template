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
 * pwd-initializr-organization-20210220185831978@ms-web-initializr
 *
 * <h1>请求参数封装</h1>
 *
 * date 2021-02-20 18:58
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
    * 上级组织
    */
   @ApiModelProperty(name = "pid", value = "上级组织", required = true, example = "0")
   @Digits(integer = 19, fraction = 0, message = "pid须为整数")
   private Long pid;

   /**
    * 
    * 组织名称
    */
   @ApiModelProperty(name = "name", value = "组织名称", required = true, example = "")
   @NotBlank(message = "name不能为空")
   private String name;

   /**
    * 
    * 组织logo
    */
   @ApiModelProperty(name = "logo", value = "组织logo", required = false, example = "")
   private String logo;

   /**
    * 
    * 组织描述
    */
   @ApiModelProperty(name = "description", value = "组织描述", required = false, example = "")
   private String description;

   /**
    * 
    * 组织slogan
    */
   @ApiModelProperty(name = "slogan", value = "组织slogan", required = false, example = "")
   private String slogan;

   /**
    * 
    * 组织等级
    */
   @ApiModelProperty(name = "level", value = "组织等级", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "level须为整数")
   private Integer level;

   /**
    * 
    * 组织排序
    */
   @ApiModelProperty(name = "sort", value = "组织排序", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "sort须为整数")
   private Integer sort;

   /**
    * 
    * 组织成员数量
    */
   @ApiModelProperty(name = "members", value = "组织成员数量", required = true, example = "1")
   @Digits(integer = 10, fraction = 0, message = "members须为整数")
   private Integer members;

   /**
    * 
    * 组织审核进度
    */
   @ApiModelProperty(name = "progress", value = "组织审核进度", required = true, example = "0")
   @Digits(integer = 10, fraction = 0, message = "progress须为整数")
   private Integer progress;

}
