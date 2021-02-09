package pwd.initializr.account.api.vo;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import pwd.initializr.account.business.session.bo.SessionType;

/**
 * pwd.initializr.account.api.admin.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-07-22 22:48
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
public class SessionInitOutput implements Serializable {

  /**
   * session 状态 0：匿名；1：具名
   */
  @ApiModelProperty(name = "type", value = "session类型", required = true, example = "0|1")
  private Integer type = SessionType.ANONYMOUS.getType();
  /**
   * 匿名uid
   */
  @ApiModelProperty(name = "uid", value = "匿名uid", required = true, example = "")
  private Long uid;
  /**
   * 匿名token
   */
  @ApiModelProperty(name = "token", value = "匿名token", required = true, example = "")
  private String token;
  /**
   * session生成的时间戳
   */
  @ApiModelProperty(name = "timestamp", value = "匿名session生成的时间戳", required = true, example = "")
  private Long timestamp;

  /**
   * session过期时间（单位秒）
   */
  @ApiModelProperty(name = "expires", value = "匿名session有效期", required = true, example = "")
  private Integer expires;

  /**
   * 是否需要captcha
   */
  @ApiModelProperty(name = "captchaRequired", value = "是否需要验证码", required = true, example = "")
  private Boolean captchaRequired;



}
