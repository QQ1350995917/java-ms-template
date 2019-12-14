package pwd.initializr.article.business.user;

import org.springframework.stereotype.Service;
import pwd.initializr.article.business.user.bo.ArticleBO;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * pwd.initializr.article.business.user@ms-web-initializr
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

  ObjectList<ArticleBO> listArticleByBookId(Long bookId);

  ArticleBO detailArticleByArticleId(Long articleId);

}
