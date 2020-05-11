package pwd.initializr.search.business.admin;

import org.springframework.stereotype.Service;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.search.business.admin.bo.ArticleBO;

/**
 * pwd.initializr.book.business.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-12-14 22:30
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class ArticleServiceImpl implements ArticleService {


  @Override
  public ArticleBO createNewArticle(ArticleBO articleBO) {

    return articleBO;
  }

  @Override
  public ObjectList<ArticleBO> listArticleByBookId(Long bookId) {
    ObjectList<ArticleBO> result = new ObjectList<>();
    return result;
  }
}
