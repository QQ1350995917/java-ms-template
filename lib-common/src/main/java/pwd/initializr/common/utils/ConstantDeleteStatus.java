package pwd.initializr.common.utils;

/**
 * pwd.initializr.common.utils@ms-web-initializr
 *
 * <h1>删除/未删除</h1>
 *
 * date 2020-05-25 15:57
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Deprecated
public enum ConstantDeleteStatus {
    /**
     * 已删除：标记为1
     */
    DELETED(1),
    /**
     * 未删除：标记为-1
     */
    EXISTING(-1);

    private int value;

    ConstantDeleteStatus(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

}
