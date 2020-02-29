package pwd.initializr.etl.core.input.processor;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * pwd.initializr.etl.core.input.processor@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-02-27 10:45
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class TXTProcessor extends DefaultFileProcessor {

  @Override
  public void onProcess(String filePath) {
    try (
        FileInputStream fileInputStream = new FileInputStream(filePath);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);
        BufferedReader input = new BufferedReader(inputStreamReader);
    ) {
      String line;
      Integer lineNum = 0;
      while ((line = input.readLine()) != null) {
        String[] split = line.split(getColumnDelimiter());

        Map<String, String> map = new HashMap<>(split.length + 2);
        map.put("_file", filePath);
        map.put("_line_number", Integer.toString(lineNum++));

        for (int i = 0; i < split.length; i++) {
          map.put(Integer.toString(i), split[i]);
        }
        this.getBlockingQueue().put(map);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
