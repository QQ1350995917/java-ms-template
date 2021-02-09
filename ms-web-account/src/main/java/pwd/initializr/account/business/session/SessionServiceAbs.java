package pwd.initializr.account.business.session;

import com.alibaba.fastjson.JSON;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import pwd.initializr.account.business.session.bo.SessionBO;
import pwd.initializr.account.business.session.bo.CaptchaBO;
import pwd.initializr.account.business.session.bo.SessionType;
import pwd.initializr.account.rpc.RPCToken;
import pwd.initializr.common.captcha.CaptchaArithmetic;
import pwd.initializr.common.mw.redis.RedisClient;
import pwd.initializr.common.captcha.CaptchaMessage;
import pwd.initializr.common.utils.Snowflake;

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
@Slf4j
public abstract class SessionServiceAbs implements SessionService {

  public abstract String getSessionPrefix();
  public abstract String getAnonymousSessionSalt();
  public abstract String getSessionInRedisPrefix();
  public abstract Integer getAnonymousSessionInRedisExpiresSeconds();
  public abstract Integer getNamedSessionInRedisExpiresSeconds();


  @Autowired
  private RedisClient redisClient;

  @Override
  public CaptchaBO createLoginCaptcha(Long uid) {
    CaptchaMessage captchaMessage = new CaptchaArithmetic().createCaptcha();
    // 更新redis中的sessionCookie对象
    SessionBO sessionBO = querySession(uid);
    sessionBO.setCaptcha(captchaMessage.getExpected());
    updateAnonymousSession(sessionBO);
    // 输出sessionCookie对象
    String presented = captchaMessage.getPresented();
    CaptchaBO captchaBO = new CaptchaBO();
    captchaBO.setBase64(presented);
    return captchaBO;
  }

  @Override
  public SessionBO createAnonymousSession() {
    SessionBO sessionBO = new SessionBO(SessionType.ANONYMOUS.getType(),0,null);
    Long uid = new Snowflake().nextId();
    sessionBO.setUid(uid);
    sessionBO.setAid(-1L);
    sessionBO.setTimestamp(System.currentTimeMillis());
    sessionBO.setToken(RPCToken.generateToken(sessionBO, getAnonymousSessionSalt()));
    redisClient.setex(getSessionKeyInRedis(uid), JSON.toJSONString(sessionBO),
        getAnonymousSessionInRedisExpiresSeconds());
    return sessionBO;
  }

  @Override
  public void createNamedSession(SessionBO sessionBO) {
    String sessionKeyInRedis = getSessionKeyInRedis(sessionBO.getUid());
    redisClient
        .setex(sessionKeyInRedis, JSON.toJSONString(sessionBO),
            getNamedSessionInRedisExpiresSeconds());
  }

  @Override
  public Boolean deleteSession(Long uid) {
    String key = getSessionKeyInRedis(uid);
    return redisClient.del(key) == 1;
  }

  @Override
  public SessionBO querySession(Long uid) {
    if (uid == null || uid < 1) {
      return null;
    }
    String key = getSessionKeyInRedis(uid);
    String session = redisClient.get(key);
    return JSON.parseObject(session, SessionBO.class);
  }

  @Override
  public void updateAnonymousSession(SessionBO sessionBO) {
    String keyInRedis = getSessionKeyInRedis(sessionBO.getUid());
    redisClient.del(keyInRedis);
    // fixme: 修改redis返回值
    redisClient.setex(keyInRedis, JSON.toJSONString(sessionBO),
        getAnonymousSessionInRedisExpiresSeconds());
  }

  @Override
  public Boolean updateNamedSession(SessionBO sessionBO) {
    String keyInRedis = getSessionKeyInRedis(sessionBO.getUid());
    redisClient.setex(keyInRedis, JSON.toJSONString(sessionBO),
        getNamedSessionInRedisExpiresSeconds());
    return true;
  }

  private String getSessionKeyInRedis(Long uid) {
    return StringUtils.join(new String[]{getSessionInRedisPrefix(), uid.toString()}, ":");
  }

}
