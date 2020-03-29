package pwd.initializr.typeface.business;

import java.util.List;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.typeface.business.bo.PaintingBO;
import pwd.initializr.typeface.persistence.dao.PaintingEntity;

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
public interface PaintingService {

  List<Integer> deleteByIds(List<Long> ids);

  ObjectList<PaintingBO> findByCondition(PaintingBO paintingBO, Long pageIndex,
      Long pageSize);

  PaintingBO findById(Long id);

  PaintingBO save(PaintingBO paintingBO);
}
