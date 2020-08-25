package pwd.initializr.account.business.user;

import com.alibaba.fastjson.JSON;
import java.util.UUID;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pwd.initializr.account.business.common.bo.AnonymousSessionBO;
import pwd.initializr.account.business.common.bo.NamedSessionBO;
import pwd.initializr.account.business.common.bo.CaptchaBO;
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


  @Value("${account.user.session.anonymous.initializr.salt}")
  private String anonymousSessionSalt;
  @Value("${account.user.session.anonymous.redis.key.prefix}")
  private String anonymousSessionInRedisPrefix;
  @Value("${account.user.session.anonymous.expires.seconds}")
  private Integer anonymousSessionInRedisExpiresSeconds;
  @Value("${account.user.session.named.redis.key.prefix}")
  private String namedSessionInRedisPrefix;
  @Value("${account.user.session.named.expires.seconds}")
  private Integer namedSessionInRedisExpiresSeconds;


  @Autowired
  private RedisClient redisClient;


  @Override
  public CaptchaBO createCaptcha(String cookie) {
    return null;
  }

  @Override
  public String createCookie() {
    long currentTimeMillis = System.currentTimeMillis();
    String uuid = UUID.randomUUID().toString();
    String clearTextCookie = currentTimeMillis + ":" + uuid;
    String encryptTextCookie = Cryptographer.encrypt(clearTextCookie, anonymousSessionSalt);
    AnonymousSessionBO anonymousSessionBO = new AnonymousSessionBO(0, null);
    redisClient.setex(getCookieKeyInRedis(clearTextCookie), JSON.toJSONString(
        anonymousSessionBO),
        anonymousSessionInRedisExpiresSeconds);
    return encryptTextCookie;
  }

  @Override
  public void createSession(String token, NamedSessionBO namedSessionBO) {
    String sessionKeyInRedis = getSessionKeyInRedis(namedSessionBO.getUid());
    redisClient
        .setex(sessionKeyInRedis, JSON.toJSONString(namedSessionBO), namedSessionInRedisExpiresSeconds);
  }

  @Override
  public Integer deleteCookie(String cookie) {
    Assert.notNull(cookie, "sessionCookieBO.cookie should not be empty");
    String clearTextCookie = Cryptographer.decrypt(cookie, anonymousSessionSalt);
    return redisClient.del(getCookieKeyInRedis(clearTextCookie)).intValue();
  }

  @Override
  public Integer deleteSession(Long userId) {
    String key = getSessionKeyInRedis(userId);
    return redisClient.del(key).intValue();
  }

  @Override
  public AnonymousSessionBO queryCookie(String cookie) {
    Assert.notNull(cookie, "cookie should not be empty");
    String clearTextCookie = Cryptographer.decrypt(cookie, anonymousSessionSalt);
    String sessionCookieJson = redisClient.get(getCookieKeyInRedis(clearTextCookie));
    if (StringUtils.isBlank(sessionCookieJson)) {
      return null;
    }
    return JSON.parseObject(sessionCookieJson, AnonymousSessionBO.class);
  }

  @Override
  public NamedSessionBO querySession(Long uid) {
    String key = getSessionKeyInRedis(uid);
    String session = redisClient.get(key);
    return JSON.parseObject(session, NamedSessionBO.class);
  }

  @Override
  public void updateCookie(String cookie, AnonymousSessionBO anonymousSessionBO) {
    String clearTextCookie = Cryptographer.decrypt(cookie, anonymousSessionSalt);
    redisClient.set(getCookieKeyInRedis(clearTextCookie), JSON.toJSONString(anonymousSessionBO),
        anonymousSessionInRedisExpiresSeconds);
  }

  @Override
  public Integer updateSession(NamedSessionBO namedSessionBO) {
    return null;
  }

  private String getSessionKeyInRedis(Long uid) {
    return StringUtils.join(new String[]{namedSessionInRedisPrefix, uid.toString()}, ":");
  }

  private String getCookieKeyInRedis(String cookie) {
    return StringUtils.join(new String[]{anonymousSessionInRedisPrefix, cookie}, ":");
  }
}
