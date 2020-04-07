package pwd.initializr.etl.core.input.scanner;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import pwd.initializr.etl.core.input.processor.Processor;
import pwd.initializr.etl.core.input.processor.ProcessorFactory;

/**
 * pwd.initializr.etl.core.input.scanner@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-01 17:06
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public abstract class DefaultScanner implements Scanner {

  private JSONObject config;
  private BlockingQueue<Map<String, Object>> inputBlockingQueue;

  @Override
  public BlockingQueue<Map<String, Object>> getInputBlockingQueue() {
    return this.inputBlockingQueue;
  }

  @Override
  public Scanner setInputBlockingQueue(BlockingQueue<Map<String, Object>> blockingQueue) {
    this.inputBlockingQueue = blockingQueue;
    return this;
  }

  @Override
  public Processor getProcessor() {
    JSONObject processorConfig = config.getJSONObject("processor");
    String aClass = processorConfig.getString("class");
    Processor processor = ProcessorFactory.getInstance(aClass);
    processor.setConfig(processorConfig);
    processor.setInputBlockingQueue(this.getInputBlockingQueue());
    return processor;
  }

  @Override
  public Scanner setConfig(JSONObject config) {
    this.config = config;
    return this;
  }

  @Override
  public JSONObject getConfig() {
    return config;
  }

  @Override
  public Boolean isEnable() {
    return this.config.getBoolean("enable");
  }
}