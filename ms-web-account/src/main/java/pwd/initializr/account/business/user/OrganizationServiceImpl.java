package pwd.initializr.account.business.user;

import org.springframework.stereotype.Service;
import pwd.initializr.account.business.user.bo.Organization;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-27 22:41
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

  @Override
  public void create(Organization organization) {

  }

  @Override
  public void update(Organization organization) {

  }

  @Override
  public ObjectList<Organization> listByPidAndStatus(Integer pid, Integer status) {
    return null;
  }
}
