package pwd.initializr.account.business.session;

import com.alibaba.fastjson.JSON;
import java.util.Random;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import pwd.initializr.account.business.session.bo.SessionBOAnonymous;
import pwd.initializr.account.business.session.bo.CaptchaBO;
import pwd.initializr.account.business.session.bo.SessionBONamed;
import pwd.initializr.account.rpc.RPCToken;
import pwd.initializr.common.captcha.CaptchaArithmetic;
import pwd.initializr.common.mw.redis.RedisClient;
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
    SessionBOAnonymous sessionBOAnonymous = querySessionAnonymous();
    sessionBOAnonymous.setCaptcha(captchaMessage.getExpected());
    updateAnonymousSession(sessionBOAnonymous);
    // 输出sessionCookie对象
    String presented = captchaMessage.getPresented();
    CaptchaBO captchaBO = new CaptchaBO();
    captchaBO.setBase64(presented);
    return captchaBO;
  }

  @Override
  public SessionBONamed createSession(String token, Long uid) {
    if (StringUtils.isNotBlank(token)) {
      // 该请求携带token，初步认为登陆后调用，检验token是否为具名token
      SessionBONamed sessionBONamed = this.querySessionNamed(uid);
      if (sessionBONamed != null) {
        return sessionBONamed;
      }
      // 该请求携带token，排除为具名token，检验token是否为匿名token
      SessionBOAnonymous sessionBOAnonymous = this.querySessionAnonymous(uid);
      if (sessionBOAnonymous != null) {
        // 识别为有效的匿名token，延长其在redis的有效期，然后返回
        this.updateAnonymousSession(sessionBOAnonymous);
        return sessionBOAnonymous;
      }
    }
    // 当前提交的token非有效的匿名token也非有效的具名token,当做提交的token为空处理
    SessionBOAnonymous anonymousSession = this.createAnonymousSession();
    return anonymousSession;
  }
  private static Random anonymousSessionIdRandom = new Random();
  private static final int anonymousSessionIdRandomBound = 1000;

  @Override
  public SessionBOAnonymous createAnonymousSession() {
    SessionBOAnonymous sessionBOAnonymous = new SessionBOAnonymous(0, null);
    // fixme: 高并发下的相同的匿名ID，若出现相同情况，表现则是首次登录需要验证码
    long id0 = System.currentTimeMillis();
    long id1 = anonymousSessionIdRandom.nextInt(anonymousSessionIdRandomBound);
    String uidString = StringUtils.join(new String[]{String.valueOf(id0), String.valueOf(id1)}, "");
    long uid = Long.parseLong(uidString);
    sessionBOAnonymous.setUid(uid);
    sessionBOAnonymous.setAid(-1L);
    sessionBOAnonymous.setTimestamp(id0);
    sessionBOAnonymous.setToken(RPCToken.generateToken(sessionBOAnonymous, getAnonymousSessionSalt()));
    redisClient.setex(getCookieKeyInRedis(uid), JSON.toJSONString(sessionBOAnonymous),
        getAnonymousSessionInRedisExpiresSeconds());
    return sessionBOAnonymous;
  }

  @Override
  public void createNamedSession(SessionBONamed sessionBONamed) {
    String sessionKeyInRedis = getSessionKeyInRedis(sessionBONamed.getUid());
    redisClient
        .setex(sessionKeyInRedis, JSON.toJSONString(sessionBONamed),
            getNamedSessionInRedisExpiresSeconds());
  }

  @Override
  public Boolean deleteAnonymousToken(Long uid) {
    redisClient.del(getCookieKeyInRedis(uid));
    return true;
  }

  @Override
  public Boolean deleteNamedSession(Long uid) {
    String key = getSessionKeyInRedis(uid);
    return redisClient.del(key) == 1;
  }

  @Override
  public SessionBOAnonymous querySessionAnonymous(Long uid) {
    String sessionCookieJson = redisClient.get(getCookieKeyInRedis(uid));
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
  public void updateAnonymousSession(SessionBOAnonymous sessionBOAnonymous) {
    redisClient.del(getCookieKeyInRedis(sessionBOAnonymous.getUid()));
    redisClient.setex(getCookieKeyInRedis(sessionBOAnonymous.getUid()),
        JSON.toJSONString(sessionBOAnonymous), getAnonymousSessionInRedisExpiresSeconds());
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

  private String getCookieKeyInRedis(Long uid) {
    return getSessionKeyInRedis(uid);
  }
}
