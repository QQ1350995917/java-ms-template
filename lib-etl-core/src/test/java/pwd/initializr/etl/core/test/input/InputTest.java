package pwd.initializr.etl.core.test.input;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import pwd.initializr.etl.core.input.scanner.InputDriver;
import pwd.initializr.etl.core.util.ConfigUtil;

/**
 * pwd.initializr.etl.core.test.input@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-02-28 13:29
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class InputTest {

  public static void main(String[] args) {
    String jsonFilePath = "/Users/pwd/workspace/dingpw/ms-web-initializr/lib-etl-core/src/test/resources/config-instance-win.json";
    JSONObject config = ConfigUtil.loadConfig(jsonFilePath);
    JSONObject inputConfig = config.getJSONObject("input");
    InputDriver inputDriver = new InputDriver(inputConfig);
    inputDriver.start();
    BlockingQueue<Map> blockingQueue = inputDriver.getBlockingQueue();
    JSONArray scanners = inputConfig.getJSONArray("scanners");
    Iterator<Object> iterator = scanners.iterator();
    while (iterator.hasNext()) {
      JSONObject next = (JSONObject)iterator.next();
      String sourceDir = next.getString("sourceDir");
      String suffix = next.getString("suffix");
      String completeSuffix = next.getString("completeSuffix");
      new Thread(new Produce(sourceDir,suffix,completeSuffix)).start();
    }

    new Thread(new Consume(blockingQueue)).start();
  }
}

class Consume implements Runnable {

  private BlockingQueue<Map> blockingQueue;

  Consume(BlockingQueue<Map> blockingQueue) {
    this.blockingQueue = blockingQueue;
  }

  @Override
  public void run() {
    while (true) {
      try {
        System.out.println(this.blockingQueue.size());
        this.blockingQueue.take();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

class Produce implements Runnable {

  private String completeSuffix;
  private String sourceDir;
  private String suffix;

  public Produce(String sourceDir, String suffix, String completeSuffix) {
    this.sourceDir = sourceDir;
    this.suffix = suffix;
    this.completeSuffix = completeSuffix;
  }

  @Override
  public void run() {
    for (int i = 0; i < 1000; i++) {
      String fakeFilePath =
          sourceDir + File.separator + UUID.randomUUID().toString() + "-" + System
              .currentTimeMillis();
      String dataFilePath = fakeFilePath + "." + suffix;
      String okFilePath = fakeFilePath + "." + completeSuffix;
      try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dataFilePath));) {
        for (int j = 0; j < 100; j++) {
          bufferedWriter.write("a|b|c|d|e|f|g");
          bufferedWriter.newLine();
        }
        bufferedWriter.flush();
      } catch (Exception e) {
        e.printStackTrace();
      }

      try {
        new File(okFilePath).createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }
}
