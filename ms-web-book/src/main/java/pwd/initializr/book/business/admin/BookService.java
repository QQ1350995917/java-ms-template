package pwd.initializr.book.business.admin;

import java.util.List;
import org.springframework.stereotype.Service;
import pwd.initializr.book.business.admin.bo.ArticleAroundBO;
import pwd.initializr.book.business.admin.bo.ArticleBO;
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

  BookBO createBook(BookBO bookBO);

  Integer deleteBooks(List<Long> bookIds);

  Integer deleteCancelBooks(List<Long> bookIds);

  BookBO findBookById(Long bookId);

  ObjectList<BookBO> listBook(Integer pageIndex, Integer pageSize);

  ObjectList<ArticleBO> listBookTable(Long bookId, Integer index, Integer size);

  ArticleAroundBO listBookTableByAround(Long bookId, Long tableId);

  Integer recommendBooks(List<Long> bookIds);

  Integer recommendCancelBooks(List<Long> bookIds);

  Long updateBook(BookBO bookBO);

  Integer visibleBooks(List<Long> bookIds);

  Integer visibleCancelBooks(List<Long> bookIds);

}
