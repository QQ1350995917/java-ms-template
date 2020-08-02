package pwd.initializr.search.business.user;

import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.search.business.user.bo.BookBO;

/**
 * pwd.initializr.book.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-12-14 21:30
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class BookServiceImpl implements BookService {

  @Override
  public PageableQueryResult<BookBO> listBookByRange(Long userId) {
    PageableQueryResult<BookBO> result = new PageableQueryResult<>();
    List<BookBO> bookBOS = new LinkedList<>();
    result.setElements(bookBOS);
    return result;
  }

  @Override
  public BookBO findBookById(Long bookId) {
    BookBO bookBO = new BookBO();
    return bookBO;
  }
}
