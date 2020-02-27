package pwd.initializr.etl.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import pwd.initializr.etl.core.input.scanner.InputDriver;

/**
 * pwd.initializr.etl.core@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-02-26 16:23
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class ETLDriver {

  private String group;
  private String name;

  public static void main(String[] args) {
    String jsonFilePath = "/Users/pwd/workspace/dingpw/ms-web-initializr/lib-etl-core/src/main/resources/config.json";
    StringBuilder stringBuilder = new StringBuilder();
    try (
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(jsonFilePath)));
    ) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        stringBuilder.append(line);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    JSONObject config = JSON.parseObject(stringBuilder.toString());
    JSONObject inputConfig = config.getJSONObject("input");
    new InputDriver(inputConfig);


  }

}
