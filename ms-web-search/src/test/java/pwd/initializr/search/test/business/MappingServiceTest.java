package pwd.initializr.search.test.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.search.business.robot.IndexService;
import pwd.initializr.search.business.robot.MappingService;

/**
 * pwd.initializr.search.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-06-14 21:58
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MappingServiceTest {

  @Autowired
  private IndexService indexService;

  @Autowired
  private MappingService mappingService;

  @Test
  public void createMapping() {
    String index = "book1";
    indexService.create(index);
    mappingService.create(index, mappingService.getDefaultMapping());
  }
}
