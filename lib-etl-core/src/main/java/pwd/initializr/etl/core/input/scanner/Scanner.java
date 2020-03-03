package pwd.initializr.etl.core.input.scanner;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import pwd.initializr.etl.core.input.processor.Processor;

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
public interface Scanner extends Runnable{

  BlockingQueue<Map<String, Object>> getInputBlockingQueue();

  Scanner setInputBlockingQueue(BlockingQueue<Map<String, Object>> blockingQueue);

  Processor getProcessor();

  Scanner setConfig(JSONObject config);

  JSONObject getConfig();

  Boolean isEnable();

}
