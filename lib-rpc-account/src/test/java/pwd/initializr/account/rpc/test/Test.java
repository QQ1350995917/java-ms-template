package pwd.initializr.account.rpc.test;

import org.apache.commons.lang.StringUtils;

/**
 * pwd.initializr.account.rpc.test@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-06 22:36
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class Test {

  public static void main(String[] args) {
    long id0 = System.currentTimeMillis();
    long id1 = 999999;
    String uid = StringUtils.join(new String[]{String.valueOf(id0), String.valueOf(id1)}, "");
    long l = Long.parseLong(uid);
    System.out.println(l);
  }
}
