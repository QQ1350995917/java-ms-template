package pwd.initializr.etl.test;

import java.io.File;
import java.util.Random;
import java.util.UUID;

/**
 * pwd.initializr.etl.test@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-01-18 14:11
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class FileMock {

  public static void main(String[] args) throws Exception {
    String fileDir = "/Users/pwd/Documents/etl/input";
    Integer num = 3;
    Random random = new Random(3000);
    for (int i = 0; i < num; i++) {
      new Thread(new Runnable(){
        @Override
        public void run() {
          try {
            while (true) {
              String file = fileDir + File.separator + UUID.randomUUID();
              new File(file + ".data").createNewFile();
              Thread.sleep(random.nextInt(5000));
              new File(file + ".ok").createNewFile();
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }).start();
    }
  }

}
