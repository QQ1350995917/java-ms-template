package pwd.initializr.article.api.user;

import pwd.initializr.article.api.user.vo.BookDetailInput;
import pwd.initializr.article.api.user.vo.BookListInput;

/**
 * pwd.initializr.logger.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-12-14 20:51
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface BookApi {

    void fetchBookListByRange(BookListInput input);

    void fetchBookDetailByBookId(Long input);

    void fetchArticleDetailByArticleId(Long bookId,Long articleId);

}
