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
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
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
public class ETLApplication implements ApplicationRunner, ETLController {

  static final String APPLICATION = "application.json";
  public static String inputDir;
  public static String outputDir;

  private List<ETLHandler> etlHandlers = new LinkedList<>();

  public static void main(String[] args) {
    SpringApplication.run(ETLApplication.class, args);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    String[] sourceArgs = args.getSourceArgs();
    String application = APPLICATION;
    if (sourceArgs != null && sourceArgs.length != 0) {
      application = sourceArgs[0];
    }

    if (APPLICATION.equals(application)) {
      try (InputStream inputStream = this.getClass().getClassLoader()
          .getResourceAsStream(application)) {
        start(inputStream);
      }
    } else {
      try (InputStream inputStream = new FileInputStream(application)) {
        start(inputStream);
      }
    }

  }

  @Override
  public void start(InputStream application) throws Exception {
    JSONObject jsonObject = JSON.parseObject(application, JSONObject.class, null);
    JSONObject basic = jsonObject.getJSONObject("basic");
    String pluginDir = basic.getString("pluginDir");
    URLClassLoader classLoader = new URLClassLoader(ETLUtil.getPlugins(pluginDir),
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

    inputDir = basic.getString("inputDir");
    outputDir = basic.getString("outputDir");

    JSONObject worker = basic.getJSONObject("worker");
    int core = worker.getIntValue("core");
    int max = worker.getIntValue("max");
    if (etlHandlers.size() == 0) {
      new ETLDriver(core, max, inputDir, outputDir, null);
    } else {
      new ETLDriver(core, max, inputDir, outputDir, etlHandlers.get(0));
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
