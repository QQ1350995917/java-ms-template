package pwd.initializr.account.api.admin.vo;

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
public class SessionTokenOutput {

  /**
   * cookie
   */
  private String cookie;
  /**
   * 过期时间（单位秒）
   */
  private Integer expires;

  /**
   * 过期时间（单位秒）
   */
  private Boolean captchaRequired;
}
