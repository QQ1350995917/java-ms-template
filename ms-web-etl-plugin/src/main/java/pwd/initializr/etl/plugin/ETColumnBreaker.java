package pwd.initializr.etl.plugin;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.util.StringUtils;
import pwd.initializr.etl.ETLDefaultHandler;

/**
 * pwd.initializr.etl.plugin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-01-14 13:07
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class ETColumnBreaker extends ETLDefaultHandler {

  private String breaker = "\\|";
  private int batchSize = 1;

  @Override
  public void handle(Object object) {
    List<String> lines = (List) object;
    List<Map<String, String>> keyValues = new LinkedList<>();
    for (String line : lines) {
      Map<String, String> item = new LinkedHashMap<>();
      String[] split = line.split(breaker);
      item.put("ip", split[0]);
      item.put("apn", split[1]);
      if (split.length > 2 && StringUtils.isEmpty(split[2])) {
        item.put("url", split[2]);
      } else {
        item.put("errorFlag", "true");
      }
      keyValues.add(item);

      if (keyValues.size() == batchSize) {
        this.getNext().handle(keyValues);
        keyValues.clear();
      }
    }
  }

}
