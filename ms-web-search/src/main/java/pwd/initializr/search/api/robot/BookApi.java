package pwd.initializr.search.api.robot;

import pwd.initializr.search.api.robot.vo.ArticleIntoSearchInputVO;
import pwd.initializr.search.api.robot.vo.BookIntoSearchInputVO;
import pwd.initializr.search.api.robot.vo.SearchInputVo;

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

  void postOrPutArticle(ArticleIntoSearchInputVO input);

  void postOrPutBook(BookIntoSearchInputVO input);

  void search(SearchInputVo input);

}
