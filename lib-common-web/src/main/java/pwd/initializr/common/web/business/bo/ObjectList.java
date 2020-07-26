package pwd.initializr.common.web.business.bo;

import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.common.web.business.bo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-14 15:26
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
public class ObjectList<T> {

  private List<T> elements = new LinkedList<>();
  /**
   * 当前页码
   */
  private Long index;
  /**
   * 总页数
   */
  private Long pages;
  /**
   * 当前页面容量
   */
  private Long size;
  /**
   * 总数据量
   */
  private Long total;

  public ObjectList(Long total, Long index, Long size, List<T> elements) {
    this.total = total;
    this.index = index;
    this.size = size;
    this.elements = elements;
  }

  public Long getPages() {
    if (size != null && size > 0) {
      return this.pages = total % size == 0 ? (total / size) : (total / size + 1);
    } else {
      return 0L;
    }
  }

  private void setPages(Long pages) {
    this.pages = pages;
  }
}
