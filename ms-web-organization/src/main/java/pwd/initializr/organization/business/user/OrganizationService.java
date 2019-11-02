package pwd.initializr.organization.business.user;


import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.organization.business.user.bo.Organization;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-27 22:37
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface OrganizationService {

  void create(Organization organization);

  void update(Organization organization);

  void reviewPending(Long id);

  ObjectList<Organization> listByPidAndStatus(Long pid, Integer status);

}
