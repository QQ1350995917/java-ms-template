package pwd.initializr.etl.core.input.scanner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * pwd.initializr.etl.core.input.scanner@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-02-27 10:14
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class InputDriver {

  private BlockingQueue<Map> blockingQueue = new LinkedBlockingQueue<>();
  private List<FileScanner> fileScanners = new LinkedList<>();

  public InputDriver() {
  }

  public InputDriver(JSONObject config) {
    this.setConfig(config);
  }

  public InputDriver setConfig(JSONObject config) {
    JSONArray scanners = config.getJSONArray("scanners");
    Iterator<Object> iterator = scanners.iterator();
    while (iterator.hasNext()) {
      JSONObject scanner = (JSONObject) iterator.next();
      String name = scanner.getString("name");
      Boolean enable = scanner.getBoolean("enable");
      if ("file".equals(name) && enable) {
        FileScanner fileScanner = new FileScanner(scanner).setBlockingQueue(blockingQueue);
        fileScanners.add(fileScanner);
      }
    }
    return this;
  }

  public void start() {
    for (FileScanner fileScanner : fileScanners) {

    }
  }
}
