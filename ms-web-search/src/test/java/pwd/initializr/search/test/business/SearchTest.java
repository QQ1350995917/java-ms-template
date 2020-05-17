package pwd.initializr.search.test.business;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchService;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.search.business.robot.BookService;

/**
 * pwd.initializr.search.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-17 13:10
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchTest {

  @Autowired
  private ElasticsearchTemplate elasticsearchTemplate;

  @Test
  public void test(){
    String keyword = "鹿鼎记";
    QueryBuilder query = QueryBuilders.boolQuery()
        .should(QueryBuilders.matchQuery("title", keyword))
        .should(QueryBuilders.matchQuery("labels", keyword))
        .should(QueryBuilders.matchQuery("paragraphs", keyword));

    SearchRequestBuilder searchRequestBuilder = elasticsearchTemplate.getClient()
        .prepareSearch("book", "article")
        .setQuery(query);
  }
}
