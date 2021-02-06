package pwd.initializr.common.utils;

import org.apache.commons.lang.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * pwd.initializr.common.utils@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-06 16:25
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class CryptographerBase64 {

  private static BASE64Decoder base64Decoder = new BASE64Decoder();
  private static BASE64Encoder base64Encoder = new BASE64Encoder();

  public static byte[] decode(String text) throws Exception {
    return base64Decoder.decodeBuffer(text);
  }

  public static String encode(byte[] bytes) {
    return base64Encoder.encodeBuffer(bytes);
  }

}
