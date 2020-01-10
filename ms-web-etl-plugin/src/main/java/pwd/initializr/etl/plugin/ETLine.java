package pwd.initializr.etl.plugin;

import pwd.initializr.etl.ETLDefaultHandler;
import pwd.initializr.etl.ETLHandler;

/**
 * pwd.initializr.etl.plugins@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-01-09 20:47
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class ETLine extends ETLDefaultHandler {

  @Override
  public void handle(Object object) {
    System.out.println("bingo000");
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
