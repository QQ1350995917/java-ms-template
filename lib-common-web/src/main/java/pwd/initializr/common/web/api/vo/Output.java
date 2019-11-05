package pwd.initializr.common.web.api.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import pwd.initializr.common.web.exception.BaseException;

/**
 * pwd.initializr.common.web.api@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-14 15:23
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Output<T> {

  private T data;

  private Meta meta;

  public Output() {
    super();
  }

  public Output(T data) {
    this.meta = Meta.successMeta();
    this.data = data;
  }

  public Output(Meta meta, T data) {
    this.meta = meta;
    this.data = data;
  }

  public static Output output(Object data) {
    return new Output(Meta.successMeta(), data);
  }

  public static Output error(int code, String errorMessage, Object data) {
    throw new BaseException(code, errorMessage);
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public Meta getMeta() {
    return meta;
  }

  public void setMeta(Meta meta) {
    this.meta = meta;
  }
}
