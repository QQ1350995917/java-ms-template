package pwd.initializr.organization.business.user;

import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.organization.business.user.bo.OrganizationMemberDeal;

/**
 * pwd.initializr.organization.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-07 13:04
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface OrganizationMemberDealService {

  void create(OrganizationMemberDeal organizationMemberDeal);

  OrganizationMemberDeal findOneById(Long id);

  OrganizationMemberDeal findOneByOrgIdUserIdType(Long orgId, Long userId, Integer type);

  PageableQueryResult<OrganizationMemberDeal> listByOrgId(Long orgId, Integer type);

  PageableQueryResult<OrganizationMemberDeal> listByUserId(Long userId, Integer type);

  void updateCounterById(Long orgId, Long userId, Integer type);

  void deal(Long dealId);


}
