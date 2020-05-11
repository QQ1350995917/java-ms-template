package pwd.initializr.search.test.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.search.business.admin.ArticleService;

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

  public static void main(String[] args) throws Exception {
    InitTest initTest = new InitTest();
    initTest.init();
  }

  @Test
  public void init() throws Exception {

  }
}
