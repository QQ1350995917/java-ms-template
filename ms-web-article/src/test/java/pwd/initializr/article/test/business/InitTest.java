package pwd.initializr.article.test.business;

import io.swagger.models.auth.In;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.article.business.admin.ArticleService;
import pwd.initializr.article.business.admin.bo.ArticleBO;
import pwd.initializr.article.persistence.dao.ArticleEntity;

/**
 * pwd.initializr.logger.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-19 12:47
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InitTest {

  @Autowired
  private ArticleService articleService;

  @Test
  public void init() throws Exception {
    Long bookId = 15L;
    String filePath = "/Users/pwd/Downloads/金庸小说全集三联版txt/雪山飞狐.txt";
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),Charset.forName("GB18030")));
    String line;
    List<ArticleBO> articleEntities = new LinkedList<>();
    ArticleBO currentArticleEntity = new ArticleBO();
    currentArticleEntity.setBookId(bookId);
    currentArticleEntity.setTitle("序");
    currentArticleEntity.setParagraphs(new LinkedList<>());
    articleEntities.add(currentArticleEntity);
    List<String> titles = new LinkedList<>();
    titles.add("　　一");
    titles.add("　　二");
    titles.add("　　三");
    titles.add("　　四");
    titles.add("　　五");
    titles.add("　　六");
    titles.add("　　七");
    titles.add("　　八");
    titles.add("　　九");
    while ((line = bufferedReader.readLine()) != null) {
      if (titles.contains(line)){
        System.out.println(line);
        currentArticleEntity = new ArticleBO();
        currentArticleEntity.setBookId(bookId);
        currentArticleEntity.setTitle(line.trim());
        currentArticleEntity.setParagraphs(new LinkedList<>());
        articleEntities.add(currentArticleEntity);
      } else {
        currentArticleEntity.getParagraphs().add(line);
      }
    }
    bufferedReader.close();
    for (ArticleBO articleBO : articleEntities) {
//      articleService.createNewArticle(articleBO);
    }
    System.out.println("over");
  }

  public static void main(String[] args) throws Exception {
    InitTest initTest = new InitTest();
    initTest.init();
  }
}
