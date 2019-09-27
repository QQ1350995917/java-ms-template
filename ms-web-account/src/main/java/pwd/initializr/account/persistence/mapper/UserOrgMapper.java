package pwd.initializr.account.persistence.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import pwd.initializr.account.persistence.dao.UserOrgEntity;

/**
 * pwd.initializr.account.persistence.mapper@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-27 17:55
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Mapper
public interface UserOrgMapper {

  /*
   *
   * <h2>向组织中添加一个成员</h2>
   * date 2019-09-27
   *
   * @param userOrgEntity 组织用户属性
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void addUserToOrg(UserOrgEntity userOrgEntity);

  /*
   *
   * <h2>更新组织中成员的等级</h2>
   * date 2019-09-27
   *
   * @param userId 用户ID
   * @param orgId 组织ID
   * @param level 等级
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void updateLevel(Long userId, Long orgId, Integer level);

  /*
   *
   * <h2>更新组织中的成员状态</h2>
   * date 2019-09-27
   *
   * @param userId 用户ID
   * @param orgId 组织ID
   * @param status 状态
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void updateStatus(Long userId, Long orgId, Integer status);

  /*
   *
   * <h2>找到组织中当前用户的信息</h2>
   * date 2019-09-27
   *
   * @param userId 用户ID
   * @param orgId  组织ID
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  UserOrgEntity findOne(Long userId, Long orgId);

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
  List<UserOrgEntity> listByOrgId(Long orgId, Integer status);

  /*
   *
   * <h2>根据用户ID查询所在的组织</h2>
   * date 2019-09-27
   *
   * @param userId 用户ID
   * @param status 状态
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  List<UserOrgEntity> listByUserId(Long userId, Integer status);
}
