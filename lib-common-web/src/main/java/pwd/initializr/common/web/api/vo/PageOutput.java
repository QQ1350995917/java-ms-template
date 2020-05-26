package pwd.initializr.common.web.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.common.web.api.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-29 23:38
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PageOutput<T> extends PageInput {

  @ApiModelProperty(value = "数据")
  private List<T> elements = new LinkedList<>();
  @ApiModelProperty(value = "总页码量")
  private Long pages;
  @ApiModelProperty(value = "总数据量")
  private Long total;

  public Long getPages() {
    if (getSize() != null && getSize() > 0) {
      return this.pages = total % getSize() == 0 ? (total / getSize()) : (total / getSize() + 1);
    } else {
      return 0L;
    }
  }
}
