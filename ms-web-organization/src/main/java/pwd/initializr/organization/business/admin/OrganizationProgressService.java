package pwd.initializr.organization.business.admin;

import org.springframework.stereotype.Service;

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

  /**
   * <h2>组织审核审核结果：通过</h2>
   * date 2019-11-03 21:36
   *
   * @param id 组织ID
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void reviewApprove(Long id);


  /**
   * <h2>组织审核结果行进中</h2>
   * date 2019-11-03 21:36
   *
   * @param id 组织ID
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void reviewExecution(Long id);


  /**
   * <h2>组织审核结果：重新审核</h2>
   * date 2019-11-03 21:36
   *
   * @param id 组织ID
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void reviewRecheck(Long id);


  /**
   * <h2>组织审核结果；拒接</h2>
   * date 2019-11-03 21:36
   *
   * @param id 组织ID
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void reviewRefuse(Long id);
}
