package pwd.initializr.account.business.admin;

import pwd.initializr.account.business.admin.bo.Admin;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * pwd.initializr.account.business.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-04 18:41
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface AdminService {
  ObjectList<Admin> listAdmin();

  void createAdmin(Admin admin);

}