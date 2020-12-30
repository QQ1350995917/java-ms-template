package pwd.initializr.gateway.business.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;

/**
 * pwd.initializr.common.web.api@ms-web-initializr
 *
 * <h1>API统一输出结构声明</h1>
 *
 * date 2019-09-14 15:23
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Output<T> implements Serializable {

  private T data;

  private Meta meta;

  public Output() {
    super();
  }

  public Output(T data) {
    this.meta = Meta.successMeta();
    this.data = data;
  }

  public Output(Meta meta) {
    this.meta = meta;
  }

  public Output(Meta meta, T data) {
    this.meta = meta;
    this.data = data;
  }

  public static Output output(Object data) {
    return new Output(Meta.successMeta(), data);
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
