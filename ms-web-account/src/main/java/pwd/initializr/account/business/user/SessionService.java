package pwd.initializr.account.business.user;

import pwd.initializr.account.business.common.bo.SessionBO;
import pwd.initializr.account.business.common.bo.SessionCaptchaBO;
import pwd.initializr.account.business.common.bo.SessionCookieBO;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>服务层逻辑：用户登录系统</h1>
 *
 * date 2019-11-04 15:36
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface SessionService {

  /**
   * <h2>生成图形验证码接口：用户登录错误阈值溢出后需要图形验证码</h2>
   * <p>1：生成图形验证码</p>
   * <p>2：把图形验证码的接口绑定到对应的cookie上，存入redis</p>
   * <p>3：把生成的图形验证码进行存储</p>
   * date 2020-07-22 16:11
   *
   * @param cookie 预登录生成的cookie
   * @return pwd.initializr.account.business.user.bo.SessionCaptchaBO
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  SessionCaptchaBO createCaptcha(String cookie);

  /**
   * <h2>cookie生成接口：用户登录前调用</h2>
   * <p>1：根据规则生成cookie</p>
   * <p>2：把cookie存入redis，并设置过期时间，设置该cookie登录次数为0</p>
   * date 2020-07-22 14:33
   *
   * @return pwd.initializr.account.business.user.bo.SessionCookieBO
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  String createCookie();

  /**
   * <h2>session创建接口：用户通过账号密码登录</h2>
   * <p>1：使用既定的加密方式对密码进行加密</p>
   * <p>2：使用登录名和加密的密码进行查找</p>
   * <p>3：找不到对应的账号则抛出异常</p>
   * <p>4：判断对应的账号的可用状态，可用则生成响应的session和个人信息保存在redis，不可用则跳过</p>
   * <p>5：返回账号对象</p>
   * date 2020-07-21 22:14
   *
   * @param token token字符串
   * @param sessionBO sessionBo对象
   * @return pwd.initializr.account.business.user.bo.AdminAccountBO
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void createSession(String token, SessionBO sessionBO);

  /**
   * <h2>cookie删除接口，根据cookie删除redis中的cookie信息</h2>
   * date 2020-07-22 23:14
   *
   * @param cookie 登录前生成的cookie
   * @return java.lang.Boolean
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Integer deleteCookie(String cookie);

  /**
   * <h2>session删除接口，根据用户ID删除session信息，等同于退出登录</h2>
   * <p>1：在redis中删除session信息</p>
   * date 2020-07-22 16:33
   *
   * @param userId 用户ID
   * @return java.lang.Boolean
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Integer deleteSession(Long userId);

  /**
   * <h2>cookie查询接口，用户尝试登录次数</h2>
   * date 2020-07-22 23:42
   *
   * @param cookie cookie
   * @return pwd.initializr.account.business.user.bo.SessionCookieBO
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  SessionCookieBO queryCookie(String cookie);

  /**
   * <h2>session查询接口，根据用户ID查询session信息</h2>
   * <p>1：在redis中查询session信息</p>
   * date 2020-07-22 16:33
   *
   * @param uid 用户用户ID
   * @return pwd.initializr.account.business.user.bo.SessionBO
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  SessionBO querySession(Long uid);

  /**
   * <h2>cookie更新接口，更新cookie对应的登录错误次数</h2>
   * date 2020-07-23 23:08
   *
   * @param sessionCookieBO cookie对象
   * @return pwd.initializr.account.business.user.bo.SessionCookieBO 更新后的cookie对象
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void updateCookie(String cookie, SessionCookieBO sessionCookieBO);

  /**
   * <h2>session更新接口，根据用户ID更新session信息</h2>
   * <p>1：在redis中更新session信息</p>
   * date 2020-07-22 16:33
   *
   * @param sessionBO session对象
   * @return java.lang.Boolean
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  Integer updateSession(SessionBO sessionBO);

}
