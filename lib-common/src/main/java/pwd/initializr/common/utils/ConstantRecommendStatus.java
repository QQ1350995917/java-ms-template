package pwd.initializr.common.utils;

/**
 * pwd.initializr.common.utils@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-06-05 21:49
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Deprecated
public enum ConstantRecommendStatus {
  RECOMMEND(1),
  NOT_RECOMMEND(-1);

  private int value;

  ConstantRecommendStatus(int value) {
    this.value = value;
  }

  public int value() {
    return value;
  }

}
