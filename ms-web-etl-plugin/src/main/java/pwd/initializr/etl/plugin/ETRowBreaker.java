package pwd.initializr.etl.plugin;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import pwd.initializr.etl.ETLDefaultHandler;

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
public class ETRowBreaker extends ETLDefaultHandler {

  private int bufferSize = 100 * 1024 * 1024;
  private int batchSize = 1;


  @Override
  public void handle(Object object) {
    String filePath = object.toString();
    if (filePath == null) {
      throw new RuntimeException("文件全路径不能为空");
    }

    File file = new File(filePath);
    if (!file.exists()) {
      throw new RuntimeException("指定的文件不存在");
    }

    try (
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);
        BufferedReader input = new BufferedReader(inputStreamReader, bufferSize);
    ) {
      List<String> lines = new LinkedList<>();
      String line;
      while ((line = input.readLine()) != null) {
        lines.add(line);
        if (lines.size() == batchSize) {
          this.getNext().handle(lines);
          lines.clear();
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {

    }
  }
}
