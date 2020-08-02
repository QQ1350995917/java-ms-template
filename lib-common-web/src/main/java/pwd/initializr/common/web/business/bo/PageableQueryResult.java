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
 * <h1>统一业务层分页查询结果结构声明</h1>
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
public class PageableQueryResult<T> {

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

  public PageableQueryResult(Long total, Long index, Long size, List<T> elements) {
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

  /**
   * <h2>禁止对总页数进行设置，总页数由页面容量和总量计算得出</h2>
   * date 2020-08-02 23:01
   *
   * @param pages 总页数
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  private void setPages(Long pages) {
    this.pages = pages;
  }
}
