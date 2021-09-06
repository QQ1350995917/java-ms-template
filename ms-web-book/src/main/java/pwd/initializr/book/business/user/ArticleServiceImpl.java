package pwd.initializr.book.business.user;

import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pwd.initializr.book.business.remote.SearchClientService;
import pwd.initializr.book.business.remote.bo.SearchResultBO;
import pwd.initializr.book.business.user.bo.ArticleBO;
import pwd.initializr.book.business.user.bo.SearchInputBO;
import pwd.initializr.book.persistence.entity.ArticleEntity;
import pwd.initializr.common.web.api.vo.Output;
import pwd.initializr.common.web.business.bo.PageableQueryResult;

/**
 * pwd.initializr.book.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-12-14 22:55
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service("userArticleService")
public class ArticleServiceImpl implements ArticleService {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Autowired
  private SearchClientService searchClientService;

  @Override
  public ArticleBO findArticleById(Long articleId) {
    Document queryObject = new Document();
    queryObject.put("id", articleId);

    Query query = new BasicQuery(queryObject);

    ArticleEntity articleEntity = mongoTemplate.findOne(query, ArticleEntity.class);

    ArticleBO articleBO = new ArticleBO();
    BeanUtils.copyProperties(articleEntity, articleBO);
    return articleBO;
  }

  @Override
  public PageableQueryResult<ArticleBO> listArticleByRange(Long index, Long size) {
    Pageable pageable = PageRequest.of(index.intValue(), size.intValue());
//    Sort sort = new Sort(Direction.DESC, "update_time");
    Query query = new Query(
//            Criteria.where("status").gt("0")
    ).with(pageable)
//            .with(sort)
        ;
    long count = mongoTemplate.count(query, ArticleEntity.class);
    List<ArticleEntity> articleEntities = mongoTemplate.find(query, ArticleEntity.class);
    List<ArticleBO> articleBOS = new LinkedList<>();
    articleEntities.forEach(articleEntity -> {
      ArticleBO articleBO = new ArticleBO();
      BeanUtils.copyProperties(articleEntity, articleBO);
      articleBOS.add(articleBO);
    });
    PageableQueryResult<ArticleBO> result = new PageableQueryResult<>();
    result.setElements(articleBOS);
    result.setTotal(count);
    return result;
  }

  @Override
  public PageableQueryResult<SearchResultBO> searchArticleByRange(SearchInputBO searchInputBO) {
    SearchInputBO tempSearchInputBO;
    if (searchInputBO == null) {
      tempSearchInputBO = new SearchInputBO();
    } else {
      tempSearchInputBO = searchInputBO;
    }

    Output<PageableQueryResult<SearchResultBO>> objectListOutput = new Output<>();
//    Output<PageableQueryResult<SearchResultBO>> objectListOutput = searchClientService
//        .search(Arrays.asList(new String[]{"article"}),tempSearchInputBO.getKeyword(), tempSearchInputBO.getIndex(),
//            tempSearchInputBO.getSize());

    if (objectListOutput.getMeta().getCode() == HttpStatus.OK.value()) {
      return objectListOutput.getData();
    } else {
      return null;
    }
  }
}
