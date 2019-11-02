package pwd.initializr.common.web.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * pwd.initializr.common.web.api@ms-web-initializr
 *
 * <P>请求响应元数据</P>
 * <P>业务上应在识别网络成功后，使用该元数据进行业务处理。</P>
 * <P>该类的实例化一般情况下在{@link pwd.initializr.common.web.api.ApiController}类中，于响应数据的接口中进行了二次封装，ApiController子类中无需实例化该类</P>
 *
 * date 2019-09-14 15:19
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@ApiModel
public class Meta {

  @NotNull(message = "400")
  @ApiModelProperty(value = "响应code", required = true)
  private int code = 200;

  @NotBlank(message = "400")
  @ApiModelProperty(value = "提示信息", required = true)
  private String message = "ok";


  public Meta() {
    super();
  }

  public Meta(int code) {
    this.code = code;
  }

  public Meta(int code, String message) {
    this.code = code;
    this.message = message;
  }

  /**
   * <P>提供便捷的生成响应成功的元数据</P>
   * <P>不提供对应的errorMeta的元数据</P>
   * <P>正常的响应总是相同的，异常的响应各有各的不同</P>
   *
   * @return Meta
   * @author 丁朋伟@600100@18511694468 on 2018-12-21 10:57
   */
  public static Meta successMeta() {
    return new Meta();
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
