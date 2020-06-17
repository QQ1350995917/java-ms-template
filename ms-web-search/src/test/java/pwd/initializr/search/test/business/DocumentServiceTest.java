package pwd.initializr.search.test.business;

import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.search.business.robot.DocumentService;
import pwd.initializr.search.business.robot.bo.DocumentBO;
import pwd.initializr.search.business.robot.bo.SearchInputBO;

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

  @Test
  public void search(){
    SearchInputBO searchInputBO = new SearchInputBO();
    searchInputBO.setIndex(0);
    searchInputBO.setSize(120);
    searchInputBO.setKeyword("鹿鼎记");
    searchInputBO.setIndices(Arrays.asList("book","article"));
    documentService.search(searchInputBO);
  }

}
