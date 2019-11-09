package pwd.initializr.account.business.user;

import org.springframework.stereotype.Service;
import pwd.initializr.account.business.user.bo.Account;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-04 15:36
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public interface SessionService {

  String genToken(Account account);

  String login(Account account);

  Account info(String sessionId);

  void logout(String sessionId);

  void createSession(Account account);

  String getSession(String sessionId);

  void delSession(String sessionId);
}
