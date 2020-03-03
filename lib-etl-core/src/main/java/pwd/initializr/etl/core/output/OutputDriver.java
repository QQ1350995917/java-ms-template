package pwd.initializr.etl.core.output;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * pwd.initializr.etl.core.output@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-03 16:32
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class OutputDriver {

  private ExecutorService executorService;
  private BlockingQueue<Map<String, Object>> outBlockingQueue;
  private List<Output> outputs = new LinkedList<>();

  public OutputDriver setConfig(JSONObject config) {
    JSONArray outputs = config.getJSONArray("outputs");
    Iterator<Object> iterator = outputs.iterator();
    while (iterator.hasNext()) {
      JSONObject outputConfig = (JSONObject) iterator.next();
      String type = outputConfig.getString("type");
      Output instance = OutputFactory.getInstance(type).setConfig(outputConfig);
      this.outputs.add(instance);
    }
    return this;
  }

  public OutputDriver setOutBlockingQueue(BlockingQueue<Map<String, Object>> outBlockingQueue) {
    this.outBlockingQueue = outBlockingQueue;
    return this;
  }

  public OutputDriver start() {

    this.executorService = Executors.newCachedThreadPool();
    for (Output output : outputs) {
      output.setOutBlockingQueue(this.outBlockingQueue);
      if (output.isEnable()) {
        for (int i = 0; i < output.getThreadNum(); i++) {
          this.executorService.execute(output);
        }
      }
    }
    return this;
  }

}
