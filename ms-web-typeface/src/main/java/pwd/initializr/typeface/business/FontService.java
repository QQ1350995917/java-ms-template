package pwd.initializr.typeface.business;

import java.util.LinkedHashSet;
import org.springframework.stereotype.Service;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.typeface.business.bo.FontBO;

/**
 * pwd.initializr.typeface.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-29 15:21
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public interface FontService {

  PageableQueryResult<FontBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
      LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize);

  FontBO findById(Long id);

  FontBO save(FontBO fontBO);
}
