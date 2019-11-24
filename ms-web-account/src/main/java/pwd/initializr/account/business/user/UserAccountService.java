package pwd.initializr.account.business.user;

import pwd.initializr.account.business.user.bo.Account;
import pwd.initializr.account.business.user.bo.User;
import pwd.initializr.account.business.user.bo.UserAccount;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-21 15:33
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface UserAccountService {

  UserAccount createUserAccount(User user, Account account);
}
