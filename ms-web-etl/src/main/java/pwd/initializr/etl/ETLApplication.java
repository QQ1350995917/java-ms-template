package pwd.initializr.etl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLClassLoader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * pwd.initializr.account@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-13 22:48
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class ETLApplication implements ETLController {

  static final String APPLICATION = "application.json";



  private List<ETLHandler> etlHandlers = new LinkedList<>();

  private final LinkedList<String> inputSet = new LinkedList<>();

  public static void main(String[] args) throws Exception {
    InputStream inputStream;
    if (args == null) {
      inputStream = ETLApplication.class.getClassLoader().getResourceAsStream(APPLICATION);
      new ETLApplication().start(inputStream);
    } else {
      inputStream = new FileInputStream(args[0]);
    }
    new ETLApplication().start(inputStream);
  }

  @Override
  public void start(InputStream application) throws Exception {
    JSONObject jsonObject = JSON.parseObject(application, JSONObject.class, null);
    JSONObject config = jsonObject.getJSONObject("config");
    String plugin = config.getString("plugin");
    URLClassLoader classLoader = new URLClassLoader(ETLUtil.getPlugins(plugin),
        Thread.currentThread().getContextClassLoader());
    JSONArray plugins = jsonObject.getJSONArray("plugins");
    ETLHandler preInstance = null;
    Iterator<Object> iterator = plugins.iterator();
    while (iterator.hasNext()) {
      String next = iterator.next().toString();
      Class<?> classz = classLoader.loadClass(next);
      ETLHandler currentInstance = (ETLHandler) classz.newInstance();
      if (preInstance != null) {
        preInstance.setNext(currentInstance);
      }
      preInstance = currentInstance;
      etlHandlers.add(currentInstance);
    }

    String input = config.getString("input");
    String output = config.getString("output");

    JSONObject thread = config.getJSONObject("thread");
    int core = thread.getIntValue("core");
    int max = thread.getIntValue("max");

  }

  @Override
  public void pause() {

  }

  @Override
  public void stop() {

  }

  @Override
  public void restart() {

  }


}
