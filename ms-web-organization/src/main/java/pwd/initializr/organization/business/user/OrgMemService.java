package pwd.initializr.organization.business.user;

import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.organization.business.user.bo.OrgMem;

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
public interface OrgMemService {

  void addUserToOrg(OrgMem userOrg);

  void updateLevel(Long userId, Long orgId, Integer level);

  void updateStatus(Long userId, Long orgId, Integer status);

  OrgMem findOne(Long userId, Long orgId);

  ObjectList<OrgMem> listByOrgId(Long orgId, Integer status);

  ObjectList<OrgMem> listByUserId(Long userId, Integer status);
}
