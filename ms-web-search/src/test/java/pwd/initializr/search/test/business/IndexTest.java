package pwd.initializr.search.test.business;

import com.netflix.discovery.converters.Auto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.search.persistence.entity.ArticleDocument;
import pwd.initializr.search.persistence.entity.BookDocument;

/**
 * pwd.initializr.search.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-11 22:36
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class IndexTest {

  @Autowired
  private ElasticsearchTemplate elasticsearchTemplate;
  @Test
  public void listIndex(){

  }

  @Test
  public void createIndex(){
    elasticsearchTemplate.createIndex(ArticleDocument.class);
    elasticsearchTemplate.putMapping(ArticleDocument.class);
    elasticsearchTemplate.createIndex(BookDocument.class);
    elasticsearchTemplate.putMapping(BookDocument.class);
  }

  @Test
  public void deleteIndex(){
//    elasticsearchTemplate.deleteIndex(ArticleDocument.class);
    elasticsearchTemplate.deleteIndex("book");
//    elasticsearchTemplate.deleteIndex(BookDocument.class);
  }
}
