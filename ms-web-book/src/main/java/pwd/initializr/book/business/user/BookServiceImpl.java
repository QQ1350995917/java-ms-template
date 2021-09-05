package pwd.initializr.book.business.user;

import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import pwd.initializr.book.business.user.bo.ArticleAroundBO;
import pwd.initializr.book.business.user.bo.ArticleBO;
import pwd.initializr.book.business.user.bo.BookBO;
import pwd.initializr.book.persistence.entity.ArticleEntity;
import pwd.initializr.book.persistence.entity.BookEntity;
import pwd.initializr.common.web.business.bo.PageableQueryResult;

/**
 * pwd.initializr.book.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-12-14 21:30
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
  public PageableQueryResult<BookBO> listRecommendBooks(Long index, Long size) {
    Pageable pageable = PageRequest.of(index.intValue(), size.intValue());
//    Sort sort = new Sort(Direction.DESC, "update_time");
    Query query = new Query(Criteria.where("recommend").is(1).and("visibility").is(1))
        .with(pageable)
//        .with(sort)
        ;
    long count = mongoTemplate.count(query, BookEntity.class);
    List<BookEntity> bookEntities = mongoTemplate.find(query, BookEntity.class);
    List<BookBO> bookBOS = new LinkedList<>();
    bookEntities.forEach(bookEntity -> {
      BookBO bookBO = new BookBO();
      BeanUtils.copyProperties(bookEntity, bookBO);
      bookBOS.add(bookBO);
    });
    PageableQueryResult<BookBO> result = new PageableQueryResult<>();
    result.setElements(bookBOS);
    result.setIndex(index.longValue());
    result.setSize(size.longValue());
    result.setTotal(count);
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
}
