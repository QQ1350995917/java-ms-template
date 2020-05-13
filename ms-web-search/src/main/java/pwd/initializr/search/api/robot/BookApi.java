package pwd.initializr.search.api.robot;

import pwd.initializr.book.rpc.ArticleIntoSearch;
import pwd.initializr.search.api.robot.vo.ArticleInputVO;
import pwd.initializr.search.api.robot.vo.BookInputVO;

/**
 * pwd.initializr.search.api.robot@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-13 11:33
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface BookApi {

  void postOrPutBook(BookInputVO input);

  void postOrPutArticle(ArticleInputVO input);

  void searchBook();

  void searchArticle();

  void searchAll();

}
