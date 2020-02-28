package pwd.initializr.etl.core.input.processor;

import com.alibaba.fastjson.JSONObject;
import pwd.initializr.etl.core.input.over.Over;

/**
 * pwd.initializr.etl.core.input.processor@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-02-28 12:47
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class ProcessorFactory {

  public static Processor getInstance(String aClass){
    Processor processor = null;
    try {
      processor = (Processor) Class.forName(aClass).newInstance();
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
    return processor;
  }
}
