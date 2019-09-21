package pwd.initializr.account.business.user;

import pwd.initializr.account.business.user.bo.User;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-21 14:05
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface UserService {

  User insert(User user);

  ObjectList<User> listUser();

  User findByUserId(Long userId);

  Long countUser();
}
