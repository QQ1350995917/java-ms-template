package pwd.initializr.account.business.session.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.account.business.admin.bo@ms-web-initializr
 *
 * <h1>服务层逻辑对象封装：管理员会话前cookie信息</h1>
 *
 * date 2020-07-22 14:46
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
public class SessionBOAnonymous implements SessionBO{

  /**
   * 已经尝试登录次数
   */
  private Integer times;
  /**
   * 期望验证码值
   */
  private String captcha;
}
