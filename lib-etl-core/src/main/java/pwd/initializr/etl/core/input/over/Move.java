package pwd.initializr.etl.core.input.over;

import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
public class Move extends DefaultFileOver {

  protected String targetDir;

  @Override
  public Move setConfig(JSONObject config) {
    super.setConfig(config);
    this.targetDir = config.getString("targetDir");
    return this;
  }

  @Override
  protected void onOver(String dataingFilePath, String okingFilePath, String fileName) {
    try {
      Path targetDataPath = Paths.get(this.targetDir + File.separator + fileName + "." + this.getSuffix());
      Path targetOkPath = Paths.get(this.targetDir + File.separator + fileName + "." + this.getCompleteSuffix());
      Files.deleteIfExists(targetDataPath);
      Files.deleteIfExists(targetOkPath);
      Files.move(Paths.get(dataingFilePath), targetOkPath);
      Files.move(Paths.get(okingFilePath), targetDataPath);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
