package pwd.initializr.common.web.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.common.web.api.vo@ms-web-initializr
 *
 * <h1>API统一范围查询输入结构声明</h1>
 *
 * date 2020-08-08 19:44
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(value = "scopeInput", description = "条件查询输入")
public class ScopeInput implements Serializable {

  @ApiModelProperty(name = "fieldName", value = "指定查询字段", required = false, example = "fieldName")
  private String fieldName;
  @ApiModelProperty(name = "fieldValue", value = "指定查询目标", required = false, example = "fieldValue")
  private String fieldValue;
  @ApiModelProperty(name = "hit", value = "指定查询方式[E:精确,AL:前后模糊,LL:左模糊,RL:右模糊,S:范围]", required = false, example = "RL")
  private String hit = "RL";
  @ApiModelProperty(name = "start", value = "范围查询起始值，区间包含", required = false, example = "0")
  private String start;
  @ApiModelProperty(name = "end", value = "范围查询结束值，区间包含", required = false, example = "0")
  private String end;

  @Override
  public int hashCode() {
    return fieldName.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof ScopeInput)) {
      return false;
    }

    ScopeInput scopeInput = (ScopeInput) obj;
    if (scopeInput.getFieldName() == null) {
      return false;
    }
    if (!scopeInput.getFieldName().equals(fieldName)) {
      return false;
    }

    return true;
  }
}
