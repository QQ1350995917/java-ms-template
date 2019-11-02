package pwd.initializr.organization.business.admin;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.organization.business.user.bo.Organization;
import pwd.initializr.organization.persistence.dao.OrganizationEntity;
import pwd.initializr.organization.persistence.dao.OrganizationEntity.Progress;
import pwd.initializr.organization.persistence.mapper.OrganizationMapper;

/**
 * pwd.initializr.account.business.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-10-12 18:39
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

  @Autowired
  private OrganizationMapper organizationMapper;

  @Override
  public Organization createRoot(Organization organization) {
    OrganizationEntity organizationEntity = new OrganizationEntity();
    BeanUtils.copyProperties(organization,organizationEntity);
    organizationEntity.setPid(0L);
    organizationEntity.setCreateTime(System.currentTimeMillis());
    organizationEntity.setUpdateTime(System.currentTimeMillis());
    organizationMapper.create(organizationEntity);
    BeanUtils.copyProperties(organizationEntity,organization);
    return organization;
  }

  @Override
  public Organization getRoot() {
    OrganizationEntity rootOrg = organizationMapper.findOneById(1L);
    if (rootOrg == null) {
      return null;
    }
    Organization organization = new Organization();
    BeanUtils.copyProperties(rootOrg,organization);
    return organization;
  }

  @Override
  public void updateRoot(Organization organization) {
    OrganizationEntity organizationEntity = new OrganizationEntity();
    BeanUtils.copyProperties(organization,organizationEntity);
    organizationMapper.update(organizationEntity);
  }

  @Override
  public void reviewExecution(Long id) {
    organizationMapper.updateProgressById(id, Progress.REVIEW_EXECUTION.value());
  }

  @Override
  public void reviewRefuse(Long id) {
    organizationMapper.updateProgressById(id, Progress.REVIEW_REFUSE.value());
  }

  @Override
  public void reviewApprove(Long id) {
    organizationMapper.updateProgressById(id, Progress.REVIEW_APPROVE.value());
  }

  @Override
  public void reviewRecheck(Long id) {
    organizationMapper.updateProgressById(id, Progress.REVIEW_RECHECK.value());
  }

  @Override
  public ObjectList<Organization> listByPidAndStatus(Long pid, Integer status) {
    ObjectList<Organization> result = new ObjectList<>();
    List<OrganizationEntity> organizationEntities = organizationMapper
        .listByPidAndStatus(pid, status);
    for (OrganizationEntity organizationEntity : organizationEntities) {
      Organization organization = new Organization();
      BeanUtils.copyProperties(organizationEntity,organization);
      result.getElements().add(organization);
    }
    return result;
  }

  @Override
  public ObjectList<Organization> listByStatus(Integer status) {
    ObjectList<Organization> result = new ObjectList<>();
    List<OrganizationEntity> organizationEntities = organizationMapper
        .listByStatus(status);
    for (OrganizationEntity organizationEntity : organizationEntities) {
      Organization organization = new Organization();
      BeanUtils.copyProperties(organizationEntity,organization);
      result.getElements().add(organization);
    }
    return result;
  }
}
