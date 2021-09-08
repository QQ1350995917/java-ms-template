package pwd.initializr.book.test.test;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.book.business.admin.ArticleService;
import pwd.initializr.book.business.admin.BookService;
import pwd.initializr.book.business.admin.bo.ArticleBO;
import pwd.initializr.book.business.admin.bo.BookBO;
import pwd.initializr.book.persistence.entity.ArticleEntity;

/**
 * pwd.initializr.book.test.test@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-09-05 18:00
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

  @Autowired
  private ArticleService articleService;
  @Autowired
  private BookService bookService;
  @Autowired
  private MongoTemplate mongoTemplate;

  @org.junit.Test
  public void change() throws Exception {
    Query query = new Query();

    List<LinkedHashMap> all = mongoTemplate.find(query, LinkedHashMap.class, "book");

    for (LinkedHashMap item : all) {
      LinkedHashMap map = (LinkedHashMap)item.get("articles");
      LinkedHashSet<ArticleEntity> articles = new LinkedHashSet<>();
      for (Object o : map.keySet()) {
        Long key = Long.parseLong((String)o);
        String value = map.get(o).toString();
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setId(key);
        articleEntity.setTitle(value);
        articles.add(articleEntity);
      }

      BookBO bookBO = new BookBO();
      bookBO.setId((long)item.get("_id"));
      bookBO.setTitle(item.get("title").toString());
      bookBO.setAuthorName(item.get("author_name") == null ? "": item.get("author_name").toString());
      bookBO.setDelStatus(-1);
      bookBO.setVisibility(1);
      bookBO.setArticles(articles);
      System.out.println();
      bookService.updateBook(bookBO);
    }

  }

}
