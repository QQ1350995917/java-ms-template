package pwd.initializr.account.business.admin;

import pwd.initializr.account.business.admin.bo.SessionBO;

/**
 * pwd.initializr.account.business.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-29 22:17
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface SessionService {

  Long delSession(Long adminId);

  SessionBO getSession(Long adminId);

  Long replaceSession(SessionBO sessionBO);
}
