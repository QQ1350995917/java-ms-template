package pwd.initializr.organization.business.admin;

import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.organization.business.user.bo.Organization;

/**
 * pwd.initializr.account.business.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-10-12 18:35
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface OrganizationService {

  /**
   * <h2>创建根节点组织</h2>
   * date 2019-11-03 21:36
   *
   * @param organization 组织信息
   * @return pwd.initializr.organization.business.user.bo.Organization
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Organization createRoot(Organization organization);


  /**
   * <h2>根节点组织查询</h2>
   * date 2019-11-03 21:36
   *
   * @return pwd.initializr.organization.business.user.bo.Organization
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Organization getRoot();


  /**
   * <h2>子级组织查询</h2>
   * date 2019-11-03 21:36
   *
   * @param pid 上级组织
   * @param status 组织状态
   * @return pwd.initializr.common.web.business.bo.PageableQueryResult<pwd.initializr.organization.business.user.bo.Organization>
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  PageableQueryResult<Organization> listByPidAndStatus(Long pid, Integer status);


  /**
   * <h2>根据状态查询组织</h2>
   * date 2019-11-03 21:36
   *
   * @param status 组织状态
   * @return pwd.initializr.common.web.business.bo.PageableQueryResult<pwd.initializr.organization.business.user.bo.Organization>
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  PageableQueryResult<Organization> listByStatus(Integer status);

  /**
   * <h2>更新根节点组织的信息</h2>
   * date 2019-11-03 21:36
   *
   * @param organization 组织信息
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void updateRoot(Organization organization);

  /*
   *
   * <h2>更新审核状态</h2>
   * date 2019-11-04
   *
   * @param id 组织ID
   * @param progress 审核状态
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void updateProgress(Long id, Integer progress);

}
