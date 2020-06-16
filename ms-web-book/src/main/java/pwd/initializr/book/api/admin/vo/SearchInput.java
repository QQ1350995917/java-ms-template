package pwd.initializr.book.api.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pwd.initializr.search.rpc.RPCSearchInput;

/**
 * pwd.initializr.book.api.user.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-17 16:34
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
@ApiModel(value = "bookVO", description = "图书VO属性")
public class SearchInput implements Serializable {

  @ApiModelProperty(name = "keyword", value = "keyword", required = false, example = "万里江山", dataType = "java.lang.String")
  private String keyword;
  @ApiModelProperty(name = "index", value = "index", required = false, example = "0", dataType = "java.lang.Integer")
  private Integer index = 0;
  @ApiModelProperty(name = "size", value = "size", required = false, example = "12", dataType = "java.lang.Integer")
  private Integer size = 12;

  public Integer getIndex() {
    return index == null ? 0 : index;
  }

  public Integer getSize() {
    return size == null ? 12 : size;
  }

}
