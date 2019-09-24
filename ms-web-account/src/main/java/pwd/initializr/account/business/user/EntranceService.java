package pwd.initializr.account.business.user;

import java.util.List;
import pwd.initializr.account.business.user.bo.Entrance;
import pwd.initializr.account.persistence.dao.EntranceEntity;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-24 16:37
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface EntranceService {

  ObjectList<Entrance> findUserEntranceByType(Integer type);

}
