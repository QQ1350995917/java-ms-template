package pwd.initializr.common.test.vcode;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import org.junit.Assert;
import org.junit.Test;
import pwd.initializr.common.vcode.ArithmeticCode;
import pwd.initializr.common.vcode.CodeMessage;
import pwd.initializr.common.vcode.GoogleCode;
import pwd.initializr.common.vcode.StringCode;
import pwd.initializr.common.vcode.VCodeHelper;

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
public class VCodeHelperTest {

    @Test
    public void testCodeMessage() {
        VCodeHelper vCodeHelper = new StringCode();

        CodeMessage productMessageNull = vCodeHelper.productMessage();
        Assert.assertEquals(6, productMessageNull.getPresented().length());
    }

    public static void main(String[] args) throws Exception {
        createStringCode(new StringCode());
        createArithmeticCode(new ArithmeticCode());
        createGoogleCode(new GoogleCode());
    }

    private static void createStringCode(VCodeHelper vCodeHelper) throws Exception {
        BufferedImage bufferedImage = vCodeHelper.productImage();
        FileOutputStream fileOutputStream = new FileOutputStream("CodeString.jpg");
        ImageIO.write(bufferedImage, "jpg", fileOutputStream);
        fileOutputStream.close();
    }

    private static void createArithmeticCode(VCodeHelper vCodeHelper) throws Exception {
        BufferedImage bufferedImage = vCodeHelper.productImage();
        FileOutputStream fileOutputStream = new FileOutputStream("CodeArithmetic.jpg");
        ImageIO.write(bufferedImage, "jpg", fileOutputStream);
        fileOutputStream.close();
    }

    private static void createGoogleCode(VCodeHelper vCodeHelper) throws Exception {
        BufferedImage bufferedImage = vCodeHelper.productImage();
        FileOutputStream fileOutputStream = new FileOutputStream("CodeGoogle.jpg");
        ImageIO.write(bufferedImage, "jpg", fileOutputStream);
        fileOutputStream.close();
    }

}
