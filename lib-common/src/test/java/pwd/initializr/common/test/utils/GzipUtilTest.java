package pwd.initializr.common.test.utils;

import pwd.initializr.common.utils.GzipUtil;

/**
 * pwd.initializr.common.test.utils@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-03 10:05
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class GzipUtilTest {
  public static void main(String[] args) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < 90; ++i) {
      sb.append("" + i);
    }

    String sourceStr = sb.toString();
    System.out.println(
        "source length:" + sourceStr.getBytes().length + ",source str length:" + sourceStr
            .length());

    try {
      byte[] out1 = GzipUtil.compress(sourceStr.getBytes());
      System.out.println("after compress:" + out1.length);
    } catch (Exception var4) {
      var4.printStackTrace();
    }

    System.out.println("gzip,deflate,sdch".indexOf("gzip"));
  }
}
