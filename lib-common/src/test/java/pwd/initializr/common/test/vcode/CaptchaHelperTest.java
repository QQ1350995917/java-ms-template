package pwd.initializr.common.test.vcode;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;
import org.junit.Assert;
import org.junit.Test;
import pwd.initializr.common.vcode.CaptchaArithmeticCode;
import pwd.initializr.common.vcode.CodeMessage;
import pwd.initializr.common.vcode.CaptchaGoogleCode;
import pwd.initializr.common.vcode.StringCode;
import pwd.initializr.common.vcode.CaptchaHelper;

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
public class CaptchaHelperTest {

    @Test
    public void testCodeMessage() {
        CaptchaHelper captchaHelper = new StringCode();

        CodeMessage productMessageNull = captchaHelper.productMessage();
        Assert.assertEquals(6, productMessageNull.getPresented().length());
    }

    public static void main(String[] args) throws Exception {
        createStringCode(new StringCode());
        createArithmeticCode(new CaptchaArithmeticCode());
        createGoogleCode(new CaptchaGoogleCode());
    }

    private static void createStringCode(CaptchaHelper captchaHelper) throws Exception {
        BufferedImage bufferedImage = captchaHelper.productImage();
        FileOutputStream fileOutputStream = new FileOutputStream("CodeString.jpg");
        ImageIO.write(bufferedImage, "jpg", fileOutputStream);
        fileOutputStream.close();
    }

    private static void createArithmeticCode(CaptchaHelper captchaHelper) throws Exception {
        BufferedImage bufferedImage = captchaHelper.productImage();
        FileOutputStream fileOutputStream = new FileOutputStream("CodeArithmetic.jpg");
        ImageIO.write(bufferedImage, "jpg", fileOutputStream);
        fileOutputStream.close();
    }

    private static void createGoogleCode(CaptchaHelper captchaHelper) throws Exception {
        BufferedImage bufferedImage = captchaHelper.productImage();
        FileOutputStream fileOutputStream = new FileOutputStream("CodeGoogle.jpg");
        ImageIO.write(bufferedImage, "jpg", fileOutputStream);
        fileOutputStream.close();
    }

}
