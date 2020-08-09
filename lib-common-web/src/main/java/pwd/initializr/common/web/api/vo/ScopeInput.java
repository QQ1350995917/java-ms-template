package pwd.initializr.common.web.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
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
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class ScopeInput<T> implements Serializable {

  @ApiModelProperty(name = "fieldName", value = "指定查询字段", required = false, example = "fieldName")
  private String fieldName;
  @ApiModelProperty(name = "fieldValue", value = "指定查询目标", required = false, example = "fieldValue")
  private T fieldValue;
  @ApiModelProperty(name = "hit", value = "指定查询方式[E:精确,AL:前后模糊,LL:左模糊,RL:右模糊,S:范围]", required = false, example = "RL")
  private String hit = "RL";
  @ApiModelProperty(name = "start", value = "范围查询起始值，区间包含", required = false, example = "0")
  private T start;
  @ApiModelProperty(name = "end", value = "范围查询结束值，区间包含", required = false, example = "0")
  private T end;
}
