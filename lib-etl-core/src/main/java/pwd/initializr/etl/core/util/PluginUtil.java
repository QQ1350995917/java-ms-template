package pwd.initializr.etl.core.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * pwd.initializr.etl.core.util@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-03 13:22
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class PluginUtil {

  public static URL[] getPlugins(String plugin) {
    List<URL> urls;
    try {
      File[] files = new File(plugin).listFiles();
      urls = new LinkedList<>();
      for (File file : files) {
        String absolutePath = file.getAbsolutePath();
        if (absolutePath.endsWith(".jar")) {
          URL url = new URL("file:" + file.getAbsolutePath());
          urls.add(url);
        }
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
    if (urls != null && urls.size() > 0) {
      return urls.toArray(new URL[]{});
    } else {
      return null;
    }
  }
}
