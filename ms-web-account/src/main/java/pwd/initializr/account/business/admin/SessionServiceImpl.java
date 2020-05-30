package pwd.initializr.account.business.admin;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pwd.initializr.account.business.admin.bo.SessionBO;
import pwd.initializr.common.mw.redis.RedisClient;

/**
 * pwd.initializr.account.business.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-29 22:36
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class SessionServiceImpl implements SessionService {

  @Value("${account_login_prefix_admin:sso_identify_admin}")
  private String SESSION_PREFIX;

  @Autowired
  private RedisClient redisClient;

  @Override
  public Long delSession(Long adminId) {
    String key = StringUtils.join(new String[]{SESSION_PREFIX, adminId.toString()});
    return redisClient.del(key);
  }

  @Override
  public SessionBO getSession(Long adminId) {
    String key = StringUtils.join(new String[]{SESSION_PREFIX, adminId.toString()});
    String session = redisClient.get(key);
    return JSON.parseObject(session, SessionBO.class);
  }

  @Override
  public Long replaceSession(SessionBO sessionBO) {
    String key = StringUtils.join(new String[]{SESSION_PREFIX, sessionBO.getId().toString()});
    if (!"0".equals(redisClient.set(key, JSON.toJSONString(sessionBO)))) {
      return 1L;
    } else {
      return 0L;
    }
  }
}
