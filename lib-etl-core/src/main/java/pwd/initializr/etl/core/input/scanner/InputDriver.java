package pwd.initializr.etl.core.input.scanner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

  private BlockingQueue<Map> blockingQueue = new ArrayBlockingQueue<>(128);
  private ExecutorService executorService = Executors.newFixedThreadPool(1);
  private List<FileScanner> fileScanners = new LinkedList<>();

  public InputDriver() {

  }

  public InputDriver(JSONObject config) {
    this.setConfig(config);
  }

  public InputDriver setConfig(JSONObject config) {
    Integer capacity = config.getInteger("capacity");
    this.blockingQueue = new LinkedBlockingQueue<>(capacity);

    JSONArray scanners = config.getJSONArray("scanners");
    Iterator<Object> iterator = scanners.iterator();
    while (iterator.hasNext()) {
      JSONObject scanner = (JSONObject) iterator.next();
      String name = scanner.getString("name");
      String type = scanner.getString("type");
      Boolean enable = scanner.getBoolean("enable");
      if ("file".equals(type)) {
        this.fileScanners.add(new FileScanner(scanner).setBlockingQueue(this.blockingQueue));
      }
    }
    return this;
  }

  public BlockingQueue<Map> getBlockingQueue() {
    return blockingQueue;
  }

  public void start() {
    this.executorService = Executors.newFixedThreadPool(fileScanners.size());
    for (FileScanner fileScanner : fileScanners) {
      this.executorService.execute(fileScanner);
    }
  }
}
