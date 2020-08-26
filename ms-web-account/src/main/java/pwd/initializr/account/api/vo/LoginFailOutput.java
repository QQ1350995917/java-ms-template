package pwd.initializr.account.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

/**
 * pwd.initializr.account.api.admin.vo@ms-web-initializr
 *
 * <h1>VO数据模型：管理员登录失败响应参数</h1>
 *
 * date 2019-11-02 09:20
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Getter
@ToString
@ApiModel(value = "loginFailOutput", description = "管理员登录失败响应参数")
public class LoginFailOutput {

  /**
   * 下次登录是否需要携带验证码
   */
  @ApiModelProperty(name = "captchaRequired", value = "下次登录是否需要携带验证码", required = false, example = "false")
  @NotNull(message = "0")
  private Boolean captchaRequired;
  /**
   * 错误类型
   */
  @ApiModelProperty(name = "type", value = "错误类型", required = true, example = "0")
  @NotNull(message = "0")
  private Integer type;
  /**
   * 错误信息
   */
  @ApiModelProperty(name = "message", value = "错误信息", required = true, example = "token can not be empty")
  @NotNull(message = "0")
  private String message;

  public LoginFailOutput(Boolean captchaRequired,FailType failType) {
    this(failType);
    this.captchaRequired = captchaRequired;
  }

  public LoginFailOutput(FailType failType) {
    this.type = failType.type;
    this.message = failType.message;
  }

  public enum FailType {
    /**
     *
     */
    TokenISNull(0, "token can not be empty"),
    /**
     *
     */
    TokenISExpires(1, "token may be expires"),
    /**
     *
     */
    CaptchaISNull(2, "captcha can not be empty"),
    /**
     *
     */
    CaptchaISError(3, "captcha may be wrong"),
    /**
     *
     */
    ParamsISNull(4, "params can not be empty"),
    /**
     *
     */
    ParamsISError(5, "params may not be wrong");

    /**
     *
     */
    @ApiModelProperty(name = "type", value = "错误类型", required = true, example = "0")
    @NotNull(message = "0")
    private Integer type;

    /**
     *
     */
    @ApiModelProperty(name = "message", value = "错误信息", required = true, example = "token can not be empty")
    @NotNull(message = "0")
    private String message;

    FailType(Integer type, String message) {
      this.type = type;
      this.message = message;
    }
  }

}
