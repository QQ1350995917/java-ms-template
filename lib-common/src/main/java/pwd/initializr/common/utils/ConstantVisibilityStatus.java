package pwd.initializr.common.utils;

/**
 * pwd.initializr.common.utils@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-06-05 21:48
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public enum ConstantVisibilityStatus {
  VISIBILITY(1),
  NOT_VISIBILITY(-1);

  private int value;

  ConstantVisibilityStatus(int value) {
    this.value = value;
  }

  public int value() {
    return value;
  }
}
