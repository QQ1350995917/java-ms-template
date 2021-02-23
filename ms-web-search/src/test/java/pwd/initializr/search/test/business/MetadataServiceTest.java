package pwd.initializr.search.test.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.search.business.admin.MetadataService;

/**
 * pwd.initializr.search.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-06-16 15:35
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MetadataServiceTest {

    @Autowired
    private MetadataService metadataService;

    @Test
    public void list(){
        metadataService.list();
    }

    @Test
    public void create() {
        metadataService.createIndex("book");
        metadataService.createIndex("article");
    }

    @Test
    public void createByCustomMapping() {
        metadataService.createIndex("book", metadataService.getDefaultMapping());
        metadataService.createIndex("article", metadataService.getDefaultMapping());
    }
}
