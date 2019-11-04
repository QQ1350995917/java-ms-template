package pwd.initializr.organization.business.user;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.organization.business.user.bo.OrganizationProgress;
import pwd.initializr.organization.persistence.dao.OrganizationProgressEntity;
import pwd.initializr.organization.persistence.mapper.OrganizationProgressMapper;

/**
 * pwd.initializr.organization.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-03 20:25
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
  public ObjectList<OrganizationProgress> listReviewPending(Long orgId, Integer status) {
    List<OrganizationProgressEntity> organizationProgressEntities = organizationProgressMapper
        .listByOrgId(orgId, status);
    ObjectList<OrganizationProgress> result = new ObjectList<>();
    for (OrganizationProgressEntity organizationProgressEntity : organizationProgressEntities) {
      OrganizationProgress organizationProgress = new OrganizationProgress();
      BeanUtils.copyProperties(organizationProgressEntity,organizationProgress);
      result.getElements().add(organizationProgress);
    }
    return result;
  }


  @Override
  public void reviewPending(OrganizationProgress organizationProgress) {
    OrganizationProgressEntity organizationProgressEntity = new OrganizationProgressEntity();
    BeanUtils.copyProperties(organizationProgress,organizationProgressEntity);
    organizationProgressMapper.create(organizationProgressEntity);
    organizationService.reviewPending(organizationProgress.getOrgId());
  }


}