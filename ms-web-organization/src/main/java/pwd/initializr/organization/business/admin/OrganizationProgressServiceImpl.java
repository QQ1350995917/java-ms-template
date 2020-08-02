package pwd.initializr.organization.business.admin;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.organization.business.admin.bo.OrganizationProgress;
import pwd.initializr.organization.persistence.dao.ConstantStatus;
import pwd.initializr.organization.persistence.dao.OrganizationEntity;
import pwd.initializr.organization.persistence.dao.OrganizationEntity.Progress;
import pwd.initializr.organization.persistence.dao.OrganizationReviewEntity;
import pwd.initializr.organization.persistence.dao.OrganizationReviewEntity.Type;
import pwd.initializr.organization.persistence.mapper.OrganizationReviewMapper;

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
  private OrganizationReviewMapper organizationProgressMapper;
  @Autowired
  private OrganizationService organizationService;

  @Override
  public PageableQueryResult<OrganizationProgress> listReviewByOrgId(Long orgId, Integer status) {
    List<OrganizationReviewEntity> organizationProgressEntities = organizationProgressMapper
        .listByOrgId(orgId, status);
    PageableQueryResult<OrganizationProgress> result = new PageableQueryResult<>();
    for (OrganizationReviewEntity organizationProgressEntity : organizationProgressEntities) {
      OrganizationProgress organizationProgress = new OrganizationProgress();
      BeanUtils.copyProperties(organizationProgressEntity, organizationProgress);
      result.getElements().add(organizationProgress);
    }
    return result;
  }

  @Override
  public Progress[] listReviewOption() {
    // TODO 返回结果形式[
    //    "NEW",
    //    "REVIEW_PENDING",
    //    "REVIEW_EXECUTION",
    //    "REVIEW_REFUSE",
    //    "REVIEW_APPROVE",
    //    "REVIEW_RECHECK"
    //  ]
    return OrganizationEntity.Progress.values();
  }

  @Override
  public void createReview(OrganizationProgress organizationProgress) {
    OrganizationReviewEntity organizationProgressEntity = new OrganizationReviewEntity();
    BeanUtils.copyProperties(organizationProgress, organizationProgressEntity);
    organizationProgressEntity.setStatus(ConstantStatus.ENABLE.value());
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
