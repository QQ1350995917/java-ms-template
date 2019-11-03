package pwd.initializr.organization.business.user;

import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.organization.business.user.bo.OrganizationMember;

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
public interface OrganizationMemberService {

  void addMemToOrg(OrganizationMember userOrg);

  void updateMemLevel(Long userId, Long orgId, Integer level);

  void updateMemStatus(Long userId, Long orgId, Integer status);

  OrganizationMember findOneMemInOrg(Long orgId,Long memId);

  ObjectList<OrganizationMember> listByOrgId(Long orgId, Integer status);

  ObjectList<OrganizationMember> listByMemId(Long medId, Integer status);
}
