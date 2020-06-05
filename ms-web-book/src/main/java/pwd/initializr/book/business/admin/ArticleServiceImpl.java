package pwd.initializr.book.business.admin;

import com.mongodb.client.result.UpdateResult;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import pwd.initializr.book.business.admin.bo.ArticleBO;
import pwd.initializr.book.persistence.entity.ArticleEntity;
import pwd.initializr.common.utils.ConstantDeleteStatus;
import pwd.initializr.common.utils.ConstantRecommendStatus;
import pwd.initializr.common.utils.ConstantVisibilityStatus;
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
  public ArticleBO createArticle(ArticleBO articleBO) {
    ArticleEntity articleEntity = new ArticleEntity();
    BeanUtils.copyProperties(articleBO, articleEntity);
    articleEntity.setDelStatus(ConstantDeleteStatus.EXISTING.value());
    Date currentDateTime = new Date();
    articleEntity.setCreateTime(currentDateTime);
    articleEntity.setUpdateTime(currentDateTime);
    ArticleEntity newArticleEntity = mongoTemplate.save(articleEntity);
    BeanUtils.copyProperties(newArticleEntity, articleBO);
    return articleBO;
  }

  @Override
  public Integer deleteArticles(List<Long> articleIds) {
    return update(articleIds, "del_status", ConstantDeleteStatus.DELETED.value());
  }

  @Override
  public Integer deleteCancelArticles(List<Long> articleIds) {
    return update(articleIds, "del_status", ConstantDeleteStatus.EXISTING.value());
  }

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

  @Override
  public ObjectList<ArticleBO> listArticle(Integer pageIndex, Integer pageSize) {
    Query query = new Query().with(PageRequest.of(pageIndex, pageSize));
    ObjectList<ArticleBO> result = this.listArticle(query);
    result.setIndex(pageIndex.longValue());
    result.setSize(pageSize.longValue());
    return result;
  }

  @Override
  public ObjectList<ArticleBO> listArticle(Long bookId, Integer pageIndex, Integer pageSize) {
    Query query = new Query().addCriteria(Criteria.where("book_id").is(bookId))
        .with(PageRequest.of(pageIndex, pageSize));
    ObjectList<ArticleBO> result = this.listArticle(query);
    result.setIndex(pageIndex.longValue());
    result.setSize(pageSize.longValue());
    return result;
  }

  @Override
  public Integer recommendArticles(List<Long> articleIds) {
    return update(articleIds, "recommend", ConstantRecommendStatus.RECOMMEND.value());
  }

  @Override
  public Integer recommendCancelArticles(List<Long> articleIds) {
    return update(articleIds, "recommend", ConstantRecommendStatus.NOT_RECOMMEND.value());
  }

  @Override
  public Long updateArticle(ArticleBO articleBO) {
    Query query = new Query().addCriteria(Criteria.where("id").is(articleBO.getId()));
    Update update = new Update()
        .set("book_id", articleBO.getTitle())
        .set("title", articleBO.getTitle())
        .set("sub_title", articleBO.getSubTitle())
        .set("author_id", articleBO.getAuthorId())
        .set("author_name", articleBO.getAuthorName())
        .set("summary", articleBO.getSummary())
        .set("labels", articleBO.getLabels())
        .set("paragraphs", articleBO.getParagraphs())
        .set("from_url", articleBO.getFromUrl())
        .set("publication_time", articleBO.getPublicationTime())
        .set("del_status", articleBO.getDelStatus())
        .set("del_status", articleBO.getDelStatus())
        .set("visibility", articleBO.getVisibility())
        .set("recommend", articleBO.getRecommend())
        .set("update_time", articleBO.getUpdateTime());

    UpdateResult updateResult = mongoTemplate.updateFirst(query, update, ArticleEntity.class);
    return updateResult.getModifiedCount();
  }

  @Override
  public Integer visibleArticles(List<Long> articleIds) {
    return update(articleIds, "visibility", ConstantVisibilityStatus.VISIBILITY.value());
  }

  @Override
  public Integer visibleCancelArticles(List<Long> articleIds) {
    return update(articleIds, "visibility", ConstantVisibilityStatus.NOT_VISIBILITY.value());
  }

  private ObjectList<ArticleBO> listArticle(Query query) {
    query.fields().exclude("paragraphs");
    List<ArticleEntity> articleEntities = mongoTemplate.find(query, ArticleEntity.class);
    long count = mongoTemplate.count(query, ArticleEntity.class);
    List<ArticleBO> articleBOs = new LinkedList<>();
    for (ArticleEntity articleEntity : articleEntities) {
      ArticleBO articleBO = new ArticleBO();
      BeanUtils.copyProperties(articleEntity, articleBO);
      articleBOs.add(articleBO);
    }
    ObjectList<ArticleBO> result = new ObjectList<>();
    result.setElements(articleBOs);
    result.setTotal(count);
    return result;
  }


  private Integer update(List<Long> articleIds, String key, Integer value) {
    Query query = new Query().addCriteria(Criteria.where("id").in(articleIds));
    Update update = new Update().set(key, value);
    UpdateResult updateResult = mongoTemplate.updateFirst(query, update, ArticleEntity.class);
    return Long.valueOf(updateResult.getModifiedCount()).intValue();
  }
}
