package pwd.initializr.etl;

/**
 * pwd.initializr.etl@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-01-10 17:27
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public abstract class ETLDefaultHandler implements ETLHandler {
  private ETLHandler next;
  @Override
  public void init() {

  }

  @Override
  public abstract void handle(Object object);

  @Override
  public void setNext(ETLHandler handler) {
    this.next = handler;
  }

  @Override
  public ETLHandler getNext() {
    return this.next;
  }

  @Override
  public void update() {

  }

  @Override
  public void close() {

  }
}
