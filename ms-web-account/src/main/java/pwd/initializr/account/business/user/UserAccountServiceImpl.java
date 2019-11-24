package pwd.initializr.account.business.user;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwd.initializr.account.business.user.bo.Account;
import pwd.initializr.account.business.user.bo.User;
import pwd.initializr.account.business.user.bo.UserAccount;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-21 16:19
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service

public class UserAccountServiceImpl implements UserAccountService {

  @Autowired
  private UserService userService;
  @Autowired
  private AccountService accountService;

  @Override
  @Transactional
  public UserAccount createUserAccount(User user, Account account) {
    User insertUser = userService.insert(user);
    account.setUserId(user.getId());
    Account insertAccount = accountService.insertAccount(account);
    UserAccount userAccount = new UserAccount(insertUser, Arrays.asList(insertAccount));
    return userAccount;
  }

}
