package pwd.initializr.book.business.admin;

import com.mongodb.client.result.UpdateResult;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import pwd.initializr.book.business.admin.bo.ArticleAroundBO;
import pwd.initializr.book.business.admin.bo.ArticleBO;
import pwd.initializr.book.business.admin.bo.BookBO;
import pwd.initializr.book.persistence.entity.ArticleEntity;
import pwd.initializr.book.persistence.entity.BookEntity;
import pwd.initializr.book.persistence.entity.DocumentObjectUpdate;
import pwd.initializr.common.utils.ConstantDeleteStatus;
import pwd.initializr.common.utils.ConstantRecommendStatus;
import pwd.initializr.common.utils.ConstantVisibilityStatus;
import pwd.initializr.common.web.business.bo.PageableQueryResult;

/**
 * pwd.initializr.book.business.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-12-14 22:13
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class BookServiceImpl implements BookService {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public BookBO createBook(BookBO bookBO) {
    BookEntity bookEntity = new BookEntity();
    BeanUtils.copyProperties(bookBO, bookEntity);
    bookEntity.setDelStatus(ConstantDeleteStatus.EXISTING.value());
    Date currentDateTime = new Date();
    bookEntity.setCreateTime(currentDateTime);
    bookEntity.setUpdateTime(currentDateTime);
    BookEntity newBookEntity = mongoTemplate.save(bookEntity);
    BeanUtils.copyProperties(newBookEntity, bookBO);
    return bookBO;
  }

  @Override
  public Integer deleteBooks(List<Long> bookIds) {
    return update(bookIds, "del_status", ConstantDeleteStatus.DELETED.value());
  }

  @Override
  public Integer deleteCancelBooks(List<Long> bookIds) {
    return update(bookIds, "del_status", ConstantDeleteStatus.EXISTING.value());
  }

  @Override
  public BookBO findBookById(Long bookId) {
    BookEntity bookEntity = mongoTemplate
        .findOne(Query.query(Criteria.where("id").is(bookId)), BookEntity.class);
    if (bookEntity == null) {
      return null;
    }
    BookBO bookBO = new BookBO();
    BeanUtils.copyProperties(bookEntity, bookBO);
    return bookBO;
  }

  @Override
  public PageableQueryResult<BookBO> listBook(Long pageIndex, Long pageSize) {
//    Sort sort = new Sort(Direction.DESC, "id");
    Query query = new Query()
        .with(PageRequest.of(pageIndex.intValue(), pageSize.intValue()))
//        .with(sort)
        ;

    List<BookEntity> bookEntities = mongoTemplate.find(query, BookEntity.class);
    long count = mongoTemplate.count(query, BookEntity.class);
    PageableQueryResult<BookBO> result = new PageableQueryResult<>();
    List<BookBO> bookBOS = new LinkedList<>();
    for (BookEntity bookEntity : bookEntities) {
      BookBO bookBO = new BookBO();
      BeanUtils.copyProperties(bookEntity, bookBO);
      bookBOS.add(bookBO);
    }
    result.setElements(bookBOS);
    result.setTotal(count);
    result.setSize(pageSize.longValue());
    result.setIndex(pageIndex.longValue());
    return result;
  }

  @Override
  public PageableQueryResult<ArticleBO> listBookTable(Long bookId, Integer index, Integer size) {
    Query query = new Query(Criteria.where("bookId").is(bookId))
        .with(PageRequest.of(index, size)).with(Sort.by(Direction.ASC, "id"));
    query.fields().include("id").include("bookId").include("title").include("subTitle");
    long count = mongoTemplate.count(query, ArticleEntity.class);
    List<ArticleEntity> articleEntities = mongoTemplate.find(query, ArticleEntity.class);
    List<ArticleBO> articleBOS = new LinkedList<>();
    articleEntities.forEach(articleEntity -> {
      ArticleBO articleBO = new ArticleBO();
      BeanUtils.copyProperties(articleEntity, articleBO);
      articleBOS.add(articleBO);
    });
    PageableQueryResult<ArticleBO> result = new PageableQueryResult<>();
    result.setElements(articleBOS);
    result.setIndex(index.longValue());
    result.setSize(size.longValue());
    result.setTotal(count);
    return result;
  }

  @Override
  public ArticleAroundBO listBookTableByAround(Long bookId, Long articleId) {
    Document query = new Document();

    Document fieldsObject = new Document();
    fieldsObject.put("id", true);
    fieldsObject.put("bookId", true);
    fieldsObject.put("title", true);
    fieldsObject.put("subTitle", true);

    Query queryBefore = new BasicQuery(query, fieldsObject);
    queryBefore.addCriteria(Criteria.where("bookId").is(bookId).and("_id").lt(articleId));
//    queryBefore.with(new Sort(Direction.DESC, "_id"));
    ArticleEntity beforeArticleEntity = mongoTemplate.findOne(queryBefore, ArticleEntity.class);

    Query queryAfter = new BasicQuery(query, fieldsObject);
    queryAfter.addCriteria(Criteria.where("bookId").is(bookId).and("_id").gt(articleId));
//    queryAfter.with(new Sort(Direction.ASC, "_id"));
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
  public Integer recommendBooks(List<Long> bookIds) {
    return update(bookIds, "recommend", ConstantRecommendStatus.RECOMMEND.value());
  }

  @Override
  public Integer recommendCancelBooks(List<Long> bookIds) {
    return update(bookIds, "recommend", ConstantRecommendStatus.NOT_RECOMMEND.value());
  }

  @Override
  public Long updateBook(BookBO bookBO) {
    Query query = new Query().addCriteria(Criteria.where("id").is(bookBO.getId()));
    BookEntity bookEntity = new BookEntity();
    BeanUtils.copyProperties(bookBO,bookEntity);
    Update update = DocumentObjectUpdate.getUpdate(bookEntity);
    UpdateResult updateResult = mongoTemplate.updateFirst(query, update, BookEntity.class);
    return updateResult.getModifiedCount();
  }

  @Override
  public Integer visibleBooks(List<Long> bookIds) {
    return update(bookIds, "visibility", ConstantVisibilityStatus.VISIBILITY.value());
  }

  @Override
  public Integer visibleCancelBooks(List<Long> bookIds) {
    return update(bookIds, "visibility", ConstantVisibilityStatus.NOT_VISIBILITY.value());
  }

  private Integer update(List<Long> bookIds, String key, Integer value) {
    Query query = new Query().addCriteria(Criteria.where("id").in(bookIds));
    Update update = new Update().set(key, value);
    UpdateResult updateResult = mongoTemplate.updateFirst(query, update, BookEntity.class);
    return Long.valueOf(updateResult.getModifiedCount()).intValue();
  }

}
