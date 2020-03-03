package pwd.initializr.etl.core.handle;

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

/**
 * pwd.initializr.etl.core.handle@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-03 09:57
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class HandlerDriver {

  private JSONObject config;
  private List<Handler> handlers;
  private BlockingQueue<Map<String, Object>> inputBlockingQueue;
  private BlockingQueue<Map<String, Object>> outputBlockingQueue;

  public BlockingQueue<Map<String, Object>> getOutBlockingQueue() {
    return this.outputBlockingQueue;
  }

  public HandlerDriver setConfig(JSONObject config) {
    this.config = config;
    Integer capacity = this.config.getInteger("capacity");
    this.outputBlockingQueue = new ArrayBlockingQueue<>(capacity);
    return this;
  }

  public HandlerDriver setInputBlockingQueue(BlockingQueue<Map<String, Object>> blockingQueue) {
    this.inputBlockingQueue = blockingQueue;
    return this;
  }

  public HandlerDriver start() {
    Integer threadNum = config.getInteger("threadNum");
    ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
    JSONArray handlers = config.getJSONArray("handlers");
    this.handlers = new LinkedList<>();
    Iterator<Object> iterator = handlers.iterator();
    while (iterator.hasNext()) {
      JSONObject handler = (JSONObject) iterator.next();
      if (!handler.getBoolean("enable")) {
        continue;
      }
      String aClass = handler.getString("class");
      Handler instance = HandlerFactory.getInstance(aClass);
      instance.init(handler);
      this.handlers.add(instance);
    }

    for (int i = 0; i < threadNum; i++) {
      executorService.execute(() -> {
        while (true) {
          // Fixme 异常后线程退出执行，直至降低为零，不再执行
          try {
            Map<String, Object> take = inputBlockingQueue.take();
            for (Handler handler : this.handlers) {
              take = handler.handle(take);
            }
            outputBlockingQueue.put(take);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        }
      });
    }
    return this;
  }

  private void loadPlugin() {

  }
}
