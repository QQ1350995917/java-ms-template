package pwd.initializr.etl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.InputStream;
import java.net.URLClassLoader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

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
@SpringBootApplication
@EnableScheduling
public class ETLApplication implements ETLController {

  static final String APPLICATION = "application.json";

  private List<ETLHandler> etlHandlers = new LinkedList<>();

  public static void main(String[] args) throws Exception {
    SpringApplication.run(ETLApplication.class, args);
    String application = APPLICATION;
    if (args != null && args.length != 0) {
      application = args[0];
    }
    try (InputStream inputStream = ETLApplication.class.getClassLoader()
        .getResourceAsStream(application)) {
      new ETLApplication().start(inputStream);
    }
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
      currentInstance.init();
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
    if (etlHandlers.size() == 0) {
      new ETLDriver(core, max, input, output, null);
    } else {
      new ETLDriver(core, max, input, output, etlHandlers.get(0));
    }

  }

  @Override
  public void pause() {

  }

  @Override
  public void stop() {
    for (ETLHandler etlHandler : etlHandlers) {
      etlHandler.close();
    }
  }

  @Override
  public void restart() {

  }


}
