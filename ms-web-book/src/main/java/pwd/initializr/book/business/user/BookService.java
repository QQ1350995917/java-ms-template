package pwd.initializr.book.business.user;

import org.springframework.stereotype.Service;
import pwd.initializr.book.business.user.bo.ArticleAroundBO;
import pwd.initializr.book.business.user.bo.ArticleBO;
import pwd.initializr.book.business.user.bo.BookBO;
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

    PageableQueryResult<BookBO> listRecommendBooks(Integer index, Integer size);

    BookBO findBookById(Long bookId);

    PageableQueryResult<ArticleBO> listBookTable(Long bookId, Integer index, Integer size);

    ArticleAroundBO listBookTableByAround(Long bookId, Long tableId);

}
