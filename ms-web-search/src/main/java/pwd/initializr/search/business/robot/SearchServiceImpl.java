package pwd.initializr.search.business.robot;

import java.util.Arrays;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

/**
 * pwd.initializr.search.business.robot@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-06-15 00:17
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class SearchServiceImpl implements SearchService {


  @Autowired
  private ElasticsearchTemplate elasticsearchTemplate;

  @Override
  public void search() {

  }

  private void search(QueryBuilder query, String... index) {
    Client client = elasticsearchTemplate.getClient();
    SearchResponse searchResponse = client.prepareSearch(index)
        .setTypes(index)
        .setQuery(query)
        .setFrom(0)
        .setSize(5)
        .get();
    //查询命中缓存
    SearchHits searchHits = searchResponse.getHits();
    System.out.println("查询结果总记录数：" + searchHits.getTotalHits());

    Arrays.stream(searchHits.getHits()).forEach(doc -> System.out.println(doc.getSourceAsString()));

  }
}
