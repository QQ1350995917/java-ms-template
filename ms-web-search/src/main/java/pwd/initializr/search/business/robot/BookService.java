package pwd.initializr.search.business.robot;

import org.springframework.stereotype.Service;
import pwd.initializr.search.business.robot.bo.ArticleBO;
import pwd.initializr.search.business.robot.bo.BookBO;

/**
 * pwd.initializr.search.business.robot@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-13 14:22
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public interface BookService {

  BookBO postOrPutBook(BookBO bookBO);

  ArticleBO postOrPutArticle(ArticleBO articleBO);

  void searchBook();

  void searchArticle();

  void searchAll();
}
