package pwd.initializr.search.test.business;

import org.springframework.beans.BeanUtils;
import pwd.initializr.search.api.robot.vo.BookIntoSearchInput;

/**
 * pwd.initializr.search.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-13 17:33
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class MainTest {

  public static void main(String[] args) {
    BookIntoSearchInput bookInputVO = new BookIntoSearchInput();
    bookInputVO.setId(123L);
    bookInputVO.setTitle("xxxx");

    BookIntoSearchInput bookInputVO1 = new BookIntoSearchInput();

    BeanUtils.copyProperties(bookInputVO,bookInputVO1);

    System.out.println();
  }
}
