package pwd.initializr.etl.core.input.processor;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
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
        InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream,Charset.forName(getCharset()));
        BufferedReader input = new BufferedReader(inputStreamReader);
    ) {
      String line;
      Integer lineNum = 0;
      while ((line = input.readLine()) != null) {
        lineNum++;
        putToInputBlockingQueue(buildMap(line,filePath,lineNum));
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  protected Map<String,Object> buildMap(String line,String filePath,Integer lineNum){
    Map<String, Object> map = new HashMap<>(3);
    map.put("_file", filePath);
    map.put("_lineNumber", Integer.toString(lineNum++));
    map.put("_value", line);
    return map;
  }
}
