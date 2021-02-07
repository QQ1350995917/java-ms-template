package pwd.initializr.account.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
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
@ApiModel(value = "loginFailOutput", description = "登录失败响应参数")
public class SessionInitFailOutput implements Serializable {

  /**
   * 下次请求是否需要携带验证码
   */
  @ApiModelProperty(name = "captchaRequired", value = "下次请求是否需要携带验证码", required = false, example = "false")
  @NotNull(message = "0")
  private Boolean captchaRequired;
  /**
   * 错误信息
   */
  @ApiModelProperty(name = "message", value = "错误信息", required = true, example = "token can not be empty")
  @NotNull(message = "0")
  private String message;

  public SessionInitFailOutput(Boolean captchaRequired,String message) {
    this.captchaRequired = captchaRequired;
    this.message = message;
  }

}
