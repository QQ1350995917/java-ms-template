package pwd.initializr.book.test.business;

import com.alibaba.fastjson.JSON;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.book.business.admin.SearchService;
import pwd.initializr.book.persistence.entity.ArticleEntity;
import pwd.initializr.book.persistence.entity.BookEntity;
import pwd.initializr.book.rpc.ArticleIntoSearch;
import pwd.initializr.book.rpc.BookIntoSearch;
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
@SpringBootTest
public class PutIntoSearchTest {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Autowired
  private SearchService searchService;

  @Test
  public void copyBook(){
    List<BookEntity> all = mongoTemplate.findAll(BookEntity.class);

    all.forEach(bookEntity -> {
      BookIntoSearch bookIntoSearch = new BookIntoSearch();
      BeanUtils.copyProperties(bookEntity,bookIntoSearch);
      String postOrPutBook = searchService.postOrPutBook(bookIntoSearch);
      Output output = JSON.parseObject(postOrPutBook, Output.class);
      System.out.println(output);
    });
  }

  @Test
  public void copyArticle(){
    List<ArticleEntity> all = mongoTemplate.findAll(ArticleEntity.class);
    all.forEach(articleEntity -> {
      ArticleIntoSearch articleIntoSearch = new ArticleIntoSearch();
      BeanUtils.copyProperties(articleEntity,articleIntoSearch);
      searchService.postOrPutArticle(articleIntoSearch);
    });
  }
}
