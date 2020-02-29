package pwd.initializr.etl.core.util;

/**
 * pwd.initializr.etl.core.util@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-02-29 12:42
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class FileUtil {

  public static String getIngFilePathByFaker(String fakePath, String suffix) {
    return getFilePathByFaker(fakePath, suffix) + ".ing";
  }

  public static String getFilePathByFaker(String fakePath, String suffix) {
    return fakePath + "." + suffix;
  }

}
