package pwd.initializr.etl.core.input.processor;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import pwd.initializr.etl.core.input.over.Over;
import pwd.initializr.etl.core.input.over.OverFactory;

/**
 * pwd.initializr.etl.core.input.processor@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-01 17:30
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public abstract class DefaultProcessor implements Processor {

  protected JSONObject config;
  private BlockingQueue<Map<String, Object>> inputBlockingQueue;

  @Override
  public Processor setConfig(JSONObject config) {
    this.config = config;
    return this;
  }

  @Override
  public JSONObject getConfig() {
    return config;
  }

  @Override
  public Processor setInputBlockingQueue(BlockingQueue<Map<String, Object>> inputBlockingQueue) {
    this.inputBlockingQueue = inputBlockingQueue;
    return this;
  }

  @Override
  public void putToInputBlockingQueue(Map<String, Object> map) {
    try {
      this.inputBlockingQueue.put(map);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Over getOver() {
    JSONObject overConfig = config.getJSONObject("over");
    String strategy = overConfig.getString("strategy");
    return OverFactory.getInstance(strategy, overConfig);
  }
}
