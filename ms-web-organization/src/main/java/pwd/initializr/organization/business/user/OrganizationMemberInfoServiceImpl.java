package pwd.initializr.organization.business.user;

import java.util.List;
import org.springframework.stereotype.Component;
import pwd.initializr.common.web.api.vo.Meta;
import pwd.initializr.common.web.api.vo.Output;
import pwd.initializr.organization.business.user.OrganizationMemberInfoService;
import pwd.initializr.organization.business.user.bo.OrganizationMemberInfo;

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
  public Output<List<OrganizationMemberInfo>> fetchMemberInfo(Long[] userIds) {
    return new Output<>(new Meta(400));
  }
}
