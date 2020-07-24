package pwd.initializr.account.business.admin;

import com.alibaba.fastjson.JSON;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
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
import pwd.initializr.common.vcode.CaptchaArithmeticCode;
import pwd.initializr.common.vcode.CaptchaHelper;
import pwd.initializr.common.vcode.CodeMessage;
import pwd.initializr.common.web.persistence.entity.EntityEnable;
import sun.misc.BASE64Encoder;

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
    @Value("${account.admin.session.redis.key.prefix}")
    private String sessionRedisKeyPrefix;

    @Autowired
    private RedisClient redisClient;

    @Resource
    private AdminAccountDao adminAccountDao;


    @Override
    public void createSession(String token,SessionBO sessionBO) {
        String sessionKeyInRedis = getSessionKeyInRedis(token, sessionBO.getUid());
        redisClient.set(sessionKeyInRedis,JSON.toJSONString(sessionBO));
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
        redisClient.del(getCookieKeyInRedis(clearTextCookie));
        return true;
    }

    @Override
    public Boolean deleteSession(Long adminUserId) {
        String key = StringUtils.join(new String[]{SESSION_PREFIX, adminUserId.toString()});
        return redisClient.del(key) == 1;
    }

    @Override
    public SessionCaptchaBO createCaptcha(SessionCookieBO sessionCookieBO) {
        // TODO 生成验证码的逻辑用着不舒服
        CaptchaHelper captchaHelper = new CaptchaArithmeticCode();
        CodeMessage codeMessage = captchaHelper.productMessage();
        redisClient.hset(getCookieKeyInRedis(sessionCookieBO.getCookie()), "captcha",
            codeMessage.getExpected());
        String presented = codeMessage.getPresented();
        BufferedImage bufferedImage = captchaHelper.productImage(presented);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        } catch (IOException e) {
            // TODO 不应该吃掉异常
            e.printStackTrace();
        }
        String encode = new BASE64Encoder().encode(byteArrayOutputStream.toByteArray()).replaceAll("\r|\n", "");;
        SessionCaptchaBO sessionCaptchaBO = new SessionCaptchaBO();
        sessionCaptchaBO.setBase64(encode);
        return sessionCaptchaBO;
    }

    @Override
    public SessionCookieBO createCookie() {
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
            .hset(getCookieKeyInRedis(clearTextCookie), "times", "0");
        return new SessionCookieBO(encryptTextCookie, 0);
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
        String timesOfCookie = redisClient.hget(getCookieKeyInRedis(clearTextCookie), "times");
        String captchaOfCookie = redisClient.hget(getCookieKeyInRedis(clearTextCookie), "captcha");
        if (timesOfCookie != null) {
            return new SessionCookieBO(clearTextCookie, Integer.parseInt(timesOfCookie),captchaOfCookie);
        } else {
            return null;
        }
    }

    @Override
    public SessionBO querySession(String token,Long uid) {
        String key = getSessionKeyInRedis(token,uid);
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

    @Override
    public SessionCookieBO updateCookieTimes(SessionCookieBO sessionCookieBO) {
        String clearTextCookie = null;
        try {
            clearTextCookie = Cryptographer.decrypt(sessionCookieBO.getCookie(), cookieSalt);
        } catch (Exception e) {
            // TODO 不应该吃掉异常
            e.printStackTrace();
        }
        String times = redisClient.hget(getCookieKeyInRedis(clearTextCookie), "times");
        int i = Integer.parseInt(times) + 1;
        redisClient
            .hset(getCookieKeyInRedis(clearTextCookie), "times",String.valueOf(i));
        sessionCookieBO.setTimes(i);
        return sessionCookieBO;
    }

    private String getCookieKeyInRedis(String cookie) {
        return StringUtils.join(new String[]{cookieRedisKeyPrefix, cookie}, ":");
    }

    private String getSessionKeyInRedis(String session,Long uid) {
        return StringUtils.join(new String[]{cookieRedisKeyPrefix,uid + "", session}, ":");
    }
}
