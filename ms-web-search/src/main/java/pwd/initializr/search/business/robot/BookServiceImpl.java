package pwd.initializr.search.business.robot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwd.initializr.search.business.robot.bo.ArticleBO;
import pwd.initializr.search.business.robot.bo.BookBO;
import pwd.initializr.search.persistence.dao.ArticleRepository;
import pwd.initializr.search.persistence.dao.BookRepository;

/**
 * pwd.initializr.search.business.robot@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-13 14:29
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class BookServiceImpl implements BookService {

  @Autowired
  private BookRepository bookRepository;
  @Autowired
  private ArticleRepository articleRepository;

  @Override
  public BookBO postOrPutBook(BookBO bookBO) {
    BookBO save = bookRepository.save(bookBO);
    return save;
  }

  @Override
  public ArticleBO postOrPutArticle(ArticleBO articleBO) {
    ArticleBO save = articleRepository.save(articleBO);
    return save;
  }

  @Override
  public void searchBook() {

  }

  @Override
  public void searchArticle() {

  }

  @Override
  public void searchAll() {

  }
}
