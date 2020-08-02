package pwd.initializr.search.business.user;

import org.springframework.stereotype.Service;
import pwd.initializr.search.business.user.bo.BookBO;
import pwd.initializr.common.web.business.bo.PageableQueryResult;

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

  PageableQueryResult<BookBO> listBookByRange(Long userId);

  BookBO findBookById(Long bookId);

}
