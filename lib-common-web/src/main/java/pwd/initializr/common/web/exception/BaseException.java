package pwd.initializr.common.web.exception;

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
public class BaseException extends RuntimeException {
    private Integer statusCode;
    private String errorMessage;
    private MessageKey msgKey;

    public BaseException(String errorMessage) {
        super(errorMessage, (Throwable) null, false, false);
        this.errorMessage = errorMessage;
    }

    public BaseException(Integer statusCode, String errorMessage) {
        super(errorMessage, (Throwable) null, false, false);
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public BaseException(Integer statusCode, String errorMessage, Throwable e) {
        super(errorMessage, e, false, false);
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public BaseException(MessageKey msgKey, String errorMessage) {
        super(errorMessage, (Throwable) null, false, false);
        this.msgKey = msgKey;
        this.errorMessage = errorMessage;
    }

    public BaseException(MessageKey msgKey) {
        super((String) null, (Throwable) null, false, false);
        this.msgKey = msgKey;
    }

    public Integer getStatusCode() {
        return this.statusCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public MessageKey getMessageKey() {
        return this.msgKey;
    }
}
