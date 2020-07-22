package pwd.initializr.account.business.admin;

import com.alibaba.fastjson.JSON;
import java.util.UUID;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pwd.initializr.account.business.admin.bo.AdminAccountBO;
import pwd.initializr.account.business.admin.bo.SessionBO;
import pwd.initializr.account.business.admin.bo.SessionCaptchaBO;
import pwd.initializr.account.business.admin.bo.SessionCookieBO;
import pwd.initializr.account.persistence.dao.AdminAccountDao;
import pwd.initializr.account.persistence.entity.AdminAccountEntity;
import pwd.initializr.common.mw.redis.RedisClient;
import pwd.initializr.common.utils.Cryptographer;
import pwd.initializr.common.web.persistence.entity.EntityEnable;

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

  @Value("${account.admin.cookie.initializr.salt}")
  private String cookieSalt;
  @Value("${account.admin.cookie.redis.key.prefix}")
  private String cookieRedisKeyPrefix;

  @Autowired
  private RedisClient redisClient;

  @Resource
  private AdminAccountDao adminAccountDao;


  @Override
  public AdminAccountBO createSessionByNameAndPwd(String loginName, String loginPwd) {
    Assert.notNull(loginName, "Login name should not be empty");
    Assert.notNull(loginName, "Login password should not be empty");
    String encrypt = null;
    try {
      encrypt = Cryptographer.encrypt(loginPwd, loginName);
    } catch (Exception e) {
      e.printStackTrace();
    }
    AdminAccountEntity adminAccountEntity = adminAccountDao
        .queryByLoginNameAndPwd(loginName, encrypt);
    Assert.notNull(adminAccountEntity, "The user does not exist");
    if (adminAccountEntity.getEnable() == EntityEnable.ENABLE.getNumber()) {
      // TODO:存放session信息和在线用户信息
    }
    AdminAccountBO adminAccountBO = new AdminAccountBO();
    BeanUtils.copyProperties(adminAccountEntity, adminAccountBO);
    return adminAccountBO;
  }

  @Override
  public AdminAccountBO createSessionByPhoneNumberAndSmsCode(String phoneNumber, String smsCode) {
    return null;
  }

  @Override
  public Boolean deleteCookie(SessionCookieBO sessionCookieBO) {
    Assert.notNull(sessionCookieBO, "sessionCookieBO should not be null");
    Assert.notNull(sessionCookieBO.getCookie(), "sessionCookieBO.cookie should not be empty");
    String clearTextCookie = null;
    try {
      clearTextCookie = Cryptographer.decrypt(sessionCookieBO.getCookie(), cookieSalt);
    } catch (Exception e) {
      // TODO 不应该吃掉异常
      e.printStackTrace();
    }
    redisClient.del(cookieRedisKeyPrefix + clearTextCookie);
    return true;
  }

  @Override
  public Boolean deleteSession(Long adminUserId) {
    String key = StringUtils.join(new String[]{SESSION_PREFIX, adminUserId.toString()});
    return redisClient.del(key) == 1;
  }

  @Override
  public SessionCaptchaBO produceCaptcha(SessionCaptchaBO sessionCaptchaBO) {
    return null;
  }

  @Override
  public SessionCookieBO produceCookie() {
    long currentTimeMillis = System.currentTimeMillis();
    String uuid = UUID.randomUUID().toString();
    String clearTextCookie = currentTimeMillis + ":" + uuid;
    String encryptTextCookie = null;
    try {
      encryptTextCookie = Cryptographer.encrypt(clearTextCookie, cookieSalt);
    } catch (Exception e) {
      // TODO 不应该吃掉异常
      e.printStackTrace();
    }
    redisClient
        .set(cookieRedisKeyPrefix + clearTextCookie, "0");
    return new SessionCookieBO(encryptTextCookie, 0);
  }

  @Override
  public SessionCaptchaBO queryCaptcha(SessionCookieBO sessionCookieBO,
      SessionCaptchaBO sessionCaptchaBO) {
    return null;
  }

  @Override
  public SessionCookieBO queryCookie(SessionCookieBO sessionCookieBO) {
    Assert.notNull(sessionCookieBO, "sessionCookieBO should not be null");
    Assert.notNull(sessionCookieBO.getCookie(), "sessionCookieBO.cookie should not be empty");
    String clearTextCookie = null;
    try {
      clearTextCookie = Cryptographer.decrypt(sessionCookieBO.getCookie(), cookieSalt);
    } catch (Exception e) {
      // TODO 不应该吃掉异常
      e.printStackTrace();
    }
    String timeOfCookie = redisClient.get(cookieRedisKeyPrefix + clearTextCookie);
    if (timeOfCookie != null) {
      return new SessionCookieBO(clearTextCookie, Integer.parseInt(timeOfCookie));
    } else {
      return null;
    }
  }

  @Override
  public SessionBO querySession(Long adminUserId) {
    String key = StringUtils.join(new String[]{SESSION_PREFIX, adminUserId.toString()});
    String session = redisClient.get(key);
    return JSON.parseObject(session, SessionBO.class);
  }

  @Override
  public Boolean updateSession(SessionBO sessionBO) {
    String key = StringUtils.join(new String[]{SESSION_PREFIX, sessionBO.getId().toString()});
    if (!"0".equals(redisClient.set(key, JSON.toJSONString(sessionBO)))) {
      return true;
    } else {
      return false;
    }
  }
}
