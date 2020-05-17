package pwd.initializr.search.business.robot;

import java.util.LinkedList;
import java.util.List;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
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
  public ObjectList<ArticleBO> search(String keyword, Integer pageIndex, Integer pageSize) {
    if (keyword.length() > KEY_WORLD_MAX_LENGTH) {
      keyword = keyword.substring(0, KEY_WORLD_MAX_LENGTH);
    }

    NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

    // boolean查询,用于多条件
    BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
    // should相当于or关系; termQuery的机制是：直接去匹配token。
//    boolQuery.should(QueryBuilders.termQuery("", ""));
    boolQuery.should(QueryBuilders.matchQuery("title", keyword));
    boolQuery.should(QueryBuilders.matchQuery("subTitle", keyword));
    boolQuery.should(QueryBuilders.matchQuery("authorName", keyword));
    boolQuery.should(QueryBuilders.matchQuery("summary", keyword));
    boolQuery.should(QueryBuilders.matchQuery("labels", keyword));
    boolQuery.should(QueryBuilders.matchQuery("paragraphs", keyword));
    boolQuery.should(QueryBuilders.matchQuery("createTime", keyword));
    boolQuery.should(QueryBuilders.matchQuery("updateTime", keyword));
    // matchQuery的机制是：先检查字段类型是否是analyzed，如果是，则先分词，再去去匹配；如果不是，则直接去匹配
    // boolQuery.should(QueryBuilders.matchQuery("",""));
    // boolQuery.filter(); // filter过滤
    // boolQuery.must(); // must相当于and关系
    // boolQuery.mustNot(); // mustNot不包含
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
//
    // withFilter过滤匹配上的 geoDistanceQuery距离搜索 location mapper字段名 point(double lat, double lon) lat维度 lon经度
    //　　　　　distance(String distance, DistanceUnit unit) distance距离 unit单位
    //queryBuilder.withFilter(QueryBuilders.geoDistanceQuery("location").point(1, 1).distance("3", DistanceUnit.KILOMETERS));
    // withQuery 正常查询按命中率排序 rangeQuery(String name)区间搜索 name mapper你要搜索的字段 get大于等于 ge大于 lte小于等于 lt小于
    //queryBuilder.withQuery(QueryBuilders.rangeQuery("price").gte(100).lte(500));
    // withSort排序 fieldSort(String field) field排序的字段 order(SortOrder order)降序还是升序
//    queryBuilder.withSort(SortBuilders.fieldSort("SystemSort").order(SortOrder.DESC));
    // 分页  PageRequest of(int page, int size) 当前页 条数 es第一页是0不是1
    queryBuilder.withPageable(PageRequest.of(pageIndex, pageSize));
    // 结果
    AggregatedPage<ArticleDocument> articleDocuments = elasticsearchTemplate
        .queryForPage(queryBuilder.build(), ArticleDocument.class);

    List<ArticleDocument> content = articleDocuments.getContent();
    List<ArticleBO> articleBOS = new LinkedList<>();
    content.forEach(articleDocument -> {
      ArticleBO articleBO = new ArticleBO();
      BeanUtils.copyProperties(articleDocument, articleBO);
      articleBOS.add(articleBO);
    });
    long totalElements = articleDocuments.getTotalElements();
    Integer totalPages = articleDocuments.getTotalPages();
    ObjectList<ArticleBO> articleBOObjectList = new ObjectList<>();
    articleBOObjectList.setSize(pageSize.longValue());
    articleBOObjectList.setIndex(pageIndex.longValue());
    articleBOObjectList.setPages(totalPages.longValue());
    articleBOObjectList.setTotal(totalElements);
    articleBOObjectList.setElements(articleBOS);
    return articleBOObjectList;

  }
}
