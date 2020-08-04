package pwd.initializr.account.business.common.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.account.business.admin.bo@ms-web-initializr
 *
 * <h1>服务层逻辑对象封装：图形验证码</h1>
 *
 * date 2020-07-22 15:06
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SessionCaptchaBO {

  /**
   * 图形验证码的base64编码
   */
  private String base64;

  /**
   * 用户填写的结果
   */
  private String result;

  /**
   * 过期时间（单位秒）
   */
  private Integer expires;

}
