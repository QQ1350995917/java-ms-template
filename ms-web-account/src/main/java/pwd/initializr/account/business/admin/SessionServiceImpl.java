package pwd.initializr.account.business.admin;

import com.alibaba.fastjson.JSON;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pwd.initializr.account.business.admin.bo.SessionBO;
import pwd.initializr.account.business.admin.bo.SessionCaptchaBO;
import pwd.initializr.account.business.admin.bo.SessionCookieBO;
import pwd.initializr.common.mw.redis.RedisClient;
import pwd.initializr.common.utils.Cryptographer;
import pwd.initializr.common.vcode.CaptchaArithmeticCode;
import pwd.initializr.common.vcode.CaptchaHelper;
import pwd.initializr.common.vcode.CodeMessage;
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
    private String cookieInRedisPrefix;
    @Value("${account.admin.cookie.expires.seconds}")
    private Integer cookieInRedisExpiresSeconds;
    @Value("${account.admin.session.redis.key.prefix}")
    private String sessionInRedisPrefix;
    @Value("${account.admin.session.expires.seconds}")
    private Integer sessionInRedisExpiresSeconds;

    @Autowired
    private RedisClient redisClient;

    @Override
    public void createSession(String token, SessionBO sessionBO) {
        String sessionKeyInRedis = getSessionKeyInRedis(sessionBO.getUid());
        redisClient.set(sessionKeyInRedis, JSON.toJSONString(sessionBO));
    }

    @Override
    public Boolean deleteCookie(String cookie) {
        Assert.notNull(cookie, "sessionCookieBO.cookie should not be empty");
        String clearTextCookie = Cryptographer.decrypt(cookie, cookieSalt);
        redisClient.del(getCookieKeyInRedis(clearTextCookie));
        return true;
    }

    @Override
    public Boolean deleteSession(Long uid) {
        String key = getSessionKeyInRedis(uid);
        return redisClient.del(key) == 1;
    }

    @Override
    public SessionCaptchaBO createCaptcha(String cookie) {
        // TODO 生成验证码的逻辑用着不舒服
        CaptchaHelper captchaHelper = new CaptchaArithmeticCode();
        CodeMessage codeMessage = captchaHelper.productMessage();
        // 更新redis中的sessionCookie对象
        SessionCookieBO sessionCookieBO = queryCookie(cookie);
        sessionCookieBO.setCaptcha(codeMessage.getExpected());
        updateCookie(cookie,sessionCookieBO);
        // 输出sessionCookie对象
        String presented = codeMessage.getPresented();
        BufferedImage bufferedImage = captchaHelper.productImage(presented);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        } catch (IOException e) {
            // TODO 不应该吃掉异常
            e.printStackTrace();
        }
        String encode = new BASE64Encoder().encode(byteArrayOutputStream.toByteArray())
            .replaceAll("\r|\n", "");
        SessionCaptchaBO sessionCaptchaBO = new SessionCaptchaBO();
        sessionCaptchaBO.setBase64(encode);
        return sessionCaptchaBO;
    }

    @Override
    public String createCookie() {
        long currentTimeMillis = System.currentTimeMillis();
        String uuid = UUID.randomUUID().toString();
        String clearTextCookie = currentTimeMillis + ":" + uuid;
        String encryptTextCookie = Cryptographer.encrypt(clearTextCookie, cookieSalt);
        SessionCookieBO sessionCookieBO = new SessionCookieBO(0,null);
        redisClient.setex(getCookieKeyInRedis(clearTextCookie), JSON.toJSONString(sessionCookieBO),cookieInRedisExpiresSeconds);
        return encryptTextCookie;
    }

    @Override
    public SessionCookieBO queryCookie(String cookie) {
        Assert.notNull(cookie, "cookie should not be empty");
        String clearTextCookie = Cryptographer.decrypt(cookie, cookieSalt);
        String sessionCookieJson = redisClient.get(getCookieKeyInRedis(clearTextCookie));
        if (StringUtils.isBlank(sessionCookieJson)) {
            return null;
        }
        return JSON.parseObject(sessionCookieJson, SessionCookieBO.class);
    }

    @Override
    public SessionBO querySession(Long uid) {
        String key = getSessionKeyInRedis(uid);
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
    public void updateCookie(String cookie,SessionCookieBO sessionCookieBO) {
        String clearTextCookie = Cryptographer.decrypt(cookie, cookieSalt);
        redisClient.set(getCookieKeyInRedis(clearTextCookie), JSON.toJSONString(sessionCookieBO),cookieInRedisExpiresSeconds);
    }

    private String getCookieKeyInRedis(String cookie) {
        return StringUtils.join(new String[]{cookieInRedisPrefix, cookie}, ":");
    }

    private String getSessionKeyInRedis(Long uid) {
        return StringUtils.join(new String[]{cookieInRedisPrefix, uid.toString()}, ":");
    }
}
