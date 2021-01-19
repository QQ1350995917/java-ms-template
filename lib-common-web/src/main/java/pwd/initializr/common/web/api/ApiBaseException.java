package pwd.initializr.common.web.api;

/**
 * pwd.initializr.common.web.exception@ms-web-initializr
 *
 * <h1>通用异常结构声明</h1>
 *
 * date 2019-09-14 15:15
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class ApiBaseException extends RuntimeException {
    private Integer statusCode;
    private String errorMessage;

    public ApiBaseException(String errorMessage) {
        super(errorMessage, null, false, false);
        this.errorMessage = errorMessage;
    }

    public ApiBaseException(Integer statusCode, String errorMessage) {
        super(errorMessage, null, false, false);
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public ApiBaseException(Integer statusCode, String errorMessage, Throwable e) {
        super(errorMessage, e, false, false);
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }


    public Integer getStatusCode() {
        return this.statusCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

}
