package pwd.initializr.account.business.user;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pwd.initializr.account.business.user.bo.User;
import pwd.initializr.account.rpc.UserSession;
import pwd.initializr.common.mw.redis.RedisClient;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-04 15:43
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class SessionServiceImpl implements SessionService {

  @Value("${account_login_prefix:sso_identify_}")
  private String SESSION_PREFIX;


  @Autowired
  private RedisClient redisClient;

  @Override
  public Long delSession(Long userId) {
    String key = StringUtils.join(new String[]{SESSION_PREFIX, userId.toString()});
    return redisClient.del(key);
  }

  @Override
  public User getSession(Long userId) {
    String key = StringUtils.join(new String[]{SESSION_PREFIX, userId.toString()});
    String session = redisClient.get(key);
    return JSON.parseObject(session, User.class);
  }

  @Override
  public Long updateSession(UserSession user) {
    String key = StringUtils.join(new String[]{SESSION_PREFIX, user.getId().toString()});
    if (!"0".equals(redisClient.set(key, JSON.toJSONString(user)))) {
      return 1L;
    } else {
      return 0L;
    }
  }
}
