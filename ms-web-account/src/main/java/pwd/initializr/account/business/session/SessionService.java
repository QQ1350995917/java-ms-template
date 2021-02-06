package pwd.initializr.account.business.session;

import pwd.initializr.account.business.session.bo.SessionBOAnonymous;
import pwd.initializr.account.business.session.bo.CaptchaBO;
import pwd.initializr.account.business.session.bo.SessionBONamed;

/**
 * pwd.initializr.account.business.admin@ms-web-initializr
 *
 * <h1>服务层逻辑：管理员登录系统</h1>
 *
 * date 2020-05-29 22:17
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface SessionService {

  /**
   * <h2>生成图形验证码接口：管理员登录错误阈值溢出后需要图形验证码</h2>
   * <p>1：生成图形验证码</p>
   * <p>2：把图形验证码的接口绑定到对应的 token 上，存入redis</p>
   * <p>3：把生成的图形验证码进行存储</p>
   * date 2020-07-22 16:11
   *
   * @param token 预登录生成的 token
   * @return pwd.initializr.account.business.session.bo.CaptchaBO
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  CaptchaBO createCaptcha(String token);


  SessionBONamed createSession(String token,Long uid);

  /**
   * <h2>匿名 token 生成接口：管理员登录前调用</h2>
   * <p>1：根据规则生成匿名 token </p>
   * <p>2：把 token 存入redis，并设置过期时间，设置该 token 登录次数为0</p>
   * date 2020-07-22 14:33
   *
   * @return pwd.initializr.account.business.session.bo.SessionBOAnonymous
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  SessionBOAnonymous createAnonymousSession();

  /**
   * <h2>session创建接口：管理员通过账号密码登录</h2>
   * <p>1：使用既定的加密方式对密码进行加密</p>
   * <p>2：使用登录名和加密的密码进行查找</p>
   * <p>3：找不到对应的账号则抛出异常</p>
   * <p>4：判断对应的账号的可用状态，可用则生成响应的session和个人信息保存在redis，不可用则跳过</p>
   * <p>5：返回账号对象</p>
   * date 2020-07-21 22:14
   *
   * @param sessionBONamed sessionBo对象
   * @return pwd.initializr.account.business.admin.bo.AdminAccountBO
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void createNamedSession(SessionBONamed sessionBONamed);

  /**
   * <h2>token 删除接口，根据 token 删除redis中的 token 信息</h2>
   * date 2020-07-22 23:14
   *
   * @param uid 登录前生成的 token
   * @return java.lang.Boolean
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Boolean deleteAnonymousToken(Long uid);

  /**
   * <h2>session删除接口，根据用户ID删除session信息，等同于退出登录</h2>
   * <p>1：在redis中删除session信息</p>
   * date 2020-07-22 16:33
   *
   * @param adminUserId 管理员用户ID
   * @return java.lang.Boolean
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Boolean deleteNamedSession(Long adminUserId);

  /**
   * <h2> token 查询接口，管理员尝试登录次数</h2>
   * date 2020-07-22 23:42
   *
   * @param uid
   * @return pwd.initializr.account.business.session.bo.SessionBOAnonymous
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  SessionBOAnonymous querySessionAnonymous(Long uid);

  /**
   * <h2>session查询接口，根据用户ID查询session信息</h2>
   * <p>1：在redis中查询session信息</p>
   * date 2020-07-22 16:33
   *
   * @param uid 管理员用户ID
   * @return pwd.initializr.account.business.session.bo.SessionBONamed
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  SessionBONamed querySessionNamed(Long uid);

  /**
   * <h2>token 更新接口，更新 token 对应的登录错误次数</h2>
   * date 2020-07-23 23:08
   *
   * @param sessionBOAnonymous token 对象
   * @return pwd.initializr.account.business.session.bo.SessionBOAnonymous 更新后的 token 对象
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void updateAnonymousSession(SessionBOAnonymous sessionBOAnonymous);

  /**
   * <h2>session更新接口，根据用户ID更新session信息</h2>
   * <p>1：在redis中更新session信息</p>
   * date 2020-07-22 16:33
   *
   * @param sessionBONamed session对象
   * @return java.lang.Boolean
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Boolean updateNamedSession(SessionBONamed sessionBONamed);


}
