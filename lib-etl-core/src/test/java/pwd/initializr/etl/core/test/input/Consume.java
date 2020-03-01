package pwd.initializr.etl.core.test.input;

import com.alibaba.fastjson.JSON;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

/**
 * pwd.initializr.etl.core.test.input@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-01 20:53
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class Consume implements Runnable {

  private BlockingQueue<Map<String, Object>> blockingQueue;

  Consume(BlockingQueue<Map<String, Object>> blockingQueue) {
    this.blockingQueue = blockingQueue;
  }

  @Override
  public void run() {
    while (true) {
      try {
        System.out.println(this.blockingQueue.size());
        Map take = this.blockingQueue.take();
        System.out.println(JSON.toJSON(take));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
