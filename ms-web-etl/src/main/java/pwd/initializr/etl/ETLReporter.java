package pwd.initializr.etl;

import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * pwd.initializr.etl@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-02-04 21:40
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class ETLReporter {

  private static final HashMap<String, Long> counter = new LinkedHashMap<>();
  private static final HashMap<String, Integer> current = new LinkedHashMap<>();
  private static final HashMap<String, Long> total = new LinkedHashMap<>();

  public static void onHandleStart(String className, String methodName){
    String key = className.replace("pwd.initializr.etl.plugin.ET","");
    if (!total.containsKey(key)) {
      total.put(key, 0L);
    }
  }

  public static void onHandleEnd(String className, String methodName, long duration) {
    String key = className.replace("pwd.initializr.etl.plugin.ET","");
    if (counter.containsKey(key)) {
      counter.put(key, counter.get(key) + 1);
    } else {
      counter.put(key, 1L);
    }
    if (total.containsKey(key)) {
      Long preTotal = total.get(key);
      Long newTotal = preTotal + duration;
      total.put(key, newTotal);
    } else {
      total.put(key, duration);
    }
    current.put(key, (int) duration);

//    System.out.println(JSON.toJSON(counter));
    System.out.println(JSON.toJSON(total));
//    System.out.println(JSON.toJSON(current));
    System.out.println("==================");
  }

  public static HashMap<String, Long> getReport(){
    return total;
  }

}
