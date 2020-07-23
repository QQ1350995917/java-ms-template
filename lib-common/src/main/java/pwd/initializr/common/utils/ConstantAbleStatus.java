package pwd.initializr.common.utils;

/**
 * pwd.initializr.common.utils@ms-web-initializr
 *
 * <h1>启用/禁用</h1>
 *
 * date 2020-05-25 15:57
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Deprecated
public enum ConstantAbleStatus {
    DISABLE(-1),
    ENABLE(1);

    private int value;

    ConstantAbleStatus(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

}
