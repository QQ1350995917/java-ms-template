package pwd.initializr.organization.business.user;

import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.organization.business.user.bo.OrganizationMember;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-28 23:11
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface OrganizationMemberService {

  /**
   * <h2>添加成员到组织</h2>
   * date 2019-11-03 21:14
   *
   * @param organizationMember 成员信息
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void addMemToOrg(OrganizationMember organizationMember);


  /**
   * <h2>找到我创建的组织</h2>
   * date 2019-11-03 21:16
   *
   * @param memId 成员ID
   * @param status 成员状态
   * @return pwd.initializr.organization.business.user.bo.OrganizationMember
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  PageableQueryResult<OrganizationMember> findMyCreation(Long memId, Integer status);


  /**
   * <h2>找到我加入的组织</h2>
   * date 2019-11-03 21:29
   *
   * @param memId 成员ID
   * @param status 成员状态
   * @return pwd.initializr.common.web.business.bo.PageableQueryResult<pwd.initializr.organization.business.user.bo.OrganizationMember>
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  PageableQueryResult<OrganizationMember> findMyJoined(Long memId, Integer status);


  /**
   * <h2>在指定的组织中查找成员</h2>
   * date 2019-11-03 21:16
   *
   * @param orgId 组织ID
   * @param memId 成员ID
   * @return pwd.initializr.organization.business.user.bo.OrganizationMember
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  OrganizationMember findOneMemInOrg(Long orgId, Long memId);


  /**
   * <h2>成员列表</h2>
   * date 2019-11-03 21:16
   *
   * @param orgId 组织ID
   * @param status 成员状态
   * @return pwd.initializr.common.web.business.bo.PageableQueryResult<pwd.initializr.organization.business.user.bo.OrganizationMember>
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  PageableQueryResult<OrganizationMember> listByOrgId(Long orgId, Integer status);


  /**
   * <h2>更新小组中成员级别</h2>
   * date 2019-11-03 21:14
   *
   * @param orgId 组织ID
   * @param memId 成员ID
   * @param level 成员等级
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void updateMemLevel(Long orgId, Long memId, Integer level);


  /**
   * <h2>更新成员状态</h2>
   * date 2019-11-03 21:16
   *
   * @param memId 成员ID
   * @param orgId 组织ID
   * @param status 成员状态
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void updateMemStatus(Long orgId, Long memId, Integer status);


}
