package pwd.initializr.organization.business.user;

import org.springframework.transaction.annotation.Transactional;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.organization.business.user.bo.Organization;
import pwd.initializr.organization.business.user.bo.OrganizationMember;
import pwd.initializr.organization.business.user.bo.OrganizationProgress;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-27 22:37
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface OrganizationService {

  /**
   * <h2>创建一个组织</h2>
   * date 2019-11-03 20:06
   *
   * @param organization 组织
   * @param organizationMember 组织创建者
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  @Transactional
  void create(Organization organization, OrganizationMember organizationMember);


  /**
   * <h2>根据组织ID批量查询组织信息</h2>
   * date 2019-11-03 20:17
   *
   * @param orgIds 组织ID集合
   * @param status 组织状态
   * @return pwd.initializr.common.web.business.bo.ObjectList<pwd.initializr.organization.business.user.bo.Organization>
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  ObjectList<Organization> listById(Long[] orgIds, Integer status);


  /**
   * <h2>查询子组织信息</h2>
   * date 2019-11-03 20:20
   *
   * @param pid 上级组织ID
   * @param status 子组织状态
   * @return pwd.initializr.common.web.business.bo.ObjectList<pwd.initializr.organization.business.user.bo.Organization>
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  ObjectList<Organization> listByPid(Long pid, Integer status);


  /**
   * <h2>便跟组织审核状态为提交审核</h2>
   * date 2019-11-03 20:08
   *
   * @param id 组织ID
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void reviewPending(Long id);


  /**
   * <h2>更新组织信息</h2>
   * date 2019-11-03 20:07
   *
   * @param organization 组织
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void update(Organization organization);


}
