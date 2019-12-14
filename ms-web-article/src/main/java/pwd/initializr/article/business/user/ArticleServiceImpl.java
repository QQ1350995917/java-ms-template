package pwd.initializr.article.business.user;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import pwd.initializr.article.business.user.bo.ArticleBO;
import pwd.initializr.article.business.user.bo.BookBO;
import pwd.initializr.article.persistence.dao.ArticleEntity;
import pwd.initializr.article.persistence.dao.BookEntity;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * pwd.initializr.article.business.user@ms-web-initializr
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

  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public ObjectList<ArticleBO> listArticleByBookId(Long bookId) {
    List<ArticleEntity> articleEntities = mongoTemplate.findAll(ArticleEntity.class);
    ObjectList<ArticleBO> result = new ObjectList<>();
    List<ArticleBO> articleBOS = new LinkedList<>();
    for (ArticleEntity articleEntity : articleEntities) {
      ArticleBO articleBO = new ArticleBO();
      BeanUtils.copyProperties(articleEntity,articleBO);
      articleBOS.add(articleBO);
    }
    result.setElements(articleBOS);
    return result;
  }

  @Override
  public ArticleBO detailArticleByArticleId(Long articleId) {
    ArticleEntity articleEntity = mongoTemplate
        .findOne(Query.query(Criteria.where("_id").is(articleId)), ArticleEntity.class);
    ArticleBO articleBO = new ArticleBO();
    BeanUtils.copyProperties(articleEntity,articleBO);
    return articleBO;
  }
}
