package pwd.initializr.etl;

import java.io.InputStream;

/**
 * pwd.initializr.etl@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-01-10 14:26
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface ETLController {
  void start(InputStream application) throws Exception;
  void pause();
  void stop();
  void restart();
}
