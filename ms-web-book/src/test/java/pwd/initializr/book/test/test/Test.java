package pwd.initializr.book.test.test;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.book.business.admin.ArticleService;
import pwd.initializr.book.business.admin.BookService;
import pwd.initializr.book.business.admin.bo.ArticleBO;
import pwd.initializr.book.business.admin.bo.BookBO;

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

    List<LinkedHashMap> all = mongoTemplate.find(query, LinkedHashMap.class, "");

    LinkedHashMap<String, BookBO> stringBookEntityLinkedHashMap = new LinkedHashMap<>();
    for (LinkedHashMap o : all) {
      if (stringBookEntityLinkedHashMap.get(o.get("bookName").toString()) == null) {
        BookBO bookEntity = new BookBO();
        bookEntity.setArticles(new LinkedHashMap<>());
        bookEntity.setType(3);
        HashSet<String> tags = new HashSet<>();
        tags.add(o.get("typeName").toString());
        bookEntity.setTags(tags);
        bookEntity.setTitle(o.get("bookName").toString());
        bookEntity.setAuthorName(o.get("authorName").toString());
        stringBookEntityLinkedHashMap.put(o.get("bookName").toString(), bookEntity);
      }
      ArticleBO articleBO = new ArticleBO();
      articleBO.setTitle(o.get("chapterName").toString());
      articleBO.setParagraphs(((List) o.get("contents")));
      ArticleBO article = articleService.createArticle(articleBO);
      stringBookEntityLinkedHashMap.get(o.get("bookName").toString()).getArticles()
          .put(article.getId(), o.get("chapterName").toString());
    }

    for (String s : stringBookEntityLinkedHashMap.keySet()) {
      BookBO bookBO = stringBookEntityLinkedHashMap.get(s);
      bookService.createBook(bookBO);
    }
  }

}
