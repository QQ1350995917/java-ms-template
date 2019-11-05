package pwd.initializr.account.api.robot;

import pwd.initializr.account.api.robot.vo.ListUserInput;

/**
 * pwd.initializr.account.api.robot@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-05 15:32
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface UserApi {

  void listById(ListUserInput input);
}
