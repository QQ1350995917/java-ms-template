package pwd.initializr.etl.plugin;

import pwd.initializr.etl.ETLDefaultHandler;

/**
 * pwd.initializr.etl.plugin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-01-14 14:47
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class ETAdditional extends ETLDefaultHandler {

  @Override
  public void handle(Object object) {
    getNext().handle(object);
  }
}
