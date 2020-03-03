package pwd.initializr.etl.core;

import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import pwd.initializr.etl.core.handle.HandlerDriver;
import pwd.initializr.etl.core.input.InputDriver;
import pwd.initializr.etl.core.output.OutputDriver;
import pwd.initializr.etl.core.util.ConfigUtil;
import pwd.initializr.etl.core.util.PluginUtil;

/**
 * pwd.initializr.etl.core@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-02-26 16:23
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class ETLDriver {

  private BlockingQueue<Map<String, Object>> inputBlockingQueue;
  private BlockingQueue<Map<String, Object>> outBlockingQueue;

  public static void main(String[] args) {
    if (args == null || args.length < 1) {
      throw new RuntimeException("without config param");
    }

    String configJsonPath = null;
    String pluginDir = null;
    for (String arg : args) {
      String[] split = arg.split("=");
      if ("-config".equals(split[0])) {
        configJsonPath = split[1];
      }
      if ("-plugin".equals(split[0])) {
        pluginDir = split[1];
      }
    }
    new ETLDriver().start(configJsonPath, pluginDir);
  }

  public ETLDriver start(String configJsonPath, String pluginDir) {
    if (configJsonPath == null) {
      throw new RuntimeException("without config param");
    }

    loadPlugin(pluginDir);

    JSONObject config = ConfigUtil.loadConfig(configJsonPath);

    JSONObject inputConfig = config.getJSONObject("input");
    InputDriver inputDriver = new InputDriver().setConfig(inputConfig).start();
    this.inputBlockingQueue = inputDriver.getInputBlockingQueue();

    JSONObject handleConfig = config.getJSONObject("handle");
    HandlerDriver handlerDriver = new HandlerDriver().setConfig(handleConfig)
        .setInputBlockingQueue(inputBlockingQueue).start();
    this.outBlockingQueue = handlerDriver.getOutBlockingQueue();

    JSONObject outputConfig = config.getJSONObject("output");
    new OutputDriver().setConfig(outputConfig).setOutBlockingQueue(this.outBlockingQueue).start();

    return this;
  }

  private void loadPlugin(String pluginDir) {
    try {
      if (pluginDir != null) {
        File file = new File(pluginDir);
        if (file.exists() && file.isDirectory()) {
          URLClassLoader urlLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
          Class urlClassLoaderClass = URLClassLoader.class;
          Method method = urlClassLoaderClass.getDeclaredMethod("addURL", new Class[]{URL.class});
          method.setAccessible(true);
          URL[] pluginUrls = PluginUtil.getPlugins(pluginDir);
          if (pluginUrls != null) {
            for (URL pluginUrl : pluginUrls) {
              method.invoke(urlLoader, pluginUrl);
            }
          }
        }
      }
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }

  public BlockingQueue<Map<String, Object>> getInputBlockingQueue() {
    return inputBlockingQueue;
  }

  public BlockingQueue<Map<String, Object>> getOutBlockingQueue() {
    return outBlockingQueue;
  }
}
