package pwd.initializr.search.business.admin;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.search.business.admin.bo.BookBO;

/**
 * pwd.initializr.book.business.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-12-14 22:13
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class BookServiceImpl implements BookService {

  @Override
  public BookBO createNewBook(BookBO bookBO) {
    return bookBO;
  }

  @Override
  public ObjectList<BookBO> searchBookByRange(Set<String> words, Integer pageIndex,
      Integer pageSize) {
    ObjectList<BookBO> result = new ObjectList<>();
    List<BookBO> bookBOS = new LinkedList<>();
    result.setElements(bookBOS);
    return result;
  }

}
