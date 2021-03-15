package pwd.initializr.search.rpc;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.search.rpc@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-03-15 17:39
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@ApiModel(value = "RPCSearchFieldVO", description = "搜索输入数据属性模型")
public class RPCSearchFieldVO {

  @ApiModelProperty(name = "fieldName", value = "fieldName", required = false, example = "exactly.status", dataType = "java.util.String")
  private String fieldName;
  @ApiModelProperty(name = "fieldValue", value = "fieldValue", required = false, example = "1", dataType = "java.util.String")
  private String fieldValue;
  @ApiModelProperty(name = "type", value = "type", required = false, example = "term|range", dataType = "java.util.String")
  private String type;
  @ApiModelProperty(name = "start", value = "start", required = false, example = "1", dataType = "java.util.String")
  private String start;
  @ApiModelProperty(name = "end", value = "end", required = false, example = "3", dataType = "java.util.String")
  private String end;
  @ApiModelProperty(name = "interval", value = "interval", required = false, example = "open|close", dataType = "java.util.String")
  private String interval;

}
