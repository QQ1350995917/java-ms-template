package pwd.initializr.book.test.business;

import com.alibaba.fastjson.JSON;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.book.BookApplication;
import pwd.initializr.book.business.remote.SearchClientService;
import pwd.initializr.book.persistence.entity.ArticleEntity;
import pwd.initializr.book.persistence.entity.BookEntity;
import pwd.initializr.book.rpc.RPCArticleIntoSearch;
import pwd.initializr.book.rpc.RPCBookIntoSearch;
import pwd.initializr.common.web.api.vo.Output;

/**
 * pwd.initializr.book.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-13 14:57
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookApplication.class)
public class PutIntoSearchTest {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Autowired
  private SearchClientService searchClientService;

  @Test
  public void copyArticle() {
    List<ArticleEntity> all = mongoTemplate.findAll(ArticleEntity.class);
    all.forEach(articleEntity -> {
      RPCArticleIntoSearch RPCArticleIntoSearch = new RPCArticleIntoSearch();
      BeanUtils.copyProperties(articleEntity, RPCArticleIntoSearch);
      RPCArticleIntoSearch.setEsAppId("BOOK-ID");
      RPCArticleIntoSearch.setEsAppName("BOOK");
      RPCArticleIntoSearch.setEsSecretKey("SECRET-KEY");
      RPCArticleIntoSearch.setEsVisibility("VISIBIE");
      RPCArticleIntoSearch.setEsTitle(articleEntity.getTitle());
      RPCArticleIntoSearch.setEsType("article");
      RPCArticleIntoSearch.setEsLinkTo(
          "http://0.0.0.0:8081/#/book/articleDetail?bookId=" + articleEntity.getBookId()
              + "&articleId=" + articleEntity.getId());
      RPCArticleIntoSearch.setEsUpdateTime(new Date());
      searchClientService.postOrPutArticle(RPCArticleIntoSearch);
    });
  }

  @Test
  public void copyBook() {
    List<BookEntity> all = mongoTemplate.findAll(BookEntity.class);

    all.forEach(bookEntity -> {
      RPCBookIntoSearch RPCBookIntoSearch = new RPCBookIntoSearch();
      BeanUtils.copyProperties(bookEntity, RPCBookIntoSearch);
      RPCBookIntoSearch.setEsAppId("BOOK-ID");
      RPCBookIntoSearch.setEsAppName("BOOK");
      RPCBookIntoSearch.setEsSecretKey("SECRET-KEY");
      RPCBookIntoSearch.setEsVisibility("VISIBIE");
      RPCBookIntoSearch.setEsTitle(bookEntity.getTitle());
      RPCBookIntoSearch.setEsType("book");
      RPCBookIntoSearch.setEsLinkTo("http://0.0.0.0:8081/#/book/bookDetail?bookId=" + bookEntity.getId());
      RPCBookIntoSearch.setEsUpdateTime(new Date());
      String postOrPutBook = searchClientService.postOrPutBook(RPCBookIntoSearch);
      Output output = JSON.parseObject(postOrPutBook, Output.class);
      System.out.println(output);
    });
  }
}
