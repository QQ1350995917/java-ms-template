package pwd.initializr.search.test.business;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.search.business.admin.MetadataService;
import pwd.initializr.search.business.admin.bo.IndexBO;

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
@Slf4j
public class MetadataServiceTest {

    @Autowired
    private MetadataService metadataService;

    @Test
    public void listIndex(){
        PageableQueryResult<IndexBO> indexBOPageableQueryResult = metadataService.listIndex();
        log.info(JSON.toJSONString(indexBOPageableQueryResult));
    }

    @Test
    public void createIndex() {
        String indexName = "book";
        if (metadataService.existIndex(indexName)) {
            throw new RuntimeException("exist " + indexName);
        } else {
            metadataService.createIndex(indexName);
        }
    }

    @Test
    public void createIndexByCustomMapping() {
        String indexName = "book";
        if (metadataService.existIndex(indexName)) {
           throw new RuntimeException("exist " + indexName);
        } else {
            metadataService.createIndex("book", metadataService.getDefaultMapping());
        }

    }
}
