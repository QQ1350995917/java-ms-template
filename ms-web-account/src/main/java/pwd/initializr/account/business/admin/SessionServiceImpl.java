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
import pwd.initializr.account.business.admin.bo.LoginCaptchaBO;
import pwd.initializr.account.business.admin.bo.LoginCookieBO;
import pwd.initializr.account.business.admin.bo.SessionBO;
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

  @Value("${account.session.admin.cookie.initializr.salt}")
  private String cookieSalt;
  @Value("${account.session.admin.cookie.expires.seconds}")
  private Integer cookieExpiresSeconds;
  @Value("${account.session.admin.cookie.redis.key.prefix}")
  private String cookieRedisKeyPrefix;

  @Autowired
  private RedisClient redisClient;

  @Resource
  private AdminAccountDao adminAccountDao;

  @Override
  public Boolean checkCaptcha(LoginCookieBO loginCookieBO, LoginCaptchaBO loginCaptchaBO) {
    return null;
  }

  @Override
  public Boolean checkCookie(LoginCookieBO loginCookieBO) {
    Assert.notNull(loginCookieBO, "loginCookieBO should not be null");
    Assert.notNull(loginCookieBO.getCookie(), "loginCookieBO.cookie should not be empty");
    String clearTextCookie = null;
    try {
      clearTextCookie = Cryptographer.decrypt(loginCookieBO.getCookie(), cookieSalt);
    } catch (Exception e) {
      // TODO 不应该吃掉异常
      e.printStackTrace();
    }

    // TODO 优化redis延期方式
    String cookieUseTimes = redisClient.get(cookieRedisKeyPrefix + clearTextCookie);
    if (cookieUseTimes == null) {
      return false;
    } else {
      redisClient.del(cookieRedisKeyPrefix + clearTextCookie);
      redisClient
          .setex(cookieRedisKeyPrefix + clearTextCookie, cookieExpiresSeconds, cookieUseTimes);
      return true;
    }
  }

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
  public Boolean deleteSession(Long adminUserId) {
    String key = StringUtils.join(new String[]{SESSION_PREFIX, adminUserId.toString()});
    return redisClient.del(key) == 1;
  }

  @Override
  public LoginCaptchaBO produceCaptcha(LoginCookieBO loginCookieBO) {
    return null;
  }

  @Override
  public LoginCookieBO produceCookie() {
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
        .setex(cookieRedisKeyPrefix + clearTextCookie, cookieExpiresSeconds, "0");
    return new LoginCookieBO(encryptTextCookie, cookieExpiresSeconds);
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
