package pwd.initializr.article.business.admin;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import pwd.initializr.article.business.admin.bo.ArticleBO;
import pwd.initializr.article.persistence.dao.ArticleEntity;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * pwd.initializr.article.business.admin@ms-web-initializr
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

  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public ArticleBO createNewArticle(ArticleBO articleBO) {
    ArticleEntity articleEntity = new ArticleEntity();
    BeanUtils.copyProperties(articleBO,articleEntity);
    ArticleEntity newArticleEntity = mongoTemplate.save(articleEntity);
    BeanUtils.copyProperties(newArticleEntity,articleBO);
    return articleBO;
  }

  @Override
  public ObjectList<ArticleBO> listArticleByBookId(Long bookId) {
    List<ArticleEntity> articleEntities = mongoTemplate.findAll(ArticleEntity.class);
    List<ArticleBO> articleBOs = new LinkedList<>();
    for (ArticleEntity articleEntity : articleEntities) {
      ArticleBO articleBO = new ArticleBO();
      BeanUtils.copyProperties(articleEntity,articleBO);
      articleBOs.add(articleBO);
    }
    ObjectList<ArticleBO> result = new ObjectList<>();
    result.setElements(articleBOs);
    return result;
  }
}