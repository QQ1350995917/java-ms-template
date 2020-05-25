package pwd.initializr.common.utils;

/**
 * pwd.initializr.common.utils@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-25 15:57
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public enum ConstantDeleteStatus {
    DELETED(-1),
    EXISTING(1);

    private int value;

    ConstantDeleteStatus(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

}
