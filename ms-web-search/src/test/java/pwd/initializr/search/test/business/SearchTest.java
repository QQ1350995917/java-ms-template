package pwd.initializr.search.test.business;

import java.util.Map;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.common.web.business.bo.ObjectList;
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

  @Autowired
  private BookService bookService;


  @Test
  public void searchBookTest() {
    String keyword = "鹿鼎记";
    ObjectList<Map<String, Object>> search = bookService.search(keyword, 0, 12);
    System.out.println();
  }

  @Test
  public void searchTest() {
    String keyword = "鹿鼎记";
    QueryBuilder query = QueryBuilders.boolQuery()
        .should(QueryBuilders.matchQuery("title", keyword))
        .should(QueryBuilders.matchQuery("labels", keyword))
        .should(QueryBuilders.matchQuery("paragraphs", keyword));

    HighlightBuilder highlightBuilder = new HighlightBuilder(); //生成高亮查询器
    highlightBuilder.field("title");      //高亮查询字段
    highlightBuilder.field("labels");    //高亮查询字段
    highlightBuilder.field("paragraphs");    //高亮查询字段
    highlightBuilder.requireFieldMatch(false);     //如果要多个字段高亮,这项要为false
    highlightBuilder.preTags("<span style=\"color:red\">");   //高亮设置
    highlightBuilder.postTags("</span>");
    //下面这两项,如果你要高亮如文字内容等有很多字的字段,必须配置,不然会导致高亮不全,文章内容缺失等
    highlightBuilder.fragmentSize(80); //最大高亮分片数
    highlightBuilder.numOfFragments(0); //从第一个分片获取高亮片段
    FieldSortBuilder sort = SortBuilders.fieldSort("id").order(SortOrder.DESC);
    Client client = elasticsearchTemplate.getClient();
    SearchResponse searchResponse = client
        .prepareSearch("article", "book")
        .setQuery(query)
        .addSort(new ScoreSortBuilder())   //根据查询相关度进行排序
        .addSort(sort)
        .setTrackScores(true)             //避免分页之后相关性乱了
        .highlighter(highlightBuilder)     //配置高亮
        .setFrom(0)
        .setSize(2048)
        .execute()
        .actionGet();

    if (searchResponse.status() == RestStatus.OK) {
      SearchHits searchHits = searchResponse.getHits();
      for (SearchHit hit : searchHits) {
        Map<String, HighlightField> highlightFields = hit.getHighlightFields();
        HighlightField title = highlightFields.get("title");
        HighlightField labels = highlightFields.get("labels");
        HighlightField paragraphs = highlightFields.get("paragraphs");

        Map<String, Object> source = hit.getSourceAsMap();

        //千万记得要记得判断是不是为空,不然你匹配的第一个结果没有高亮内容,那么就会报空指针异常,这个错误一开始真的搞了很久
        if (title != null) {
          Text[] fragments = title.fragments();
          String name = "";
          for (Text text : fragments) {
            name += text;
          }
          source.put("title", name);   //高亮字段替换掉原本的内容
        }
        if (labels != null) {
          Text[] fragments = labels.fragments();
          String name = "";
          for (Text text : fragments) {
            name += text;
          }
          source.put("labels", name);   //高亮字段替换掉原本的内容
        }
        if (paragraphs != null) {
          Text[] fragments = paragraphs.fragments();
          String name = "";
          for (Text text : fragments) {
            name += text;
          }
          source.put("paragraphs", name);   //高亮字段替换掉原本的内容
        }
      }

    } else {
      System.out.println(searchResponse.status());
    }
    client.close();
  }
}
