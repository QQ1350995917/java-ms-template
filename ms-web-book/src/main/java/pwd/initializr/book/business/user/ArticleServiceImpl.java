package pwd.initializr.book.business.user;

import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import pwd.initializr.book.business.user.bo.ArticleAroundBO;
import pwd.initializr.book.business.user.bo.ArticleBO;
import pwd.initializr.book.persistence.entity.ArticleEntity;
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

  @Autowired
  private MongoTemplate mongoTemplate;



  @Override
  public ArticleBO findArticleById(Long articleId) {
    Document queryObject = new Document();
    queryObject.put("id", articleId);

    Query query = new BasicQuery(queryObject);

    ArticleEntity articleEntity = mongoTemplate.findOne(query, ArticleEntity.class);

    ArticleBO articleBO = new ArticleBO();
    BeanUtils.copyProperties(articleEntity, articleBO);
    return articleBO;
  }

}
