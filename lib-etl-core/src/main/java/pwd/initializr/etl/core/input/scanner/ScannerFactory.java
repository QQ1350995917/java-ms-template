package pwd.initializr.etl.core.input.scanner;


/**
 * pwd.initializr.etl.core.input.scanner@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-01 20:29
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class ScannerFactory {

  public static Scanner getInstance(String type) {
    Scanner scanner = null;
    try {
      String aClass = "pwd.initializr.etl.core.input.scanner." + type.toUpperCase() + "Scanner";
      scanner = (Scanner) Class.forName(aClass).newInstance();
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
    return scanner;
  }
}
