package pwd.initializr.account.api.user;

import pwd.initializr.account.api.user.vo.UserUpdateInput;

/**
 * pwd.initializr.account.api.user@ms-web-initializr
 *
 * <h1>控制层接口：用户信息</h1>
 *
 * date 2020-07-28 23:05
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface UserApi {

  void queryUser();

  void updateUser(UserUpdateInput input);
}
