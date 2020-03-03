package pwd.initializr.etl.core.handle;

/**
 * pwd.initializr.etl.core.handle@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-03 15:15
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class HandlerFactory {

  public static Handler getInstance(String aClass) {
    Handler handler = null;
    try {
      handler = (Handler) Class.forName(aClass).newInstance();
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
    return handler;
  }

}
