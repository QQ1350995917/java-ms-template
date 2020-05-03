package pwd.initializr.book.api.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.logger.api.user.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-12-14 20:01
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(value = "bookListInput", description = "图书清单输入参数")
public class BookListInput {
  @ApiModelProperty(name = "words", value = "搜索关键词", required = false, example = "基石")
  @NotNull(message = "0")
  private Set<String> words;
  @ApiModelProperty(name = "pageIndex", value = "页码，从零起始，默认0", required = false, example = "0")
  @NotNull(message = "0")
  private Integer pageIndex;
  @ApiModelProperty(name = "pageSize", value = "页面容量，默认12", required = false, example = "12")
  @NotNull(message = "0")
  private Integer pageSize;
}
