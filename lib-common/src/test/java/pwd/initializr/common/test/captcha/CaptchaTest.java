package pwd.initializr.common.test.captcha;

import org.junit.Test;
import pwd.initializr.common.captcha.CaptchaArithmetic;
import pwd.initializr.common.captcha.CaptchaHelper;
import pwd.initializr.common.captcha.CaptchaMessage;
import pwd.initializr.common.captcha.CaptchaRandom;

/**
 * pwd.initializr.common.test.vcode@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-18 19:09
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class CaptchaTest {

    @Test
    public void captchaArithmeticTest() {
        CaptchaHelper captchaHelper = new CaptchaArithmetic();
        CaptchaMessage captcha = captchaHelper.createCaptcha();
        System.out.println(captcha);
    }

    @Test
    public void randomTest() {
        CaptchaHelper captchaHelper = new CaptchaRandom(6);
        CaptchaMessage captcha = captchaHelper.createCaptcha();
        System.out.println(captcha);
    }

}
