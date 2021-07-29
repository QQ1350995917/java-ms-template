package pwd.initializr.common.test.utils;

import org.junit.Test;
import pwd.initializr.common.utils.CryptographerPbkdf;

/**
 * pwd.initializr.common.test.utils@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-07-29 22:19
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class CryptographerPbkdfTest {

  @Test
  public void gen() {
    String s = CryptographerPbkdf.randomSalt();
    String admin = CryptographerPbkdf.encrypt("admin", s);
    System.out.println(s);
    System.out.println(admin);
  }
}
