package pwd.initializr.organization.business.user;

import org.springframework.stereotype.Component;
import pwd.initializr.organization.business.user.OrganizationMemberInfoService;

/**
 * pwd.initializr.organization.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-05 16:37
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Component
public class OrganizationMemberInfoServiceImpl implements OrganizationMemberInfoService {

  @Override
  public Object fetchMemberInfo(Long[] userIds) {
    return "er";
  }
}
