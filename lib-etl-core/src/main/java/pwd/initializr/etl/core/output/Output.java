package pwd.initializr.etl.core.output;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

/**
 * pwd.initializr.etl.core.output@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-03 16:36
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface Output extends Runnable {

  BlockingQueue<Map<String, Object>> getOutBlockingQueue();

  Output setOutBlockingQueue(BlockingQueue<Map<String, Object>> outBlockingQueue);

  Output setConfig(JSONObject config);

  JSONObject getConfig();

  Boolean isEnable();

  Integer getThreadNum();
}
