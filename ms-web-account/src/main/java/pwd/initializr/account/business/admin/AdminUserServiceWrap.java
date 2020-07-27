package pwd.initializr.account.business.admin;

import java.util.List;
import pwd.initializr.account.business.admin.bo.AdminAccountBO;
import pwd.initializr.account.business.admin.bo.AdminUserBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;

/**
 * pwd.initializr.account.business.admin@ms-web-initializr
 *
 * <h1>用户账号服务包装类</h1>
 *
 * date 2020-07-26 23:19
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface AdminUserServiceWrap {

  /**
   * <h2>禁用/启用用户，同时禁用/启用其下所有账户</h2>
   * date 2020-07-26 23:24
   *
   * @param userId 用户ID
   * @param entityAble 禁用/启用
   * @return Boolean
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Boolean ableByUserId(Long userId, EntityAble entityAble);

  /**
   * <h2>禁用/启用用户，同时禁用/启用其下所有账户</h2>
   * date 2020-07-26 23:56
   *
   * @param userIds 用户ID
   * @param entityAble 禁用/启用
   * @return Boolean
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Boolean ableByUserId(List<Long> userIds, EntityAble entityAble);

  /**
   * <h2>删除用户，同时删除其下所有账户</h2>
   * date 2020-07-26 23:24
   *
   * @return Boolean
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Boolean deleteByUserId(Long userId);

  /**
   * <h2>删除用户，同时删除其下所有账户</h2>
   * date 2020-07-26 23:49
   *
   * @return Boolean
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Boolean deleteByUserId(List<Long> userIds);

  /**
   * <h2>创建用户，同时创建账户</h2>
   * date 2020-07-26 23:23
   *
   * @param adminUserBO 用户对象
   * @param adminAccountBO 账户对象
   * @return pwd.initializr.account.business.admin.bo.AdminUserBO
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  AdminUserBO insert(AdminUserBO adminUserBO, AdminAccountBO adminAccountBO);
}
