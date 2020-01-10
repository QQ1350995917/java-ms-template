package pwd.initializr.etl;

import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * pwd.initializr.etl@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-01-10 14:51
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class ETLUtil {

  public static URL[] getPlugins(String plugin) throws Exception {
    File[] files = new File(plugin).listFiles();
    List<URL> urls = new LinkedList<>();
    for (File file : files) {
      String absolutePath = file.getAbsolutePath();
      if (absolutePath.endsWith(".jar")) {
        URL url = new URL("file:" + file.getAbsolutePath());
        urls.add(url);
      }
    }
    return urls.toArray(new URL[]{});
  }
}
