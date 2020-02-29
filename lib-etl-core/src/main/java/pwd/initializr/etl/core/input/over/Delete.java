package pwd.initializr.etl.core.input.over;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * pwd.initializr.etl.core.input.over@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-02-27 10:26
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class Delete extends DefaultFileOver {

  @Override
  protected void onOver(String dataingFilePath, String okingFilePath, String fileName) {
    try {
      Files.deleteIfExists(Paths.get(dataingFilePath));
      Files.deleteIfExists(Paths.get(okingFilePath));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
