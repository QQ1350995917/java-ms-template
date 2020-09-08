package pwd.initializr.account.business.session;

import com.alibaba.fastjson.JSON;
import java.util.UUID;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import pwd.initializr.account.business.session.bo.SessionBO;
import pwd.initializr.account.business.session.bo.SessionBOAnonymous;
import pwd.initializr.account.business.session.bo.CaptchaBO;
import pwd.initializr.account.business.session.bo.SessionBONamed;
import pwd.initializr.common.captcha.CaptchaArithmetic;
import pwd.initializr.common.mw.redis.RedisClient;
import pwd.initializr.common.utils.Cryptographer;
import pwd.initializr.common.captcha.CaptchaHelper;
import pwd.initializr.common.captcha.CaptchaMessage;

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
public abstract class SessionServiceAbs implements SessionService {

  public abstract String getSessionPrefix();
  public abstract String getAnonymousSessionSalt();
  public abstract String getAnonymousSessionInRedisPrefix();
  public abstract Integer getAnonymousSessionInRedisExpiresSeconds();
  public abstract String getNamedSessionInRedisPrefix();
  public abstract Integer getNamedSessionInRedisExpiresSeconds();


  @Autowired
  private RedisClient redisClient;

  @Override
  public CaptchaBO createCaptcha(String token) {
    CaptchaMessage captchaMessage = new CaptchaArithmetic().createCaptcha();
    // 更新redis中的sessionCookie对象
    SessionBOAnonymous sessionBOAnonymous = querySessionAnonymous(token);
    sessionBOAnonymous.setCaptcha(captchaMessage.getExpected());
    updateAnonymousSession(token, sessionBOAnonymous);
    // 输出sessionCookie对象
    String presented = captchaMessage.getPresented();
    CaptchaBO captchaBO = new CaptchaBO();
    captchaBO.setBase64(presented);
    return captchaBO;
  }

  @Override
  public SessionBO createSession(String token, Long uid) {
    if (StringUtils.isNotBlank(token)) {
      // 该请求携带token，初步认为登陆后调用，检验token是否为具名token
      SessionBONamed sessionBONamed = this.querySessionNamed(uid);
      if (sessionBONamed != null) {
        return sessionBONamed;
      }
      // 该请求携带token，排除为具名token，检验token是否为匿名token
      SessionBOAnonymous sessionBOAnonymous = this.querySessionAnonymous(token);
      if (sessionBOAnonymous != null) {
        // 识别为有效的匿名token，延长其在redis的有效期，然后返回
        this.updateAnonymousSession(token, sessionBOAnonymous);
        return sessionBOAnonymous;
      }
    }
    // 当前提交的token非有效的匿名token也非有效的具名token,当做提交的token为空处理
    SessionBOAnonymous anonymousSession = this.createAnonymousSession();
    return anonymousSession;
  }

  @Override
  public SessionBOAnonymous createAnonymousSession() {
    long currentTimeMillis = System.currentTimeMillis();
    String uuid = UUID.randomUUID().toString();
    String clearTextToken = currentTimeMillis + ":" + uuid;
    String encryptTextCookie = Cryptographer.encrypt(clearTextToken, getAnonymousSessionSalt());
    SessionBOAnonymous sessionBOAnonymous = new SessionBOAnonymous(encryptTextCookie,0, null);
    redisClient.setex(getCookieKeyInRedis(clearTextToken), JSON.toJSONString(sessionBOAnonymous),
        getAnonymousSessionInRedisExpiresSeconds());
    return sessionBOAnonymous;
  }

  @Override
  public void createNamedSession(String token, SessionBONamed sessionBONamed) {
    String sessionKeyInRedis = getSessionKeyInRedis(sessionBONamed.getUid());
    redisClient
        .setex(sessionKeyInRedis, JSON.toJSONString(sessionBONamed),
            getNamedSessionInRedisExpiresSeconds());
  }

  @Override
  public Boolean deleteAnonymousToken(String token) {
    Assert.notNull(token, "sessionCookieBO.token should not be empty");
    String clearTextCookie = Cryptographer.decrypt(token, getAnonymousSessionSalt());
    redisClient.del(getCookieKeyInRedis(clearTextCookie));
    return true;
  }

  @Override
  public Boolean deleteNamedSession(Long uid) {
    String key = getSessionKeyInRedis(uid);
    return redisClient.del(key) == 1;
  }

  @Override
  public SessionBOAnonymous querySessionAnonymous(String token) {
    Assert.notNull(token, "token should not be empty");
    String clearTextCookie = Cryptographer.decrypt(token, getAnonymousSessionSalt());
    String sessionCookieJson = redisClient.get(getCookieKeyInRedis(clearTextCookie));
    if (StringUtils.isBlank(sessionCookieJson)) {
      return null;
    }
    return JSON.parseObject(sessionCookieJson, SessionBOAnonymous.class);
  }

  @Override
  public SessionBONamed querySessionNamed(Long uid) {
    if (uid == null || uid < 1) {
      return null;
    }
    String key = getSessionKeyInRedis(uid);
    String session = redisClient.get(key);
    return JSON.parseObject(session, SessionBONamed.class);
  }

  @Override
  public void updateAnonymousSession(String token, SessionBOAnonymous sessionBOAnonymous) {
    String clearTextCookie = Cryptographer.decrypt(token, getAnonymousSessionSalt());
    redisClient.del(getCookieKeyInRedis(clearTextCookie));
    redisClient.setex(getCookieKeyInRedis(clearTextCookie), JSON.toJSONString(sessionBOAnonymous),
        getAnonymousSessionInRedisExpiresSeconds());
  }

  @Override
  public Boolean updateNamedSession(SessionBONamed sessionBONamed) {
    String key = StringUtils.join(new String[]{getSessionPrefix(), sessionBONamed.getUid().toString()});
    if (!"0".equals(redisClient.set(key, JSON.toJSONString(sessionBONamed)))) {
      return true;
    } else {
      return false;
    }
  }

  private String getSessionKeyInRedis(Long uid) {
    return StringUtils.join(new String[]{getNamedSessionInRedisPrefix(), uid.toString()}, ":");
  }

  private String getCookieKeyInRedis(String cookie) {
    return StringUtils.join(new String[]{getAnonymousSessionInRedisPrefix(), cookie}, ":");
  }
}
