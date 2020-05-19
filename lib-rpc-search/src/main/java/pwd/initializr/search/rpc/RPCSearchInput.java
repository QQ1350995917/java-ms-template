package pwd.initializr.search.rpc;

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
 * date 2020-05-19 14:34
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
public class RPCSearchInput {

  private static final long serialVersionUID = -4291776028708567323L;

  @ApiModelProperty(name = "keyword", value = "keyword", required = false, example = "万里江山", dataType = "java.lang.String")
  private String keyword;
  @ApiModelProperty(name = "index", value = "index", required = false, example = "0", dataType = "java.lang.Integer")
  private Integer index = 0;
  @ApiModelProperty(name = "size", value = "size", required = false, example = "12", dataType = "java.lang.Integer")
  private Integer size = 12;
}
