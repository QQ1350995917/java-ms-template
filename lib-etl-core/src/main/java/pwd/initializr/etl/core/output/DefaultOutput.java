package pwd.initializr.etl.core.output;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

/**
 * pwd.initializr.etl.core.output@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-03 16:39
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public abstract class DefaultOutput implements Output {

  private JSONObject config;
  private BlockingQueue<Map<String, Object>> outBlockingQueue;

  @Override
  public BlockingQueue<Map<String, Object>> getOutBlockingQueue() {
    return this.outBlockingQueue;
  }

  @Override
  public Output setOutBlockingQueue(BlockingQueue<Map<String, Object>> outBlockingQueue) {
    this.outBlockingQueue = outBlockingQueue;
    return this;
  }

  @Override
  public Output setConfig(JSONObject config) {
    this.config = config;
    return this;
  }

  @Override
  public JSONObject getConfig() {
    return this.config;
  }

  @Override
  public Boolean isEnable() {
    return this.config.getBoolean("enable");
  }

  @Override
  public Integer getThreadNum() {
    return this.config.getInteger("threadNum");
  }
}
