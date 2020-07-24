package pwd.initializr.account.api.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.account.api.admin.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-02 09:20
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(value = "loginFailOutput", description = "管理员登录响应参数")
public class LoginFailOutput {

  @ApiModelProperty(name = "type", value = "错误类型", required = true, example = "0")
  @NotNull(message = "0")
  private Integer type;

  @ApiModelProperty(name = "cookie", value = "错误信息", required = true, example = "cookie can not be empty")
  @NotNull(message = "0")
  private String message;

  public LoginFailOutput(FailType failType) {
    this.type = failType.type;
    this.message = failType.message;
  }

  public enum FailType {
    CookieISNull(0,"cookie can not be empty"),
    CookieISExpires(1,"cookie may be expires"),
    CaptchaISNull(2,"captcha can not be empty"),
    CaptchaISError(3,"captcha may be wrong"),
    ParamsISNull(4,"params can not be empty"),
    ParamsISError(5,"params may not be wrong");

    Integer type;
    String message;

    FailType(Integer type, String message) {
      this.type = type;
      this.message = message;
    }

    public Integer getType() {
      return type;
    }

    public String getMessage() {
      return message;
    }
  }
}
