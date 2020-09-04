package pwd.initializr.account.business.user;

import java.util.List;
import pwd.initializr.account.business.user.bo.UserAccountBO;
import pwd.initializr.account.business.user.bo.UserUserBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-07-28 15:49
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface UserUserServiceWrap {

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
  Integer ableByUserId(Long userId, EntityAble entityAble);

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
   * @param userUserBO 用户对象
   * @param userAccountBO 账户对象
   * @return pwd.initializr.account.business.user.bo.UserUserBO
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  UserAccountBO insert(UserUserBO userUserBO, UserAccountBO userAccountBO);
}
