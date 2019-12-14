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
 * @author DingPengwei[dingpengwei@eversec.com]
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
    String filePath = "/Users/pwd/Downloads/金庸小说全集三联版txt/射雕英雄传.txt";
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),Charset.forName("GB18030")));
    String line;
    List<ArticleBO> articleEntities = new LinkedList<>();
    ArticleBO currentArticleEntity = new ArticleBO();
    currentArticleEntity.setBookId(1L);
    currentArticleEntity.setTitle("序");
    currentArticleEntity.setParagraphs(new LinkedList<>());
    articleEntities.add(currentArticleEntity);
    while ((line = bufferedReader.readLine()) != null) {
      if (line.contains("回　")){
        System.out.println(line);
        currentArticleEntity = new ArticleBO();
        currentArticleEntity.setBookId(1L);
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
