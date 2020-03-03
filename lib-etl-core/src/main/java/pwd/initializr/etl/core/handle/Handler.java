package pwd.initializr.etl.core.handle;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;

/**
 * pwd.initializr.etl.core.handle@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-03 09:38
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface Handler {

  Map<String, Object> handle(Map<String, Object> map);

  void init(JSONObject config);

  void update(JSONObject config);

}
