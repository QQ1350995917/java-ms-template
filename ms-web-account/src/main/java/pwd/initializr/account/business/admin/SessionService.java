package pwd.initializr.account.business.admin;

import pwd.initializr.account.business.admin.bo.AdminAccountBO;
import pwd.initializr.account.business.admin.bo.LoginCaptchaBO;
import pwd.initializr.account.business.admin.bo.LoginCookieBO;
import pwd.initializr.account.business.admin.bo.SessionBO;

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
     * <h2>cookie生成接口：管理员登录前调用</h2>
     * <p>1：根据规则生成cookie，存入redis，并设置过期时间</p>
     * date 2020-07-22 14:33
     *
     * @return pwd.initializr.account.business.admin.bo.LoginCookieBO
     * @author DingPengwei[www.dingpengwei@foxmail.com]
     * @since DistributionVersion
     */
    LoginCookieBO produceCookie();

    /**
     * <h2>cookie校验接口：管理员登录验证前调用</h2>
     * <p>1：校验cookie是否过期，未过期，这个对该cookie的有效期进行延时</p>
     * <p>1：校验cookie是否过期，已过期，直接返回</p>
     * date 2020-07-22 14:52
     *
     * @param loginCookieBO cookie信息
     * @return java.lang.Boolean
     * @author DingPengwei[www.dingpengwei@foxmail.com]
     * @since DistributionVersion
     */
    Boolean checkCookie(LoginCookieBO loginCookieBO);

    /**
     * <h2>生成图形验证码接口：管理员登录错误阈值溢出后需要图形验证码</h2>
     * <p>1：生成图形验证码</p>
     * <p>2：把图形验证码的接口绑定到对应的cookie上，存入redis</p>
     * <p>3：把生成的图形验证码进行存储</p>
     * date 2020-07-22 16:11
     *
     * @param loginCookieBO 预登录生成的cookie对象
     * @return pwd.initializr.account.business.admin.bo.LoginCaptchaBO
     * @author DingPengwei[www.dingpengwei@foxmail.com]
     * @since DistributionVersion
     */
    LoginCaptchaBO produceCaptcha(LoginCookieBO loginCookieBO);

    /**
     * <h2>图形验证码校验接口：管理员登录识别的图验证码校验</h2>
     * <p>1：校验cookie对应的验证码识别结果是否正确</p>
     * date 2020-07-22 16:12
     *
     * @param loginCookieBO 预登录生成的cookie对象
     * @param loginCaptchaBO 登录识别后的验证码对象
     * @return java.lang.Boolean
     * @author DingPengwei[www.dingpengwei@foxmail.com]
     * @since DistributionVersion
     */
    Boolean checkCaptcha(LoginCookieBO loginCookieBO, LoginCaptchaBO loginCaptchaBO);

    /**
     * <h2>session创建接口：管理员通过账号密码登录</h2>
     * <p>1：使用既定的加密方式对密码进行加密</p>
     * <p>2：使用登录名和加密的密码进行查找</p>
     * <p>3：找不到对应的账号则抛出异常</p>
     * <p>4：判断对应的账号的可用状态，可用则生成响应的session和个人信息保存在redis，不可用则跳过</p>
     * <p>5：返回账号对象</p>
     * date 2020-07-21 22:14
     *
     * @param loginName 登录名
     * @param loginPwd 登录密码
     * @return pwd.initializr.account.business.admin.bo.AdminAccountBO
     * @author DingPengwei[www.dingpengwei@foxmail.com]
     * @since DistributionVersion
     */
    AdminAccountBO createSessionByNameAndPwd(String loginName, String loginPwd);

    /**
     * <h2>TODO session创建接口：管理员通过手机号和短信验证码登录</h2>
     * date 2020-07-22 16:28
     *
     * @param phoneNumber 手机号码
     * @param smsCode 短信验证码
     * @return pwd.initializr.account.business.admin.bo.AdminAccountBO
     * @author DingPengwei[www.dingpengwei@foxmail.com]
     * @since DistributionVersion
     */
    AdminAccountBO createSessionByPhoneNumberAndSmsCode(String phoneNumber, String smsCode);

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
    Boolean deleteSession(Long adminUserId);

    /**
     * <h2>session查询接口，根据用户ID查询session信息</h2>
     * <p>1：在redis中查询session信息</p>
     * date 2020-07-22 16:33
     *
     * @param adminUserId 管理员用户ID
     * @return pwd.initializr.account.business.admin.bo.SessionBO
     * @author DingPengwei[www.dingpengwei@foxmail.com]
     * @since DistributionVersion
     */
    SessionBO querySession(Long adminUserId);

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
    Boolean updateSession(SessionBO sessionBO);
}
