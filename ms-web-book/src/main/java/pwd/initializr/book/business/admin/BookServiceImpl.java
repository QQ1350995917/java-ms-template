package pwd.initializr.book.business.admin;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import pwd.initializr.book.business.admin.bo.BookBO;
import pwd.initializr.book.persistence.entity.BookEntity;
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
    BeanUtils.copyProperties(bookBO,bookEntity);
    BookEntity newBookEntity = mongoTemplate.save(bookEntity);
    BeanUtils.copyProperties(newBookEntity,bookBO);
    return bookBO;
  }

  @Override
  public ObjectList<BookBO> listBookByRange() {
    List<BookEntity> bookEntities = mongoTemplate.findAll(BookEntity.class);
    ObjectList<BookBO> result = new ObjectList<>();
    List<BookBO> bookBOS = new LinkedList<>();
    for (BookEntity bookEntity : bookEntities) {
      BookBO bookBO = new BookBO();
      BeanUtils.copyProperties(bookEntity,bookBO);
      bookBOS.add(bookBO);
    }
    result.setElements(bookBOS);
    return result;
  }

}
