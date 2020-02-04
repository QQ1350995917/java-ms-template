package pwd.initializr.etl;

import com.alibaba.fastjson.JSON;
import io.swagger.models.auth.In;
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
public class ETLHandlerReporter {
  private static final HashMap<String,Integer> counter = new LinkedHashMap<>();
  private static final HashMap<String,Integer> average = new LinkedHashMap<>();
  private static final HashMap<String,Integer> current = new LinkedHashMap<>();

  public static void report(){
    report("testClassName");
  }

  public static void report(String className){
    report(className,"testMethodName");
  }

  public static void report(String className,String methodName){
    report(className,methodName,-1L);
  }

  public static void report(String className,String methodName,long duration){
    String key = className.replace("pwd.initializr.etl.plugin.","") + "." + methodName;
    if (counter.containsKey(key)) {
      counter.put(key,counter.get(key) + 1);
    } else {
      counter.put(key, 1);
    }
    if (average.containsKey(key)) {
      Integer currentAverage = average.get(key);
      Integer currentCounter = counter.get(key);
      Integer newAverage = (int)(currentAverage * (currentCounter - 1) + duration) / currentCounter;
      average.put(key,newAverage);
    } else {
      average.put(key,(int)duration);
    }
    current.put(key,(int)duration);

    System.out.println(JSON.toJSON(counter));
    System.out.println(JSON.toJSON(average));
    System.out.println(JSON.toJSON(current));
    System.out.println("==================");
  }

}
