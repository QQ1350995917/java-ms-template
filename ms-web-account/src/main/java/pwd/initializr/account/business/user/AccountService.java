package pwd.initializr.account.business.user;

import pwd.initializr.account.business.user.bo.Account;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-21 14:06
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface AccountService {

  Account insertAccount(Account account);

  ObjectList<Account> findByUserId(Long userId);

  Account findByAccountId(Long accountId);
}
