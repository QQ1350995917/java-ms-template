package pwd.initializr.search.business.robot;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.search.business.robot.bo.ArticleBO;
import pwd.initializr.search.business.robot.bo.BookBO;
import pwd.initializr.search.business.robot.bo.SearchOutputBO;
import pwd.initializr.search.persistence.dao.ArticleRepository;
import pwd.initializr.search.persistence.dao.BookRepository;
import pwd.initializr.search.persistence.entity.ArticleDocument;

/**
 * pwd.initializr.search.business.robot@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-13 14:29
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class BookServiceImpl implements BookService {

  private static final List<String> fields = Arrays
      .asList("subTitle", "summary", "labels", "paragraphs");
  @Autowired
  private BookRepository bookRepository;
  @Autowired
  private ArticleRepository articleRepository;
  @Autowired
  private ElasticsearchTemplate elasticsearchTemplate;
  @Value("${search.book.key.word.max.length}")
  private Integer KEY_WORLD_MAX_LENGTH = 12;

  @Override
  public BookBO postOrPutBook(BookBO bookBO) {
    BookBO save = bookRepository.save(bookBO);
    return save;
  }

  @Override
  public ArticleBO postOrPutArticle(ArticleBO articleBO) {
    ArticleBO save = articleRepository.save(articleBO);
    return save;
  }

  @Override
  public ObjectList<SearchOutputBO> search(String keyword, Integer pageIndex, Integer pageSize) {
    if (keyword.length() > KEY_WORLD_MAX_LENGTH) {
      keyword = keyword.substring(0, KEY_WORLD_MAX_LENGTH);
    }
    final String KEY_WORD = keyword;
    ObjectList<SearchOutputBO> searchResultBOObjectList = new ObjectList<>();

    BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
    // should相当于or关系; filter过滤;must相当于and关系; mustNot不包含
    // termQuery的机制是：直接去匹配token。
    // .should(QueryBuilders.termQuery("", "")
    // matchQuery的机制是：先检查字段类型是否是analyzed，如果是，则先分词，再去去匹配；如果不是，则直接去匹配
    fields.forEach(field -> boolQuery.should(QueryBuilders.matchQuery(field, KEY_WORD)));

    //生成高亮查询器
    HighlightBuilder highlightBuilder = new HighlightBuilder();
    //高亮查询字段
    fields.forEach(field -> highlightBuilder.field(field));
    //如果要多个字段高亮,这项要为false
    highlightBuilder.requireFieldMatch(false);
    //高亮设置
    highlightBuilder.preTags("<span style=\"color:red\">");
    highlightBuilder.postTags("</span>");
    //下面这两项,如果你要高亮如文字内容等有很多字的字段,必须配置,不然会导致高亮不全,文章内容缺失等
    //最大高亮分片数
    highlightBuilder.fragmentSize(80);
    //从第一个分片获取高亮片段
    highlightBuilder.numOfFragments(0);
    FieldSortBuilder sort = SortBuilders.fieldSort("score").order(SortOrder.DESC);
    ScoreSortBuilder scoreSortBuilder = new ScoreSortBuilder();
    Client client = elasticsearchTemplate.getClient();
    SearchResponse searchResponse = client
        .prepareSearch("book", "article")
        .setQuery(boolQuery)
        //根据查询相关度进行排序
        .addSort(sort)
        //避免分页之后相关性乱了
        .setTrackScores(true)
        //配置高亮
        .highlighter(highlightBuilder)
        .setFrom(pageIndex)
        .setSize(pageSize)
        .execute()
        .actionGet();

    if (searchResponse.status() == RestStatus.OK) {
      SearchHits searchHits = searchResponse.getHits();
      for (SearchHit hit : searchHits) {
        Map<String, HighlightField> highlightFields = hit.getHighlightFields();
        Map<String, Object> source = hit.getSourceAsMap();
        StringBuilder stringBuilder = new StringBuilder();
        fields.forEach(field -> {
          HighlightField highlightField = highlightFields.get(field);
          if (highlightField != null) {
            Text[] fragments = highlightField.fragments();
            for (Text text : fragments) {
              if (stringBuilder.length() < 512) {
                stringBuilder.append("#");
                stringBuilder.append(text);
                stringBuilder.append("#");
              }
            }
          }
        });
        String summary = stringBuilder.toString();
        SearchOutputBO searchResultBO = new SearchOutputBO();
        searchResultBO
            .setEsType(source.get("esType") == null ? null : source.get("esType").toString());
        searchResultBO
            .setEsTitle(source.get("esTitle") == null ? null : source.get("esTitle").toString());
        searchResultBO.setEsSummary(summary);
        searchResultBO
            .setEsLinkTo(source.get("esLinkTo") == null ? null : source.get("esLinkTo").toString());
        searchResultBO.setEsUpdateTime(source.get("esUpdateTime") == null ? null
            : new Date(Long.valueOf(source.get("esUpdateTime").toString())));
        searchResultBOObjectList.getElements().add(searchResultBO);
      }
      searchResultBOObjectList.setIndex(pageIndex.longValue());
      searchResultBOObjectList.setSize(pageSize.longValue());
      searchResultBOObjectList.setTotal(searchHits.totalHits);
    } else {
      System.out.println(searchResponse.status());
    }
//    client.close();
    return searchResultBOObjectList;

  }

  public void search2(String keyword, Integer pageIndex, Integer pageSize) {

    NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
    BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
    queryBuilder.withQuery(boolQuery);
    queryBuilder.withHighlightFields(
        new HighlightBuilder.Field("title"),
        new HighlightBuilder.Field("subTitle"),
        new HighlightBuilder.Field("authorName"),
        new HighlightBuilder.Field("summary"),
        new HighlightBuilder.Field("labels"),
        new HighlightBuilder.Field("paragraphs"),
        new HighlightBuilder.Field("createTime"),
        new HighlightBuilder.Field("updateTime")
    );

//     withFilter过滤匹配上的 geoDistanceQuery距离搜索 location mapper字段名 point(double lat, double lon) lat维度 lon经度
//    　　　　　distance(String distance, DistanceUnit unit) distance距离 unit单位
    queryBuilder.withFilter(QueryBuilders.geoDistanceQuery("location").point(1, 1).distance("3", DistanceUnit.KILOMETERS));
//     withQuery 正常查询按命中率排序 rangeQuery(String name)区间搜索 name mapper你要搜索的字段 get大于等于 ge大于 lte小于等于 lt小于
    queryBuilder.withQuery(QueryBuilders.rangeQuery("price").gte(100).lte(500));
//     withSort排序 fieldSort(String field) field排序的字段 order(SortOrder order)降序还是升序
    queryBuilder.withSort(SortBuilders.fieldSort("SystemSort").order(SortOrder.DESC));
//     分页  PageRequest of(int page, int size) 当前页 条数 es第一页是0不是1
    queryBuilder.withPageable(PageRequest.of(pageIndex, pageSize));
//     结果
     AggregatedPage<ArticleDocument> articleDocuments = elasticsearchTemplate
         .queryForPage(queryBuilder.build(), ArticleDocument.class);

     List<ArticleDocument> content = articleDocuments.getContent();

     content.forEach(articleDocument -> {
       ArticleBO articleBO = new ArticleBO();
       BeanUtils.copyProperties(articleDocument, articleBO);
//       articleBOS.add(articleBO);
     });
     long totalElements = articleDocuments.getTotalElements();
     Integer totalPages = articleDocuments.getTotalPages();

//     articleBOObjectList.setSize(pageSize.longValue());
//     articleBOObjectList.setIndex(pageIndex.longValue());
//     articleBOObjectList.setPages(totalPages.longValue());
//     articleBOObjectList.setTotal(totalElements);
//     articleBOObjectList.setElements(articleBOS);
  }
}
