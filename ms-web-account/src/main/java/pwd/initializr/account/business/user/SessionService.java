package pwd.initializr.account.business.user;

import org.springframework.stereotype.Service;
import pwd.initializr.account.business.user.bo.Account;
import pwd.initializr.account.business.user.bo.UserAccount;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-04 15:36
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public interface SessionService {

  String genToken(UserAccount userAccount);

  UserAccount login(Account account);

  Account info(String userId);

  void logout(String userId);

  void saveSession(UserAccount userAccount);

  String getSession(String userId);

  void delSession(String userId);
}
