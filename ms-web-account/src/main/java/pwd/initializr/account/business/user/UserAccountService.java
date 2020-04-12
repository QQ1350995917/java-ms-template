package pwd.initializr.account.business.user;

import pwd.initializr.account.business.user.bo.User;
import pwd.initializr.account.business.user.bo.UserAccount;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-21 15:33
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface UserAccountService {

  User createAccountForUser(User user);

  User createUserAndAccount(User user);

  UserAccount findAccountByLoginNameAndPassword(String loginName, String password);

  User findUserByUserId(Long id);

  ObjectList<User> listAccount(UserAccount userAccount, Integer offset, Integer size);

  ObjectList<User> listAccountByUser(User user, Integer offset, Integer size);

  ObjectList<User> listUser(User user, Integer offset, Integer size);
}
