package pwd.initializr.book.business.admin;

import java.util.List;
import org.springframework.stereotype.Service;
import pwd.initializr.book.business.admin.bo.ArticleBO;
import pwd.initializr.common.web.business.bo.ObjectList;


/**
 * pwd.initializr.book.business.admin.bo@ms-web-initializr
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

  ArticleBO createArticle(ArticleBO articleBO);

  Integer deleteArticles(List<Long> articleIds);

  Integer deleteCancelArticles(List<Long> articleIds);

  ArticleBO findArticleById(Long articleId);

  ObjectList<ArticleBO> listArticle(Integer pageIndex, Integer pageSize);

  ObjectList<ArticleBO> listArticle(Long bookId, Integer pageIndex, Integer pageSize);

  Integer recommendArticles(List<Long> articleIds);

  Integer recommendCancelArticles(List<Long> articleIds);

  Long updateArticle(ArticleBO articleBO);

  Integer visibleArticles(List<Long> articleIds);

  Integer visibleCancelArticles(List<Long> articleIds);
}

