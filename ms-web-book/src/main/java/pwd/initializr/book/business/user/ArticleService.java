package pwd.initializr.book.business.user;

import org.springframework.stereotype.Service;
import pwd.initializr.book.business.remote.bo.SearchResultBO;
import pwd.initializr.book.business.user.bo.ArticleBO;
import pwd.initializr.book.business.user.bo.SearchInputBO;
import pwd.initializr.common.web.business.bo.PageableQueryResult;

/**
 * pwd.initializr.book.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-12-14 20:38
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public interface ArticleService {

  ArticleBO findArticleById(Long articleId);

  PageableQueryResult<ArticleBO> listArticleByRange(Long index, Long size);

  PageableQueryResult<SearchResultBO> searchArticleByRange(SearchInputBO searchInputBO);

}
