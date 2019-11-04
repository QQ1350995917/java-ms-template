package pwd.initializr.organization.business.admin;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.organization.business.admin.bo.OrganizationProgress;
import pwd.initializr.organization.persistence.dao.OrganizationEntity.Progress;

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
public interface OrganizationProgressService {


  ObjectList<OrganizationProgress> listReviewByOrgId(Long orgId, Integer status);

  Progress[] listReviewOption();

  @Transactional
  void createReview(OrganizationProgress organizationProgress);



}
