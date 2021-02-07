package pwd.initializr.account.business.admin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pwd.initializr.account.business.session.SessionServiceAbs;

/**
 * pwd.initializr.account.business.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-09-07 18:20
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service("adminSessionService")
public class SessionServiceImpl extends SessionServiceAbs {
    @Value("${account_login_prefix_admin:sso_identify_admin}")
    private String sessionPrefix;

    @Value("${account.admin.session.anonymous.initializr.salt}")
    private String anonymousSessionSalt;
    @Value("${account.admin.session.anonymous.expires.seconds}")
    private Integer anonymousSessionInRedisExpiresSeconds;
    @Value("${account.session.redis.key.prefix}")
    private String sessionInRedisPrefix;
    @Value("${account.admin.session.named.expires.seconds}")
    private Integer namedSessionInRedisExpiresSeconds;

    @Override
    public String getSessionPrefix() {
        return sessionPrefix;
    }

    @Override
    public String getAnonymousSessionSalt() {
        return anonymousSessionSalt;
    }


    @Override
    public Integer getAnonymousSessionInRedisExpiresSeconds() {
        return anonymousSessionInRedisExpiresSeconds;
    }

    @Override
    public String getSessionInRedisPrefix() {
        return sessionInRedisPrefix;
    }

    @Override
    public Integer getNamedSessionInRedisExpiresSeconds() {
        return namedSessionInRedisExpiresSeconds;
    }
}
