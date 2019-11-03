package pwd.initializr.organization.api.user;

import pwd.initializr.organization.api.user.vo.CreateOrgInput;

/**
 * pwd.initializr.account.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-10-12 10:38
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface OrgApi {

  void listOrgByMemId();

  void create(CreateOrgInput input);

  void reviewPending();
}
