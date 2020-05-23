package pwd.initializr.book.api.admin;

import pwd.initializr.book.api.admin.vo.CreateBookInput;
import pwd.initializr.book.api.admin.vo.SearchInput;

/**
 * pwd.initializr.book.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-12-14 22:15
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface BookApi {

    void fetchBooksByRange(SearchInput input);

    void fetchBookById(Long bookId);

    void fetchBookTables(Long bookId, Long startId, Long pageSize);

    void fetchBookTableById(Long bookId, Long articleId);

    void updateBook(Long bookId, CreateBookInput input);

    void createBook(CreateBookInput input);

    void deleteBooks(Long[] bookIds);

}
