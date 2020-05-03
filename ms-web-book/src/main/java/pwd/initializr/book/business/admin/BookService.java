package pwd.initializr.book.business.admin;

import java.util.Set;
import org.springframework.stereotype.Service;
import pwd.initializr.book.business.admin.bo.BookBO;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * pwd.initializr.book.business.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-12-14 20:46
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public interface BookService {

  BookBO createNewBook(BookBO bookBO);

  ObjectList<BookBO> searchBookByRange(Set<String> words, Integer pageIndex, Integer pageSize);

}
