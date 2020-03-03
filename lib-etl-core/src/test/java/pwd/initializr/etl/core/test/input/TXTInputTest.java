package pwd.initializr.etl.core.test.input;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import pwd.initializr.etl.core.ETLDriver;

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
public class TXTInputTest {

  public static void main(String[] args) {
    String jsonFilePath = "/Users/pwd/workspace/dingpw/ms-web-initializr/lib-etl-core/src/test/resources/config-instance-mac.json";
    ETLDriver etlDriver = new ETLDriver().start(jsonFilePath, null);
    BlockingQueue<Map<String, Object>> blockingQueue = etlDriver.getInputBlockingQueue();
    new Thread(new Consume(blockingQueue)).start();
    String completeSuffix = "ok";
    String sourceDir = "";
    String suffix = "txt";
    new Thread(new TXTInputTest().new Produce(sourceDir, suffix, completeSuffix)).start();
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
      for (int i = 0; i < 10; i++) {
        String fakeFilePath =
            sourceDir + File.separator + UUID.randomUUID().toString() + "-" + System
                .currentTimeMillis();
        String dataFilePath = fakeFilePath + "." + suffix;
        String okFilePath = fakeFilePath + "." + completeSuffix;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dataFilePath));) {
          for (int j = 0; j < 100; j++) {
            bufferedWriter.write("我|和|我|的|祖|国|a");
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

}
