package pwd.initializr.book.business.admin;

import com.mongodb.client.result.UpdateResult;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import pwd.initializr.book.business.admin.bo.ArticleBO;
import pwd.initializr.book.persistence.entity.ArticleEntity;
import pwd.initializr.book.persistence.entity.BookEntity;
import pwd.initializr.common.utils.ConstantDeleteStatus;
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
    BeanUtils.copyProperties(articleBO,articleEntity);
    articleEntity.setDelStatus(ConstantDeleteStatus.EXISTING.value());
    Date currentDateTime = new Date();
    articleEntity.setCreateTime(currentDateTime);
    articleEntity.setUpdateTime(currentDateTime);
    ArticleEntity newArticleEntity = mongoTemplate.save(articleEntity);
    BeanUtils.copyProperties(newArticleEntity,articleBO);
    return articleBO;
  }

  @Override
  public Long updateArticle(ArticleBO articleBO) {
    Query query = new Query().addCriteria(Criteria.where("id").is(articleBO.getId()));
    Update update = new Update()
        .set("book_id",articleBO.getTitle())
        .set("title",articleBO.getTitle())
        .set("sub_title",articleBO.getSubTitle())
        .set("author_id",articleBO.getAuthorId())
        .set("author_name",articleBO.getAuthorName())
        .set("summary",articleBO.getSummary())
        .set("labels",articleBO.getLabels())
        .set("paragraphs",articleBO.getParagraphs())
        .set("from_url",articleBO.getFromUrl())
        .set("publication_time",articleBO.getPublicationTime())
        .set("del_status",articleBO.getDelStatus())
        .set("update_time",articleBO.getUpdateTime());

    UpdateResult updateResult = mongoTemplate.updateFirst(query, update, ArticleEntity.class);
    return updateResult.getModifiedCount();
  }

  @Override
  public Long deleteArticleById(List<Long> articleIds) {
    Query query = new Query().addCriteria(Criteria.where("id").in(articleIds));
    Update update = new Update().set("del_status",ConstantDeleteStatus.DELETED.value());
    UpdateResult updateResult = mongoTemplate.updateFirst(query, update, BookEntity.class);
    return updateResult.getModifiedCount();
  }

  @Override
  public ObjectList<ArticleBO> listArticle(Integer pageIndex, Integer pageSize) {
    Query query = new Query().with(PageRequest.of(pageIndex, pageSize));
    ObjectList<ArticleBO> result = this.listArticle(query);
    result.setPages(pageIndex.longValue());
    result.setSize(pageSize.longValue());
    return result;
  }

  @Override
  public ObjectList<ArticleBO> listArticle(Long bookId, Integer pageIndex, Integer pageSize) {
    Query query = new Query().addCriteria(Criteria.where("book_id").is(bookId)).with(PageRequest.of(pageIndex, pageSize));
    ObjectList<ArticleBO> result = this.listArticle(query);
    result.setPages(pageIndex.longValue());
    result.setSize(pageSize.longValue());
    return result;
  }

  private ObjectList<ArticleBO> listArticle(Query query) {
    List<ArticleEntity> articleEntities = mongoTemplate.find(query,ArticleEntity.class);
    long count = mongoTemplate.count(query, ArticleEntity.class);
    List<ArticleBO> articleBOs = new LinkedList<>();
    for (ArticleEntity articleEntity : articleEntities) {
      ArticleBO articleBO = new ArticleBO();
      BeanUtils.copyProperties(articleEntity,articleBO);
      articleBOs.add(articleBO);
    }
    ObjectList<ArticleBO> result = new ObjectList<>();
    result.setElements(articleBOs);
    result.setTotal(count);
    return result;
  }
}
