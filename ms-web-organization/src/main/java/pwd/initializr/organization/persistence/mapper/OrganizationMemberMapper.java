package pwd.initializr.organization.persistence.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pwd.initializr.organization.persistence.dao.OrganizationMemberEntity;

/**
 * pwd.initializr.account.persistence.entity@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-27 17:55
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Component
@Mapper
public interface OrganizationMemberMapper {

  /*
   *
   * <h2>向组织中添加一个成员</h2>
   * date 2019-09-27
   *
   * @param orgMemEntity 组织用户属性
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void addMemToOrg(OrganizationMemberEntity orgMemEntity);


  /**
   * <h2>找到我创建的组织</h2>
   * date 2019-11-03 21:31
   *
   * @param memId 成员ID
   * @param status 成员状态
   * @return java.util.List<pwd.initializr.organization.persistence.entity.OrganizationMemberEntity>
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  List<OrganizationMemberEntity> findMyCreation(@Param("memId") Long memId,
      @Param("status") Integer status);


  /*
   *
   * <h2>根据用户ID查询所在的组织</h2>
   * date 2019-09-27
   *
   * @param memId 用户ID
   * @param status 状态
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  List<OrganizationMemberEntity> findMyJoined(@Param("memId") Long memId,
      @Param("status") Integer status);


  /*
   *
   * <h2>找到组织中当前用户的信息</h2>
   * date 2019-09-27
   *
   * @param orgId  组织ID
   * @param memId 用户ID
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  OrganizationMemberEntity findOneMemInOrg(Long orgId, Long memId);


  /*
   *
   * <h2>根据组织ID查询其中的用户</h2>
   * date 2019-09-27
   *
   * @param orgId 组织ID
   * @param status 状态
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  List<OrganizationMemberEntity> listMemInOrgByOrgId(Long orgId, Integer status);


  /*
   *
   * <h2>更新组织中成员的等级</h2>
   * date 2019-09-27
   *
   * @param orgId 组织ID
   * @param memId 用户ID
   * @param level 等级
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void updateMemLevelInOrg(Long orgId, Long memId, Integer level);


  /*
   *
   * <h2>更新组织中的成员状态</h2>
   * date 2019-09-27
   *
   * @param orgId 组织ID
   * @param memId 用户ID
   * @param status 状态
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void updateMemStatusInOrg(Long orgId, Long memId, Integer status);


}
