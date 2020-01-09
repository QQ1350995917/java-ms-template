package pwd.initializr.etl;


import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

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
public class ETLApplication {

  public static void main(String[] args) throws Exception {
    URL url = new URL(
        "file:E:\\workspace\\github\\ms-web-initializr\\ms-web-etl\\lib\\ms-web-etl-0.0.1-SNAPSHOT.jar");
    URLClassLoader classLoader = new URLClassLoader(new URL[]{url}, Thread.currentThread().getContextClassLoader());

    Class<?> classz = classLoader.loadClass("pwd.initializr.etl.plugins.ETLine");
    Object instance = classz.newInstance();
    Method handler = classz.getMethod("handler");
    Object invoke = handler.invoke(instance, null);
  }
}
