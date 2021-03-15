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
@ApiModel(value = "RPCSearchFieldSortVO", description = "搜索输入数据排序模型")
public class RPCSearchFieldSortVO {

  @ApiModelProperty(name = "fieldName", value = "fieldName", required = false, example = "id", dataType = "java.util.String")
  private String fieldName;
  @ApiModelProperty(name = "order", value = "order", required = false, example = "ASC|DESC", dataType = "java.util.String")
  private String order;

}
