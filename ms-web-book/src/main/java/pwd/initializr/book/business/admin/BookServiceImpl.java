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
import pwd.initializr.common.utils.ConstantDeleteStatus;
import pwd.initializr.common.web.business.bo.ObjectList;

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
  public Long updateBook(BookBO bookBO) {
    Query query = new Query().addCriteria(Criteria.where("id").is(bookBO.getId()));
    Update update = new Update()
      .set("title",bookBO.getTitle())
      .set("sub_title",bookBO.getSubTitle())
      .set("author_id",bookBO.getAuthorId())
      .set("author_name",bookBO.getAuthorName())
      .set("type",bookBO.getType())
      .set("isbn",bookBO.getIsbn())
      .set("thumbs",bookBO.getThumbs())
      .set("summary",bookBO.getSummary())
      .set("labels",bookBO.getLabels())
      .set("publisher",bookBO.getPublisher())
      .set("publication_time",bookBO.getPublicationTime())
      .set("del_status",bookBO.getDelStatus())
      .set("update_time",bookBO.getUpdateTime());

    UpdateResult updateResult = mongoTemplate.updateFirst(query, update, BookEntity.class);
    return updateResult.getModifiedCount();
  }

  @Override
  public Long deleteBookById(List<Long> bookIds) {
    Query query = new Query().addCriteria(Criteria.where("id").in(bookIds));
    Update update = new Update().set("del_status",ConstantDeleteStatus.DELETED.value());
    UpdateResult updateResult = mongoTemplate.updateFirst(query, update, BookEntity.class);
    return updateResult.getModifiedCount();
  }

  @Override
  public ObjectList<BookBO> listBook(Integer pageIndex, Integer pageSize) {
    Query query = new Query().with(PageRequest.of(pageIndex, pageSize));
    List<BookEntity> bookEntities = mongoTemplate.find(query, BookEntity.class);
    long count = mongoTemplate.count(query, BookEntity.class);
    ObjectList<BookBO> result = new ObjectList<>();
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
  public ObjectList<ArticleBO> listBookTable(Long bookId, Integer index, Integer size) {
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
    ObjectList<ArticleBO> result = new ObjectList<>();
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
}
