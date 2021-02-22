package ${projectPackage}.persistence.entity;

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
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>${tableName}数据表实体类</h2>
 * date ${projectCreateDate}
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @since ${projectVersion}
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ${className}Entity implements Serializable {

  private static final long serialVersionUID = 1L;
<#if columns?exists>
  <#list columns as column>
  /**
    * <#if (column.key)>主键<#assign hasKey=true/><#assign keyName='${column.javaName}'/><#else><#assign hasKey=false/></#if>
    * ${column.comment!}
    */
  private ${column.javaType} ${column.javaName};
  </#list>
</#if>

  @Override
  public boolean equals(Object obj) {
    // fixme: 视情况而定是否修改或删除equals
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof ${className}Entity)) {
      return false;
    }
<#if hasKey>
  ${className}Entity entity = (${className}Entity) obj;
    if (entity.get${keyName?cap_first}() == null || !(entity.get${keyName?cap_first}().equals(this.get${keyName?cap_first}()))) {
      return false;
    } else {
      return true;
    }
<#else>
    return super.equals(obj);
</#if>
  }

  @Override
  public int hashCode() {
    // fixme: 视情况而定是否修改或删除hashCode
<#if hasKey>
    return this.get${keyName?cap_first}().hashCode();
<#else>
    return super.hashCode();
</#if>
  }
}
