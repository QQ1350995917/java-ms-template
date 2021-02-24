package pwd.initializr.search.business;

import com.alibaba.fastjson.JSON;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.search.business.bo.DocumentBO;
import pwd.initializr.search.business.bo.SearchBodyVOBO;
import pwd.initializr.search.business.bo.SearchInputBO;

/**
 * pwd.initializr.search.business.robot@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-06-14 21:29
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class DocumentServiceImpl implements DocumentService {

  private static final List<String> fields = Arrays.asList("esTitle", "esContent");
  @Autowired
  private ElasticsearchRestTemplate elasticsearchRestTemplate;
  @Value("${search.query.key.word.max.length:120}")
  private Integer queryKeyWorldMaxLength;

  @Override
  public int create(String indexName, List<DocumentBO> documentBOS) {
    try {
      List<IndexQuery> queries = documentBOS.stream().map(item->{
          IndexQuery query = new IndexQuery();
          query.setIndexName(indexName);
          query.setType(indexName);
          query.setSource(JSON.toJSONString(item));
          return query;
      }).collect(Collectors.toList());

      elasticsearchRestTemplate.bulkIndex(queries);
      return 0;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


  @Override
    public PageableQueryResult<SearchBodyVOBO> search(SearchInputBO searchInputBO) {
      // 查询条件
      String keyword = searchInputBO.getKeyword();
      if (keyword == null) {
          return null;
      }
      if (keyword.length() > queryKeyWorldMaxLength) {
          keyword = keyword.substring(0, queryKeyWorldMaxLength);
      }
      final String KEY_WORD = keyword;
      BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
      // should相当于or关系; filter过滤;must相当于and关系; mustNot不包含
      // termQuery的机制是：直接去匹配token。
      // .should(QueryBuilders.termQuery("", "")
      // matchQuery的机制是：先检查字段类型是否是analyzed，如果是，则先分词，再去去匹配；如果不是，则直接去匹配
      fields.forEach(field -> boolQueryBuilder.should(QueryBuilders.matchQuery(field, KEY_WORD)));

      // 查询排序
      ScoreSortBuilder scoreSortBuilder = SortBuilders.scoreSort().order(SortOrder.DESC);

      // 查询分页
      Integer pageIndex = searchInputBO.getIndex();
      Integer pageSize = searchInputBO.getSize();
      if (pageIndex <= 0) {
          pageIndex = 0;
      }

      // 查询生成高亮查询器
      HighlightBuilder highlightBuilder = new HighlightBuilder();
      //高亮查询字段
      fields.forEach(field -> highlightBuilder.field(field));
      //如果要多个字段高亮,这项要为false
      highlightBuilder.requireFieldMatch(false);
      //高亮设置
      highlightBuilder.preTags(searchInputBO.getPreTags()).postTags(searchInputBO.getPostTags());
      //最大高亮分片数
//    highlightBuilder.fragmentSize(80);
      //从第一个分片获取高亮片段
//    highlightBuilder.numOfFragments(0);
//    highlightBuilder.noMatchSize(100);


      SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
      // 设置查询条件
      searchSourceBuilder.query(boolQueryBuilder);
      // 设置排序条件
      searchSourceBuilder.sort(scoreSortBuilder);
      // 设置查询分页
      searchSourceBuilder.from((pageIndex) * pageSize);
      searchSourceBuilder.size(pageSize);
      // 避免分页之后相关性乱了
      searchSourceBuilder.trackScores(true);
      // 设置查询高亮
      searchSourceBuilder.highlighter(highlightBuilder);




      try {
          SearchRequest searchRequest = new SearchRequest(searchInputBO.getIndices().toArray(new String[]{}),searchSourceBuilder);
          RestHighLevelClient highLevelClient = elasticsearchRestTemplate.getClient();
          PageableQueryResult<SearchBodyVOBO> searchResultBOPageableQueryResult = new PageableQueryResult<>();
          SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
          if (searchResponse.status() == RestStatus.OK) {
              SearchHits searchHits = searchResponse.getHits();
              for (SearchHit hit : searchHits) {
                  Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                  Map<String, Object> source = hit.getSourceAsMap();
                  LinkedList<String> esContent = new LinkedList<>();
                  HighlightField highlightESTitle = highlightFields.get("esTitle");
                  if (highlightESTitle != null) {
                      Text[] fragments = highlightESTitle.fragments();
                      StringBuilder esTitleFragmentsStringBuilder = new StringBuilder();
                      for (Text text : fragments) {
                          esTitleFragmentsStringBuilder.append(text);
                      }
                      if (esTitleFragmentsStringBuilder.length() > 0) {
                          source.put("esTitle",esTitleFragmentsStringBuilder.toString());
                      }
                  }

                  HighlightField highlightESContent = highlightFields.get("esContent");
                  if (highlightESContent != null) {
                      Text[] fragments = highlightESContent.fragments();
                      for (Text text : fragments) {
                          if (esContent.size() < 5) {
                              esContent.add("..." + text + "...");
                          } else {
                              break;
                          }
                      }
                  }
                  SearchBodyVOBO searchResultBO = new SearchBodyVOBO();
                  searchResultBO.setEsIndex(hit.getIndex());
                  searchResultBO.setEsId(hit.getId());
                  searchResultBO.setEsVisibility(
                      source.get("esVisibility") == null ? null : source.get("esVisibility").toString());
                  searchResultBO
                      .setEsTitle(source.get("esTitle") == null ? null : source.get("esTitle").toString());
                  searchResultBO.setEsContent(esContent);
                  searchResultBO
                      .setEsLinkTo(source.get("esLinkTo") == null ? null : source.get("esLinkTo").toString());
                  searchResultBO.setEsUpdateTime(source.get("esUpdateTime") == null ? null
                      : source.get("esUpdateTime").toString());
                  searchResultBOPageableQueryResult.getElements().add(searchResultBO);
              }
              searchResultBOPageableQueryResult.setIndex(pageIndex.longValue());
              searchResultBOPageableQueryResult.setSize(pageSize.longValue());
              searchResultBOPageableQueryResult.setTotal(searchHits.getTotalHits());
          } else {
              System.out.println(searchResponse.status());
          }
          return searchResultBOPageableQueryResult;
      } catch (IOException e) {
          e.printStackTrace();
      }
      return null;
    }


  private void search2(String keyword, Integer pageIndex, Integer pageSize) {

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
    queryBuilder.withFilter(QueryBuilders.geoDistanceQuery("location").point(1, 1)
        .distance("3", DistanceUnit.KILOMETERS));
//     withQuery 正常查询按命中率排序 rangeQuery(String name)区间搜索 name mapper你要搜索的字段 get大于等于 ge大于 lte小于等于 lt小于
    queryBuilder.withQuery(QueryBuilders.rangeQuery("price").gte(100).lte(500));
//     withSort排序 fieldSort(String field) field排序的字段 order(SortOrder order)降序还是升序
    queryBuilder.withSort(SortBuilders.fieldSort("SystemSort").order(SortOrder.DESC));
//     分页  PageRequest of(int page, int size) 当前页 条数 es第一页是0不是1
    queryBuilder.withPageable(PageRequest.of(pageIndex, pageSize));
//     结果
//    AggregatedPage<ArticleDocument> articleDocuments = elasticsearchTemplate
//        .queryForPage(queryBuilder.build(), ArticleDocument.class);
//
//    List<ArticleDocument> content = articleDocuments.getContent();
//
//    content.forEach(articleDocument -> {
//      ArticleBO articleBO = new ArticleBO();
//      BeanUtils.copyProperties(articleDocument, articleBO);
////       articleBOS.add(articleBO);
//    });
//    long totalElements = articleDocuments.getTotalElements();
//    Integer totalPages = articleDocuments.getTotalPages();

//     articleBOObjectList.setSize(pageSize.longValue());
//     articleBOObjectList.setIndex(pageIndex.longValue());
//     articleBOObjectList.setPages(totalPages.longValue());
//     articleBOObjectList.setTotal(totalElements);
//     articleBOObjectList.setElements(articleBOS);
  }
}
