package pwd.initializr.etl.core.plugin.handle;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import pwd.initializr.etl.core.handle.Handler;

/**
 * pwd.initializr.etl.core.plugin.handle@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-03 13:07
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class LineSplitHandler implements Handler {

  private JSONObject config;
  private String delimiter;
  private String[] fields;

  @Override
  public Map<String, Object> handle(Map<String, Object> map) {
    String line = map.get("_value").toString();
    String[] split = line.split(delimiter);
    for (int i = 0; i < split.length; i++) {
      map.put(fields[i],split[i]);
    }
    return map;
  }

  @Override
  public void init(JSONObject config) {
    this.config = config;
    this.delimiter = config.getString("delimiter");
    this.fields = config.getObject("fields", String[].class);
  }

  @Override
  public void update(JSONObject config) {

  }
}
