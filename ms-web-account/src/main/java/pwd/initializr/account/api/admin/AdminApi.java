package pwd.initializr.account.api.admin;

import pwd.initializr.account.api.admin.vo.CreateAdminInput;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-10-26 8:13
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface AdminApi {

  void list();

  void create(CreateAdminInput input);

  void enable(Long id);

  void disable(Long id);

}
