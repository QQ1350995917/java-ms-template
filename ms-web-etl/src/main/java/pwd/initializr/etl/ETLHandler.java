package pwd.initializr.etl;

/**
 * pwd.initializr.etl@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-01-10 11:41
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface ETLHandler {
  void init();
  void handle(Object object);
  void setNext(ETLHandler handler);
  ETLHandler getNext();
  void update();
  void close();
}
