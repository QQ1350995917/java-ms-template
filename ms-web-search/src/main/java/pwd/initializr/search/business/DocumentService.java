package pwd.initializr.search.business;

import java.util.List;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.search.business.bo.DocumentBO;
import pwd.initializr.search.business.bo.SearchInputBO;
import pwd.initializr.search.business.bo.SearchBodyVOBO;

/**
 * pwd.initializr.search.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-06-14 21:24
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface DocumentService {

  int create(String indexName, List<DocumentBO> documentBOS);

  PageableQueryResult<SearchBodyVOBO> search(SearchInputBO searchInputBO);
}
