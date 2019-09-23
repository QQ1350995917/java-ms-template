package pwd.initializr.common.vcode;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * pwd.initializr.common.vcode@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-23 17:20
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class SMSCode extends VCodeHelper {

  private final String originCode = "0123456789";

  @Override
  public String getOriginCode() {
    return originCode;
  }

  @Override
  public BufferedImage productImage() {
    return null;
  }

  @Override
  public BufferedImage productImage(String codeMessage) {
    return null;
  }
}
