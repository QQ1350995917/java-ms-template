package pwd.initializr.search.business.user;

import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;
import pwd.initializr.search.business.user.bo.ArticleAroundBO;
import pwd.initializr.search.business.user.bo.ArticleBO;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * pwd.initializr.book.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-12-14 22:55
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class ArticleServiceImpl implements ArticleService {


  @Override
  public ObjectList<ArticleBO> listTablesInBook(Long bookId) {
    ObjectList<ArticleBO> result = new ObjectList<>();
    List<ArticleBO> articleBOS = new LinkedList<>();
    result.setElements(articleBOS);
    return result;
  }


  @Override
  public ArticleAroundBO listTablesAroundInBook(Long bookId, Long articleId) {
    ArticleAroundBO result = new ArticleAroundBO();
    return result;
  }

  @Override
  public ArticleBO findArticleByArticleIdInBook(Long bookId, Long articleId) {
    ArticleBO articleBO = new ArticleBO();
    return articleBO;
  }

}
