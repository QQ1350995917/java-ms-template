package pwd.initializr.account.business.user;

import com.alibaba.fastjson.JSON;
import java.util.UUID;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pwd.initializr.account.business.common.bo.SessionBO;
import pwd.initializr.account.business.common.bo.SessionCaptchaBO;
import pwd.initializr.account.business.common.bo.SessionTokenBO;
import pwd.initializr.common.mw.redis.RedisClient;
import pwd.initializr.common.utils.Cryptographer;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>服务层逻辑：用户登录系统</h1>
 *
 * date 2019-11-04 15:43
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service("sessionService")
public class SessionServiceImpl implements SessionService {


  @Value("${account.user.cookie.initializr.salt}")
  private String cookieSalt;
  @Value("${account.user.cookie.redis.key.prefix}")
  private String cookieInRedisPrefix;
  @Value("${account.user.cookie.expires.seconds}")
  private Integer cookieInRedisExpiresSeconds;
  @Value("${account.user.session.redis.key.prefix}")
  private String sessionInRedisPrefix;
  @Value("${account.user.session.expires.seconds}")
  private Integer sessionInRedisExpiresSeconds;


  @Autowired
  private RedisClient redisClient;


  @Override
  public SessionCaptchaBO createCaptcha(String cookie) {
    return null;
  }

  @Override
  public String createCookie() {
    long currentTimeMillis = System.currentTimeMillis();
    String uuid = UUID.randomUUID().toString();
    String clearTextCookie = currentTimeMillis + ":" + uuid;
    String encryptTextCookie = Cryptographer.encrypt(clearTextCookie, cookieSalt);
    SessionTokenBO sessionTokenBO = new SessionTokenBO(0, null);
    redisClient.setex(getCookieKeyInRedis(clearTextCookie), JSON.toJSONString(sessionTokenBO),
        cookieInRedisExpiresSeconds);
    return encryptTextCookie;
  }

  @Override
  public void createSession(String token, SessionBO sessionBO) {
    String sessionKeyInRedis = getSessionKeyInRedis(sessionBO.getUid());
    redisClient
        .setex(sessionKeyInRedis, JSON.toJSONString(sessionBO), sessionInRedisExpiresSeconds);
  }

  @Override
  public Integer deleteCookie(String cookie) {
    Assert.notNull(cookie, "sessionCookieBO.cookie should not be empty");
    String clearTextCookie = Cryptographer.decrypt(cookie, cookieSalt);
    return redisClient.del(getCookieKeyInRedis(clearTextCookie)).intValue();
  }

  @Override
  public Integer deleteSession(Long userId) {
    String key = getSessionKeyInRedis(userId);
    return redisClient.del(key).intValue();
  }

  @Override
  public SessionTokenBO queryCookie(String cookie) {
    Assert.notNull(cookie, "cookie should not be empty");
    String clearTextCookie = Cryptographer.decrypt(cookie, cookieSalt);
    String sessionCookieJson = redisClient.get(getCookieKeyInRedis(clearTextCookie));
    if (StringUtils.isBlank(sessionCookieJson)) {
      return null;
    }
    return JSON.parseObject(sessionCookieJson, SessionTokenBO.class);
  }

  @Override
  public SessionBO querySession(Long uid) {
    String key = getSessionKeyInRedis(uid);
    String session = redisClient.get(key);
    return JSON.parseObject(session, SessionBO.class);
  }

  @Override
  public void updateCookie(String cookie, SessionTokenBO sessionTokenBO) {
    String clearTextCookie = Cryptographer.decrypt(cookie, cookieSalt);
    redisClient.set(getCookieKeyInRedis(clearTextCookie), JSON.toJSONString(sessionTokenBO),
        cookieInRedisExpiresSeconds);
  }

  @Override
  public Integer updateSession(SessionBO sessionBO) {
    return null;
  }

  private String getSessionKeyInRedis(Long uid) {
    return StringUtils.join(new String[]{sessionInRedisPrefix, uid.toString()}, ":");
  }

  private String getCookieKeyInRedis(String cookie) {
    return StringUtils.join(new String[]{cookieInRedisPrefix, cookie}, ":");
  }
}
