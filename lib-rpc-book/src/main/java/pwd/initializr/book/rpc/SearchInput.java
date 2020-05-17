package pwd.initializr.book.rpc;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.book.rpc@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-17 08:23
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class SearchInput {

  @ApiModelProperty(name = "keyword", value = "keyword", required = false, example = "万里江山", dataType = "java.lang.String")
  private String keyword;
  @ApiModelProperty(name = "index", value = "pageIndex", required = false, example = "0", dataType = "java.lang.Integer")
  private Integer index;
  @ApiModelProperty(name = "size", value = "pageSize", required = false, example = "12", dataType = "java.lang.Integer")
  private Integer size;
}
