package pwd.initializr.organization.business.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.organization.business.user.bo.OrganizationProgress;

/**
 * pwd.initializr.organization.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-03 20:19
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public interface OrganizationReviewService {

  /**
   * <h2>TODO what you want to do</h2>
   * date 2019-11-03 20:40
   *
   * @param orgId 组织ID
   * @param status 组织状态
   * @return pwd.initializr.common.web.business.bo.PageableQueryResult<pwd.initializr.organization.business.user.bo.OrganizationProgress>
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  PageableQueryResult<OrganizationProgress> listReviewPending(Long orgId,Integer status);

  /**
   * <h2>提交审核组织</h2>
   * date 2019-11-03 20:08
   *
   * @param organizationProgress 审核内容
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  @Transactional
  void reviewPending(OrganizationProgress organizationProgress);


}
