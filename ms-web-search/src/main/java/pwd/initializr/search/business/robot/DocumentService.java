package pwd.initializr.search.business.robot;

import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.search.business.robot.bo.DocumentBO;
import pwd.initializr.search.business.robot.bo.SearchInputBO;
import pwd.initializr.search.business.robot.bo.SearchOutputBO;

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

  int create(String index, DocumentBO documentBO);

  ObjectList<SearchOutputBO> search(SearchInputBO searchInputBO);
}
