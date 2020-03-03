package pwd.initializr.etl.core.input.processor;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;

/**
 * pwd.initializr.etl.core.input.processor@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-02-29 23:04
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class KAFKAProcessor extends DefaultMQProcessor {

  @Override
  public void process(Object object) {
    Map<String,Object> map = (Map)object;
    putToInputBlockingQueue(map);
  }

}
