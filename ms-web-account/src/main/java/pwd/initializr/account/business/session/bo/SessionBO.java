package pwd.initializr.account.business.session.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pwd.initializr.account.rpc.RPCSession;

/**
 * pwd.initializr.account.business.admin.bo@ms-web-initializr
 *
 * <h1>服务层逻辑对象封装：管理员会话信息</h1>
 *
 * date 2020-05-30 14:24
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString()
public class SessionBO extends RPCSession {

  /**
   * session类型
   * 0:匿名session
   * 1:具名session
   */
  private Integer type;
  /**
   * 匿名session变成具名session过程中已经尝试登录次数
   */
  private Integer times;
  /**
   * 期望验证码值
   */
  private String captcha;

}
