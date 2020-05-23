package pwd.initializr.search.business.robot;

import org.springframework.stereotype.Service;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.search.business.robot.bo.ArticleBO;
import pwd.initializr.search.business.robot.bo.BookBO;
import pwd.initializr.search.business.robot.bo.SearchOutputBO;

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

  ObjectList<SearchOutputBO> search(String keyword,Integer pageIndex,Integer pageSize);
}