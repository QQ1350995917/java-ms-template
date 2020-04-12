package pwd.initializr.account.api.user;

import pwd.initializr.account.api.user.vo.LoginInput;

/**
 * pwd.initializr.account.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-02 21:59
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface SessionApi {

  void info(Long id);

  void login(LoginInput input);

  void logout(Long id);

}
