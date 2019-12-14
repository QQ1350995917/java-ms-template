package pwd.initializr.article.api.admin;

import pwd.initializr.article.api.admin.vo.BookListInput;
import pwd.initializr.article.api.admin.vo.CreateBookInput;

/**
 * pwd.initializr.article.api.admin@ms-web-initializr
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

  void createNewBook(CreateBookInput input);

  void fetchBookByRange(BookListInput input);

}
