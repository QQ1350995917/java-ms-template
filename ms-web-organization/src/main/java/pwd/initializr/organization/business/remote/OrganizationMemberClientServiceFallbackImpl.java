package pwd.initializr.organization.business.remote;

import java.util.List;
import org.springframework.stereotype.Component;
import pwd.initializr.account.rpc.RPCUser;
import pwd.initializr.common.web.api.vo.Output;

/**
 * pwd.initializr.organization.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-06-14 13:55
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Component
public class OrganizationMemberClientServiceFallbackImpl implements OrganizationMemberClientService {

  @Override
  public Output<List<RPCUser>> findByIds(Long[] ids) {
    return new Output<>();
  }
}
