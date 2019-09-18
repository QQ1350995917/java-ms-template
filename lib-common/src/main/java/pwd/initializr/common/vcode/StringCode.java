package pwd.initializr.common.vcode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * pwd.initializr.common.vcode@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-18 18:57
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class StringCode implements VCodeHelper {

  private static final String originCode = "abcdefghigklmnopqrstuvwxyzZXCVBNMASDFGHJKLQWERTYUIOP0123456789";

  @Override
  public String productMessage() {
    return productMessage(null);
  }

  @Override
  public String productMessage(Integer length) {
    if (length == null) {
      length = 6;
    }

    if (length < 1) {
      length = 1;
    }

    if (length > 9) {
      length = 9;
    }

    StringBuilder stringBuilder = new StringBuilder();
    Random random = new Random();
    for (int i = 0; i < length; i++) {
      char code = originCode.charAt(random.nextInt(originCode.length()));
      stringBuilder.append(code);
    }
    return stringBuilder.toString();
  }

  @Override
  public BufferedImage productImage() {
    return productImage(this.productMessage());
  }

  @Override
  public BufferedImage productImage(String codeMessage) {
    if (codeMessage == null || codeMessage.trim().equals("")) {
      throw new RuntimeException("验证码信息不可为空");
    }

    if (codeMessage.length() > 9) {
      throw new RuntimeException("验证码信息超出长度限制");
    }

    int width = 300;
    int height = 60;
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
    Graphics2D graphics = bufferedImage.createGraphics();
    Random random = new Random();
    graphics.setColor(Color.WHITE);
    graphics.fillRect(0, 0, width, height);
    Font font = new Font("Fixedsys", Font.BOLD, 50);
    graphics.setFont(font);

    graphics.setColor(Color.BLACK);
    graphics.drawRect(0, 0, width - 1, height - 1);
    // 随机产生干扰线
    for (int i = 0; i < 30; i++) {
      int x = random.nextInt(width);
      int y = random.nextInt(height);
      int xl = random.nextInt(12);
      int yl = random.nextInt(12);
      graphics.drawLine(x, y, x + xl, y + yl);
    }
    int red = 0, green = 0, blue = 0;
    for (int i = 0; i < codeMessage.length(); i++) {
      String code = codeMessage.substring(i);
      red = random.nextInt(255);
      green = random.nextInt(255);
      blue = random.nextInt(255);

      graphics.setColor(new Color(red, green, blue));
      graphics.drawString(code, (i + 1) * 30, 45);
    }
    System.out.println(codeMessage);
    graphics.dispose();
    return bufferedImage;
  }


}
