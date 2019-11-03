package pwd.initializr.organization.business.user;


import org.springframework.transaction.annotation.Transactional;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.organization.business.user.bo.Organization;
import pwd.initializr.organization.business.user.bo.OrganizationMember;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-27 22:37
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface OrganizationService {

  @Transactional
  void create(Organization organization,OrganizationMember organizationMember);

  void update(Organization organization);

  void reviewPending(Long id);

  ObjectList<Organization> listById(Long[] ids, Integer status);

  ObjectList<Organization> listByPid(Long pid, Integer status);

}
