package pwd.initializr.account.business.user;

import org.springframework.stereotype.Service;
import pwd.initializr.account.business.user.bo.UserBO;
import pwd.initializr.account.rpc.RPCUserSession;

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

  Long delSession(Long userId);

  UserBO getSession(Long userId);

  Long updateSession(RPCUserSession user);
}
