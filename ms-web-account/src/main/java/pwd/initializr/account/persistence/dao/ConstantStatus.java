package pwd.initializr.account.persistence.dao;

/**
 * pwd.initializr.account.persistence.dao@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-21 12:01
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public enum ConstantStatus {
  ENABLE(0),
  DISABLE(1),
  DELETE(2);

  private int value;

  ConstantStatus(int value) {
    this.value = value;
  }

  public int value(){
    return value;
  }
}
