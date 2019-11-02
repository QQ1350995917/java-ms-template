package pwd.initializr.account.business.admin;

import pwd.initializr.account.business.admin.bo.Organization;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * pwd.initializr.account.business.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-10-12 18:35
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface OrganizationService {

  Organization createRoot(Organization organization);

  Organization getRoot();

  void updateRoot(Organization organization);

  void reviewExecution(Long id);

  void reviewRefuse(Long id);

  void reviewApprove(Long id);

  void reviewRecheck(Long id);

  ObjectList<Organization> listByPidAndStatus(Long pid, Integer status);

  ObjectList<Organization> listByStatus(Integer status);
}
