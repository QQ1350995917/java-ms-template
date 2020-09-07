package pwd.initializr.account.api.user;

import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import pwd.initializr.account.api.user.vo.LoginInput;

/**
 * pwd.initializr.account.api.user@ms-web-initializr
 *
 * <h1>控制层接口：session信息</h1>
 *
 * date 2019-11-02 21:59
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface SessionApi {

  /**
   * <h2>用户账号登录接口</h2>
   * <p>1：校验token是否存在，响应异常信息</p>
   * <p>2：根据用户名密码查询对象，出现异常或者查询失败则响应异常信息，同时根据token判断登录错误阈值，决定是否需要图形验证码以及生成验证码</p>
   * <p>3：根据用户名密码查询对象，对象查询成功，在redis中销毁token，以及token对应的验证码信息</p>
   * <p>4：根据查询对象，判断可用状态，不可用则响应异常信息</p>
   * <p>5：可用状态对象，响应是否需要修改密码</p>
   * <p>6：可用状态对象，响应是否异地登录</p>
   * <p>7：可用状态对象，响应token，过期时间等信息</p>
   * date 2020-07-21 23:21
   *
   * @param input 登录信息
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  @ApiOperation(value = "登录")
  @PutMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void loginByNameAndPwd(@RequestBody @Valid @NotNull(message = "参数不能为空") LoginInput input);

  /**
   * <h2>用户登录验证码刷新</h2>
   * <p>1：校验token是否存在，响应异常信息</p>
   * <p>2：响应新的验证码</p>
   * date 2020-07-22 15:59
   *
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  @ApiOperation(value = "获取验证码")
  @GetMapping(value = {"/captcha"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void loginCaptchaRefresh();

  /**
   * <h2>登录页面中的信息</h2>
   * <p1>1：响应登录前token信息，执行刷新验证码或者执行登录携带token信息</p1>
   * <p1>2：响应可选择的登录方式列表</p1>
   * <p1>3：响应该token的有效时间</p1>
   * date 2020-07-22 11:52
   *
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  @ApiOperation(value = "登录页面初始化")
  @GetMapping(value = {"/init"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void loginInitializr(
      @RequestHeader(value = "x-aid", required = false) Long aid,
      @RequestHeader(value = "x-uid", required = false) Long uid,
      @RequestHeader(value = "x-token", required = false) String token
  );

  /**
   * <h2>TODO what you want to do</h2>
   * date 2020-07-22 15:09
   *
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  @ApiOperation(value = "退出")
  @DeleteMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void logout();

  /**
   * <h2>登录信息查询</h2>
   * date 2020-07-22 14:28
   *
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  @ApiOperation(value = "登录信息查询")
  @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void querySessionInfo();

}
