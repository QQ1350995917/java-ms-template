package pwd.initializr.account.test.admin.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.account.AccountApplication;
import pwd.initializr.account.business.admin.SessionService;
import pwd.initializr.account.business.admin.bo.AdminAccountBO;
import pwd.initializr.account.business.admin.bo.SessionCaptchaBO;
import pwd.initializr.account.business.admin.bo.SessionCookieBO;

/**
 * pwd.initializr.account.test.admin.service@ms-web-initializr
 *
 * <h1>单元测试：SessionServiceTest</h1>
 *
 * date 2020-07-21 22:56
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AccountApplication.class})
public class SessionServiceTest {

    @Autowired
    private SessionService sessionService;

    @Test
    public void testQueryCookie() {
        SessionCookieBO sessionCookieBO = new SessionCookieBO(
            "1595428898246:042ad7e9-d6c1-492a-9eae-4ee365de573b");
        Assert.assertNotNull(sessionService.queryCookie(sessionCookieBO));
    }

    @Test
    public void testCreateSessionByNameAndPwd() {
        AdminAccountBO sessionByNameAndPwd = sessionService.createSessionByNameAndPwd("pwd", "pwd");
        Assert.assertNotNull(sessionByNameAndPwd);
    }

    @Test
    public void testCreateCookie() {
        SessionCookieBO sessionCookieBO = sessionService.createCookie();
        Assert.assertNotNull(sessionCookieBO);
    }

    @Test
    public void testCreateCaptcha() {
        SessionCookieBO sessionCookieBO = sessionService.createCookie();
        SessionCaptchaBO captcha = sessionService.createCaptcha(sessionCookieBO);
        Assert.assertNotNull(captcha);
    }


}
