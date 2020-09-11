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
 * <h1>响应参数封装</h1>
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
@ApiModel(value = "${className}Input", description = "${className}响应参数")
public class ${className}Output implements Serializable {

<#if columns?exists>
 <#list columns as column>
  /**
   * <#if (column.key)>主键<#assign hasKey=true/><#assign keyName='${column.javaName}'/></#if>
   * ${column.comment!}
   */
  @ApiModelProperty(name = "${column.javaName}", value = "${column.comment!}", <#if column.nullable == 'NO'>required = true<#else>required = false</#if>, example = "${column.defaultValue!}")
  <#if column.nullable == 'NO'>
   @NotBlank(message = "${column.javaName}不能为空")
  </#if>
  private ${column.javaType} ${column.javaName};
 </#list>
</#if>
}
