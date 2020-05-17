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
  public ObjectList<ArticleBO> listTablesInBook(Long bookId) {
    Document queryObject = new Document();
    queryObject.put("bookId", bookId);

    Document fieldsObject = new Document();
    fieldsObject.put("id", true);
    fieldsObject.put("bookId", true);
    fieldsObject.put("title", true);
    fieldsObject.put("subTitle", true);

    Query query = new BasicQuery(queryObject, fieldsObject);

    List<ArticleEntity> articleEntities = mongoTemplate.find(query, ArticleEntity.class);

    ObjectList<ArticleBO> result = new ObjectList<>();
    List<ArticleBO> articleBOS = new LinkedList<>();
    for (ArticleEntity articleEntity : articleEntities) {
      ArticleBO articleBO = new ArticleBO();
      BeanUtils.copyProperties(articleEntity, articleBO);
      articleBOS.add(articleBO);
    }
    result.setElements(articleBOS);
    return result;
  }


  @Override
  public ArticleAroundBO listTablesAroundInBook(Long bookId, Long articleId) {
    Document query = new Document();

    Document fieldsObject = new Document();
    fieldsObject.put("id", true);
    fieldsObject.put("bookId", true);
    fieldsObject.put("title", true);
    fieldsObject.put("subTitle", true);

    Query queryBefore = new BasicQuery(query, fieldsObject);
    queryBefore.addCriteria(Criteria.where("bookId").is(bookId).and("_id").lt(articleId));
    queryBefore.with(new Sort(Direction.DESC, "_id"));
    ArticleEntity beforeArticleEntity = mongoTemplate.findOne(queryBefore, ArticleEntity.class);

    Query queryAfter = new BasicQuery(query, fieldsObject);
    queryAfter.addCriteria(Criteria.where("bookId").is(bookId).and("_id").gt(articleId));
    queryAfter.with(new Sort(Direction.ASC, "_id"));
    ArticleEntity afterArticleEntity = mongoTemplate.findOne(queryAfter, ArticleEntity.class);

    ArticleAroundBO result = new ArticleAroundBO();
    if (beforeArticleEntity != null) {
      ArticleBO beforeArticleBO = new ArticleBO();
      BeanUtils.copyProperties(beforeArticleEntity, beforeArticleBO);
      result.setPre(beforeArticleBO);
    }

    if (afterArticleEntity != null) {
      ArticleBO afterArticleBO = new ArticleBO();
      BeanUtils.copyProperties(afterArticleEntity, afterArticleBO);
      result.setNext(afterArticleBO);
    }

    return result;
  }

  @Override
  public ArticleBO findArticleByArticleIdInBook(Long articleId) {
    Document queryObject = new Document();
    queryObject.put("id", articleId);

    Query query = new BasicQuery(queryObject);

    ArticleEntity articleEntity = mongoTemplate.findOne(query, ArticleEntity.class);

    ArticleBO articleBO = new ArticleBO();
    BeanUtils.copyProperties(articleEntity, articleBO);
    return articleBO;
  }

}
