package pwd.initializr.common.web.api;

/**
 * pwd.initializr.common.web.exception@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-14 15:15
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public enum MessageKey {
    UNKNOW(820, "未知错误");

    private int code;
    private String msg;

    private MessageKey(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return Integer.valueOf(this.code);
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public static MessageKey getMessageKey(int code) {
        MessageKey[] arr = values();
        int len = arr.length;

        for(int i = 0; i < len; ++i) {
            MessageKey v = arr[i];
            if(code == v.getCode().intValue()) {
                return v;
            }
        }

        return UNKNOW;
    }
}
