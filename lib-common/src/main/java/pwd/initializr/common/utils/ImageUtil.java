package pwd.initializr.common.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;

/**
 * pwd.initializr.common.utils@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-04-10 21:52
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class ImageUtil {

  /**
   * 将Base64编码转换成图片
   *
   * @param base64 待处理图片base64
   * @param imageFilePath 待处理图片
   */
  public static boolean base642image(String base64, String imageFilePath) {
    if (base64 == null) {
      return false;
    }
    BASE64Decoder decoder = new BASE64Decoder();
    try (OutputStream out = new FileOutputStream(imageFilePath);) {
      byte[] b = decoder.decodeBuffer(base64);
      for (int i = 0; i < b.length; ++i) {
        if (b[i] < 0) {
          b[i] += 256;
        }
      }
      out.write(b);
      out.flush();
      out.close();
      return true;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static void format(File sourceImageFile, File targetImageFile, String targetImageFormat) {
    try {
      BufferedImage image = ImageIO.read(sourceImageFile);
      int width = image.getWidth();
      int height = image.getHeight();
      BufferedImage resize = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      Graphics2D g2d = resize.createGraphics();
      g2d.drawImage(image, 0, 0, null);
      g2d.dispose();
      ImageIO.write(resize, targetImageFormat, targetImageFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 将图片转换成Base64编码
   *
   * @param imageFile 待处理图片
   */
  public static String image2base64(String imageFile) {
    try (InputStream inputStream = new FileInputStream(imageFile);) {
      byte[] data = new byte[inputStream.available()];
      inputStream.read(data);
      return new String(Base64.encodeBase64(data));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
