package pwd.initializr.typeface.business;

import java.util.LinkedHashSet;
import java.util.List;
import org.springframework.stereotype.Service;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.typeface.business.bo.PaintingBO;

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
public interface PaintingService {

  PaintingBO create(PaintingBO paintingBO);

  Integer deleteByIds(List<Long> ids);

  PageableQueryResult<PaintingBO> findByCondition(LinkedHashSet<ScopeBO> scopes,
      LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize);

  PaintingBO findById(Long id);
}
