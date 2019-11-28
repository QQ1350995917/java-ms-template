package pwd.initializr.common.vcode;

import java.awt.image.BufferedImage;

/**
 * pwd.initializr.common.vcode@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-18 18:57
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class StringCode extends VCodeHelper {

  private final String originCode = "abcdefghigklmnopqrstuvwxyzZXCVBNMASDFGHJKLQWERTYUIOP0123456789";

  @Override
  public String getOriginCode() {
    return originCode;
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

    BufferedImage bufferedImage = draw(codeMessage);
    return bufferedImage;
  }


}
