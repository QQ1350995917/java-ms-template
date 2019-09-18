package pwd.initializr.common.test.vcode;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import org.junit.Assert;
import org.junit.Test;
import pwd.initializr.common.vcode.StringCode;
import pwd.initializr.common.vcode.VCodeHelper;

/**
 * pwd.initializr.common.test.vcode@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-18 19:09
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class VCodeHelperTest {

  @Test
  public void testCodeMessage(){
    VCodeHelper vCodeHelper = new StringCode();

    String productMessageNull = vCodeHelper.productMessage();
    Assert.assertEquals(6,productMessageNull.length());

    String productMessage0 = vCodeHelper.productMessage(0);
    Assert.assertEquals(1,productMessage0.length());

    String productMessage1 = vCodeHelper.productMessage(1);
    Assert.assertEquals(1,productMessage1.length());

    String productMessage3 = vCodeHelper.productMessage(3);
    Assert.assertEquals(3,productMessage3.length());

    String productMessage6 = vCodeHelper.productMessage(6);
    Assert.assertEquals(6,productMessage6.length());

    String productMessage9 = vCodeHelper.productMessage(9);
    Assert.assertEquals(9,productMessage9.length());

    String productMessage10 = vCodeHelper.productMessage(10);
    Assert.assertEquals(9,productMessage10.length());
  }

  public static void main(String[] args) throws Exception {
    VCodeHelper vCodeHelper = new StringCode();
    BufferedImage bufferedImage = vCodeHelper.productImage();
    FileOutputStream fileOutputStream = new FileOutputStream("e://vcode.jpg");
    ImageIO.write(bufferedImage,"jpg",fileOutputStream);
    fileOutputStream.close();
  }

}
