package pwd.initializr.etl.core.output;

/**
 * pwd.initializr.etl.core.output@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-03 17:10
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class OutputFactory {

  public static Output getInstance(String type) {
    Output output;
    try {
      String aClass = "pwd.initializr.etl.core.output." + type.toUpperCase() + "Output";
      output = (Output) Class.forName(aClass).newInstance();
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
    return output;
  }
}
