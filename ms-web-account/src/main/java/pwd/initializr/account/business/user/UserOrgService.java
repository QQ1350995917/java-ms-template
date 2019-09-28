package pwd.initializr.account.business.user;

import java.util.List;
import pwd.initializr.account.business.user.bo.UserOrg;
import pwd.initializr.account.persistence.dao.UserOrgEntity;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-28 23:11
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface UserOrgService {

  void addUserToOrg(UserOrg userOrg);

  void updateLevel(Long userId, Long orgId, Integer level);

  void updateStatus(Long userId, Long orgId, Integer status);

  UserOrg findOne(Long userId, Long orgId);

  ObjectList<UserOrg> listByOrgId(Long orgId, Integer status);

  ObjectList<UserOrg> listByUserId(Long userId, Integer status);
}
