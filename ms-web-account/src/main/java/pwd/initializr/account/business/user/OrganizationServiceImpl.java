package pwd.initializr.account.business.user;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwd.initializr.account.business.user.bo.Organization;
import pwd.initializr.account.persistence.dao.OrganizationEntity;
import pwd.initializr.account.persistence.dao.OrganizationEntity.Progress;
import pwd.initializr.account.persistence.mapper.OrganizationMapper;
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
  @Autowired
  private OrganizationMapper organizationMapper;

  @Override
  public void create(Organization organization) {
    OrganizationEntity organizationEntity = new OrganizationEntity();
    BeanUtils.copyProperties(organization,organizationEntity);
    organizationEntity.setCreateTime(System.currentTimeMillis());
    organizationEntity.setUpdateTime(System.currentTimeMillis());
    organizationMapper.create(organizationEntity);
  }

  @Override
  public void update(Organization organization) {
    OrganizationEntity organizationEntity = new OrganizationEntity();
    BeanUtils.copyProperties(organization,organizationEntity);
    organizationMapper.update(organizationEntity);
  }

  @Override
  public void reviewCommit(Object object) {
    organizationMapper.updateProgressById(null, Progress.REVIEW_PENDING.value());
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
}
