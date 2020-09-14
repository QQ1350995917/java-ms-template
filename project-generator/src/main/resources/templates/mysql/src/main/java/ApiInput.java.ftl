package ${projectPackage}.api.vo;

<#if columns?exists>
 <#list columns as column>
  <#if (column.javaType?lower_case = 'date')>
   <#assign importDate="import java.util.Date;"/>
  </#if>
  <#if (column.javaType?lower_case = 'bigdecimal')>
   <#assign importBigDecimal="import java.math.BigDecimal;"/>
  </#if>
 </#list>
</#if>
<#if (importBigDecimal)??>
${importBigDecimal!''}
</#if>
<#if (importDate)??>
${importDate!''}
</#if>
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
 * ${projectName}@ms-web-initializr
 *
 * <h1>请求参数封装</h1>
 *
 * date ${projectCreateDate}
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @version ${projectVersion}
 * @since ${projectVersion}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(value = "${className}Input", description = "${className}请求参数")
public class ${className}Input implements Serializable {

<#if columns?exists>
 <#list columns as column>
  <#if (column.key || column.javaName = 'createTime' || column.javaName = 'updateTime' || column.javaName = 'able' || column.javaName = 'del' || column.javaName = 'version')>
  <#else>
   /**
    * <#if (column.key)>主键<#assign hasKey=true/><#assign keyName='${column.javaName}'/></#if>
    * ${column.comment!}
    */
   @ApiModelProperty(name = "${column.javaName}", value = "${column.comment!}", <#if column.nullable == 'NO'>required = true<#else>required = false</#if>, example = "${column.defaultValue!}")
   <#if (column.nullable == 'NO' && column.javaType = "String")>
   @NotBlank(message = "${column.javaName}不能为空")
   </#if>
   <#if (column.nullable == 'NO' && column.javaType = "Long")>
   @Digits(integer = 19, fraction = 0, message = "${column.javaName}须为整数")
   </#if>
   <#if (column.nullable == 'NO' && column.javaType = "Integer")>
   @Digits(integer = 10, fraction = 0, message = "${column.javaName}须为整数")
   </#if>
   <#if (column.nullable == 'NO' && column.javaType = "Short")>
   @Digits(integer = 5, fraction = 0, message = "${column.javaName}须为整数")
   </#if>
   <#if (column.nullable == 'NO' && column.javaType = "Byte")>
   @Digits(integer = 3, fraction = 0, message = "${column.javaName}须为整数")
   </#if>
   <#if (column.nullable == 'NO' && column.javaType = "Double")>
   @Digits(integer = 19, fraction = 16, message = "${column.javaName}须为小数")
   </#if>
   <#if (column.nullable == 'NO' && column.javaType = "Float")>
   @Digits(integer = 10, fraction = 6, message = "${column.javaName}须为小数")
   </#if>
   private ${column.javaType} ${column.javaName};

  </#if>
 </#list>
</#if>
}
