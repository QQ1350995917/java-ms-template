package pwd.initializr.organization.business.user;

import java.util.List;
import pwd.initializr.organization.business.user.bo.OrganizationMemberInfo;

/**
 * pwd.initializr.organization.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-05 16:29
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */

public interface OrganizationMemberInfoService {

  List<OrganizationMemberInfo> fetchMemberInfo(Long[] memberIds);
}
