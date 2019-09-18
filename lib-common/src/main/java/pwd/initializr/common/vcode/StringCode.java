package pwd.initializr.common.vcode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
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
public class StringCode extends VCodeHelper {

  private static final String originCode = "abcdefghigklmnopqrstuvwxyzZXCVBNMASDFGHJKLQWERTYUIOP0123456789";

  @Override
  public CodeMessage productMessage() {
    return productMessage(null);
  }

  @Override
  protected CodeMessage productMessage(Integer length) {
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

    return new CodeMessage(stringBuilder.toString(),stringBuilder.toString());
  }

  @Override
  public BufferedImage productImage() {
    return productImage(this.productMessage().getPresented());
  }

  @Override
  public BufferedImage productImage(String codeMessage) {
    if (codeMessage == null || codeMessage.trim().equals("")) {
      throw new RuntimeException("验证码信息不可为空");
    }

    if (codeMessage.length() > 9) {
      throw new RuntimeException("验证码信息超出长度限制");
    }

    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    draw(bufferedImage,codeMessage);
    return bufferedImage;
  }


}
