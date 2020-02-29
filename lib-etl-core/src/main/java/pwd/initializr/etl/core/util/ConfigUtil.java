package pwd.initializr.etl.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * pwd.initializr.etl.core.util@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-02-29 10:00
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class ConfigUtil {

  public static JSONObject loadConfig(String configFilePath) {
    StringBuilder stringBuilder = new StringBuilder();
    try (
        BufferedReader bufferedReader = new BufferedReader(
            new FileReader(new File(configFilePath)));
    ) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        stringBuilder.append(line);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return JSON.parseObject(stringBuilder.toString());
  }
}
