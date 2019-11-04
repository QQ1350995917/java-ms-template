package pwd.initializr.organization.business.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwd.initializr.organization.business.user.bo.Organization;
import pwd.initializr.organization.business.user.bo.OrganizationProgress;
import pwd.initializr.organization.persistence.dao.OrganizationEntity.Progress;
import pwd.initializr.organization.persistence.mapper.OrganizationProgressMapper;

/**
 * pwd.initializr.organization.business.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-03 21:52
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class OrganizationProgressServiceImpl implements OrganizationProgressService {

  @Autowired
  private OrganizationProgressMapper organizationProgressMapper;

  @Override
  public void reviewApprove(Long id) {

  }

  @Override
  public void reviewExecution(Long id) {

  }

  @Override
  public void reviewRecheck(Long id) {

  }

  @Override
  public void reviewRefuse(Long id) {

  }
}
