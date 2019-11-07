package pwd.initializr.organization.business.user;

import org.apache.ibatis.annotations.Param;
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

  OrganizationMemberDeal findOne(@Param("orgId") Long orgId,
      @Param("userId") Long userId, @Param("type") Integer type);

  void create(OrganizationMemberDeal organizationMemberDeal);

  void updateCounterById(Long id);

  ObjectList<OrganizationMemberDeal> listByOrgId(@Param("orgId") Long orgId,
      @Param("type") Integer type);

  ObjectList<OrganizationMemberDeal> listByUserId(@Param("userId") Long userId,
      @Param("type") Integer type);
}
