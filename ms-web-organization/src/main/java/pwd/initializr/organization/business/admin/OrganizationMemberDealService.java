package pwd.initializr.organization.business.admin;

import org.springframework.stereotype.Service;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.organization.business.admin.bo.OrganizationMemberDeal;

/**
 * pwd.initializr.organization.business.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-08 18:35
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public interface OrganizationMemberDealService {

  ObjectList<OrganizationMemberDeal> listByOrgId(Long orgId, Integer type);

  ObjectList<OrganizationMemberDeal> listByUserId(Long userId, Integer type);
}
