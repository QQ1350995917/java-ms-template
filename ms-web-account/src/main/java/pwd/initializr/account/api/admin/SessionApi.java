package pwd.initializr.account.api.admin;

import pwd.initializr.account.api.admin.vo.LoginInput;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-10-25 20:17
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface SessionApi {

  void getInfo();

  void login(LoginInput input);

  void logout();
}
