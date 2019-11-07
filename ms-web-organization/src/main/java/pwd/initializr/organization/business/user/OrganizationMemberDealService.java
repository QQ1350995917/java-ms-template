package pwd.initializr.organization.business.user;

import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.organization.business.user.bo.OrganizationMemberDeal;

/**
 * pwd.initializr.organization.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-07 13:04
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface OrganizationMemberDealService {

  void create(OrganizationMemberDeal organizationMemberDeal);

  OrganizationMemberDeal findOne(Long orgId, Long userId, Integer type);

  ObjectList<OrganizationMemberDeal> listByOrgId(Long orgId, Integer type);

  ObjectList<OrganizationMemberDeal> listByUserId(Long userId, Integer type);

  void updateCounterById(Long orgId, Long userId, Integer type);


}
