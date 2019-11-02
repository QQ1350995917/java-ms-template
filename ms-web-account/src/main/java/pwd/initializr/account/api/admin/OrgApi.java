package pwd.initializr.account.api.admin;

import pwd.initializr.account.api.admin.vo.OrgCreateInput;
import pwd.initializr.account.api.admin.vo.OrgListInput;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-10-12 17:59
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface OrgApi {

  void listOrg(OrgListInput input);

  void queryRootOrg();

  void createRootOrg(OrgCreateInput input);

  void reviewExecution();

  void reviewRefuse();

  void reviewApprove();

  void reviewRecheck();

}
