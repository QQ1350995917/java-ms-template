package pwd.initializr.book.business.admin;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import pwd.initializr.book.business.admin.bo.ArticleBO;
import pwd.initializr.book.persistence.entity.ArticleEntity;
import pwd.initializr.common.utils.DateTimeUtil;
import pwd.initializr.common.web.business.bo.ObjectList;

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

  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public ArticleBO createNewArticle(ArticleBO articleBO) {
    ArticleEntity articleEntity = new ArticleEntity();
    BeanUtils.copyProperties(articleBO,articleEntity);
    articleEntity.setStatus(0);
    String currentDateTime = DateTimeUtil.getCurrentDateTime();
    articleEntity.setCreateTime(currentDateTime);
    articleEntity.setUpdateTime(currentDateTime);
    articleEntity.setWords(articleEntity.createWords(articleBO.getParagraphTokenizer()));
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
