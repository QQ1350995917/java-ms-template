package pwd.initializr.organization.business.user;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.organization.business.user.bo.OrganizationProgress;
import pwd.initializr.organization.persistence.dao.ConstantStatus;
import pwd.initializr.organization.persistence.dao.OrganizationEntity.Progress;
import pwd.initializr.organization.persistence.dao.OrganizationReviewEntity;
import pwd.initializr.organization.persistence.dao.OrganizationReviewEntity.Type;
import pwd.initializr.organization.persistence.mapper.OrganizationReviewMapper;

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
public class OrganizationReviewServiceImpl implements OrganizationReviewService {

  @Autowired
  private OrganizationReviewMapper organizationProgressMapper;
  @Autowired
  private OrganizationService organizationService;

  @Override
  public ObjectList<OrganizationProgress> listReviewPending(Long orgId, Integer status) {
    List<OrganizationReviewEntity> organizationProgressEntities = organizationProgressMapper
        .listByOrgId(orgId, status);
    ObjectList<OrganizationProgress> result = new ObjectList<>();
    for (OrganizationReviewEntity organizationProgressEntity : organizationProgressEntities) {
      OrganizationProgress organizationProgress = new OrganizationProgress();
      BeanUtils.copyProperties(organizationProgressEntity,organizationProgress);
      result.getElements().add(organizationProgress);
    }
    return result;
  }


  @Override
  public void reviewPending(OrganizationProgress organizationProgress) {
    OrganizationReviewEntity organizationProgressEntity = new OrganizationReviewEntity();
    BeanUtils.copyProperties(organizationProgress,organizationProgressEntity);
    organizationProgressEntity.setStatus(ConstantStatus.ENABLE.value());
    organizationProgressEntity.setType(Type.USER.value());
    organizationProgressEntity.setProgress(Progress.REVIEW_PENDING.value());
    organizationProgressEntity.setCreateTime(System.currentTimeMillis());
    organizationProgressEntity.setUpdateTime(System.currentTimeMillis());
    organizationProgressMapper.create(organizationProgressEntity);
    organizationService.reviewPending(organizationProgress.getOrgId());
  }


}
