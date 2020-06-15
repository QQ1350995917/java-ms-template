package pwd.initializr.search.test.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.search.business.robot.DocumentService;
import pwd.initializr.search.business.robot.bo.DocumentBO;

/**
 * pwd.initializr.search.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-06-14 23:28
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentServiceTest {

  @Autowired
  private DocumentService documentService;

  @Test
  public void create() {
    DocumentBO documentBO = new DocumentBO();
    documentBO.setEsId("1");
    documentBO.setEsVisibility("1");
    documentBO.setEsTitle("testtesttesttesttesttesttest");
    documentBO.setEsContent("testtesttesttesttesttesttesttest");
    documentBO.setEsLinkTo("http://www.github.com");
    documentBO.setEsUpdateTime("20200505");
    documentService.create("book1", documentBO);
  }

}
