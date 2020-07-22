package pwd.initializr.account.business.admin;

import com.alibaba.fastjson.JSON;
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

    @Autowired
    private RedisClient redisClient;

    @Resource
    private AdminAccountDao adminAccountDao;


    @Override
    public LoginCookieBO produceCookie() {
        return null;
    }

    @Override
    public Boolean checkCookie(LoginCookieBO loginCookieBO) {
        return null;
    }

    @Override
    public LoginCaptchaBO produceCaptcha(LoginCookieBO loginCookieBO) {
        return null;
    }

    @Override
    public Boolean checkCaptcha(LoginCookieBO loginCookieBO, LoginCaptchaBO loginCaptchaBO) {
        return null;
    }

    @Override
    public AdminAccountBO createSessionByNameAndPwd(String loginName, String loginPwd) {
        Assert.notNull(loginName, "Login name should not be empty");
        Assert.notNull(loginName, "Login password should not be empty");
        // fixme:优化加密
        String encrypt = Cryptographer.encrypt(loginPwd, loginName);
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
