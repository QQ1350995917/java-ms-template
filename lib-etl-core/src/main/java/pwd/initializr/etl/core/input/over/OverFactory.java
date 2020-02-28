package pwd.initializr.etl.core.input.over;

import com.alibaba.fastjson.JSONObject;

/**
 * pwd.initializr.etl.core.input.over@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-02-28 10:59
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class OverFactory {

  public static Over getInstance(String strategy, JSONObject config) {
    Over over = null;
    try {
      over = (Over) Class.forName("pwd.initializr.etl.core.input.over." + strategy)
          .newInstance();
      over.setConfig(config);
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
    return over;
  }

}
