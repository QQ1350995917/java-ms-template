package pwd.initializr.organization.business.admin;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.organization.business.admin.bo.OrganizationProgress;
import pwd.initializr.organization.persistence.dao.OrganizationEntity;
import pwd.initializr.organization.persistence.dao.OrganizationEntity.Progress;
import pwd.initializr.organization.persistence.dao.OrganizationProgressEntity;
import pwd.initializr.organization.persistence.dao.OrganizationProgressEntity.Type;
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
  @Autowired
  private OrganizationService organizationService;

  @Override
  public ObjectList<OrganizationProgress> listReviewByOrgId(Long orgId, Integer status) {
    List<OrganizationProgressEntity> organizationProgressEntities = organizationProgressMapper
        .listByOrgId(orgId, status);
    ObjectList<OrganizationProgress> result = new ObjectList<>();
    for (OrganizationProgressEntity organizationProgressEntity : organizationProgressEntities) {
      OrganizationProgress organizationProgress = new OrganizationProgress();
      BeanUtils.copyProperties(organizationProgressEntity, organizationProgress);
      result.getElements().add(organizationProgress);
    }
    return result;
  }

  @Override
  public Progress[] listReviewOption() {
    return OrganizationEntity.Progress.values();
  }

  @Override
  public void createReview(OrganizationProgress organizationProgress) {
    OrganizationProgressEntity organizationProgressEntity = new OrganizationProgressEntity();
    BeanUtils.copyProperties(organizationProgress, organizationProgressEntity);
    organizationProgressEntity.setAuditorTime(System.currentTimeMillis());
    organizationProgressEntity.setType(Type.ADMIN.value());
    organizationProgressEntity.setCreateTime(System.currentTimeMillis());
    organizationProgressEntity.setUpdateTime(System.currentTimeMillis());
    organizationProgress.setAuditorTime(organizationProgressEntity.getAuditorTime());
    organizationProgress.setType(organizationProgressEntity.getType());
    organizationProgress.setCreateTime(organizationProgressEntity.getCreateTime());
    organizationProgress.setUpdateTime(organizationProgressEntity.getUpdateTime());
    organizationProgressMapper.create(organizationProgressEntity);
    organizationService
        .updateProgress(organizationProgress.getOrgId(), organizationProgress.getProgress());
  }
}
