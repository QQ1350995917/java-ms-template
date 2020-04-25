package pwd.initializr.book.business.user;

import org.springframework.stereotype.Service;
import pwd.initializr.book.business.user.bo.ArticleAroundBO;
import pwd.initializr.book.business.user.bo.ArticleBO;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * pwd.initializr.book.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-12-14 20:38
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public interface ArticleService {

  ObjectList<ArticleBO> listTablesInBook(Long bookId);

  ArticleAroundBO listTablesAroundInBook(Long bookId, Long tableId);

  ArticleBO findArticleByArticleIdInBook(Long bookId, Long articleId);

}
