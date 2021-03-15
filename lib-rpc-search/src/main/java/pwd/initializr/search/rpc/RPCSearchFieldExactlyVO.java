package pwd.initializr.search.rpc;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.Set;
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
@ApiModel(value = "RPCSearchFieldExactlyVO", description = "精确查找输入数据属性模型")
public class RPCSearchFieldExactlyVO {

  @ApiModelProperty(name = "must", value = "must", required = false, example = "", dataType = "java.util.Set")
  private Set<RPCSearchFieldVO> must;
  @ApiModelProperty(name = "should", value = "should", required = false, example = "", dataType = "java.util.Set")
  private Set<RPCSearchFieldVO> should;
  @ApiModelProperty(name = "mustNot", value = "mustNot", required = false, example = "", dataType = "java.util.Set")
  private Set<RPCSearchFieldVO> mustNot;


}
