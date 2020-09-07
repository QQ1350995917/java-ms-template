package pwd.initializr.account.business.session;

import com.alibaba.fastjson.JSON;
import java.util.UUID;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pwd.initializr.account.api.vo.SessionInitOutput;
import pwd.initializr.account.api.vo.SessionStatus;
import pwd.initializr.account.business.session.bo.SessionBO;
import pwd.initializr.account.business.session.bo.SessionBOAnonymous;
import pwd.initializr.account.business.session.bo.CaptchaBO;
import pwd.initializr.account.business.session.bo.SessionBONamed;
import pwd.initializr.common.mw.redis.RedisClient;
import pwd.initializr.common.utils.Cryptographer;
import pwd.initializr.common.vcode.CaptchaArithmeticCode;
import pwd.initializr.common.vcode.CaptchaHelper;
import pwd.initializr.common.vcode.CodeMessage;

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
@Service("adminSessionService")
public class SessionServiceImpl implements SessionService {

  @Value("${account_login_prefix_admin:sso_identify_admin}")
  private String SESSION_PREFIX;

  @Value("${account.admin.session.anonymous.initializr.salt}")
  private String anonymousSessionSalt;
  @Value("${account.admin.session.anonymous.redis.key.prefix}")
  private String anonymousSessionInRedisPrefix;
  @Value("${account.admin.session.anonymous.expires.seconds}")
  private Integer anonymousSessionInRedisExpiresSeconds;
  @Value("${account.admin.session.named.redis.key.prefix}")
  private String namedSessionInRedisPrefix;
  @Value("${account.admin.session.named.expires.seconds}")
  private Integer namedSessionInRedisExpiresSeconds;

  @Autowired
  private RedisClient redisClient;

  @Override
  public CaptchaBO createCaptcha(String token) {
    // TODO 生成验证码的逻辑用着不舒服
    CaptchaHelper captchaHelper = new CaptchaArithmeticCode();
    CodeMessage codeMessage = captchaHelper.productMessage();
    // 更新redis中的sessionCookie对象
    SessionBOAnonymous sessionBOAnonymous = querySessionAnonymous(token);
    sessionBOAnonymous.setCaptcha(codeMessage.getExpected());
    updateAnonymousSession(token, sessionBOAnonymous);
    // 输出sessionCookie对象
    String presented = codeMessage.getPresented();
    String base64Image = captchaHelper.productBase64Image(presented);
    CaptchaBO captchaBO = new CaptchaBO();
    captchaBO.setBase64(base64Image);
    return captchaBO;
  }

  @Override
  public String createAnonymousSession() {
    long currentTimeMillis = System.currentTimeMillis();
    String uuid = UUID.randomUUID().toString();
    String clearTextToken = currentTimeMillis + ":" + uuid;
    String encryptTextCookie = Cryptographer.encrypt(clearTextToken, anonymousSessionSalt);
    SessionBOAnonymous sessionBOAnonymous = new SessionBOAnonymous(0, null);
    redisClient.setex(getCookieKeyInRedis(clearTextToken), JSON.toJSONString(
        sessionBOAnonymous),
        anonymousSessionInRedisExpiresSeconds);
    return encryptTextCookie;
  }

  @Override
  public void createNamedSession(String token, SessionBONamed sessionBONamed) {
    String sessionKeyInRedis = getSessionKeyInRedis(sessionBONamed.getUid());
    redisClient
        .setex(sessionKeyInRedis, JSON.toJSONString(sessionBONamed),
            namedSessionInRedisExpiresSeconds);
  }

  @Override
  public Boolean deleteAnonymousToken(String token) {
    Assert.notNull(token, "sessionCookieBO.token should not be empty");
    String clearTextCookie = Cryptographer.decrypt(token, anonymousSessionSalt);
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
    String clearTextCookie = Cryptographer.decrypt(token, anonymousSessionSalt);
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
    String clearTextCookie = Cryptographer.decrypt(token, anonymousSessionSalt);
    redisClient.del(getCookieKeyInRedis(clearTextCookie));
    redisClient.setex(getCookieKeyInRedis(clearTextCookie), JSON.toJSONString(sessionBOAnonymous),
        anonymousSessionInRedisExpiresSeconds);
  }

  @Override
  public Boolean updateNamedSession(SessionBONamed sessionBONamed) {
    String key = StringUtils.join(new String[]{SESSION_PREFIX, sessionBONamed.getId().toString()});
    if (!"0".equals(redisClient.set(key, JSON.toJSONString(sessionBONamed)))) {
      return true;
    } else {
      return false;
    }
  }

  private String getSessionKeyInRedis(Long uid) {
    return StringUtils.join(new String[]{namedSessionInRedisPrefix, uid.toString()}, ":");
  }

  private String getCookieKeyInRedis(String cookie) {
    return StringUtils.join(new String[]{anonymousSessionInRedisPrefix, cookie}, ":");
  }
}
