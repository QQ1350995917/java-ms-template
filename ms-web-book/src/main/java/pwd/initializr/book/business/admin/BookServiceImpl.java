package pwd.initializr.book.business.admin;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;
import pwd.initializr.book.business.admin.bo.BookBO;
import pwd.initializr.book.persistence.entity.BookEntity;
import pwd.initializr.common.utils.DateTimeUtil;
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
  public BookBO createNewBook(BookBO bookBO) {
    BookEntity bookEntity = new BookEntity();
    BeanUtils.copyProperties(bookBO, bookEntity);
    bookEntity.setStatus(0);
    Date currentDateTime = new Date();
    bookEntity.setCreateTime(currentDateTime);
    bookEntity.setUpdateTime(currentDateTime);
    BookEntity newBookEntity = mongoTemplate.save(bookEntity);
    BeanUtils.copyProperties(newBookEntity, bookBO);
    return bookBO;
  }

  @Override
  public ObjectList<BookBO> searchBookByRange(Set<String> words, Integer pageIndex,
      Integer pageSize) {
    TextCriteria criteria = TextCriteria.forDefaultLanguage();
    if (words != null) {
      words.forEach(word -> criteria.matchingAny(word));
    }
    Query query = TextQuery.queryText(criteria).sortByScore()
        .with(PageRequest.of(pageIndex, pageSize));
    List<BookEntity> bookEntities = mongoTemplate.find(query, BookEntity.class);
    ObjectList<BookBO> result = new ObjectList<>();
    List<BookBO> bookBOS = new LinkedList<>();
    for (BookEntity bookEntity : bookEntities) {
      BookBO bookBO = new BookBO();
      BeanUtils.copyProperties(bookEntity, bookBO);
      bookBOS.add(bookBO);
    }
    result.setElements(bookBOS);
    return result;
  }

}
