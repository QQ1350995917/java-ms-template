package pwd.initializr.account.api.user;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import pwd.initializr.account.api.user.vo.SignUpByNamePwdInput;

/**
 * pwd.initializr.account.api.user@ms-web-initializr
 *
 * <h1>控制层接口：账号信息</h1>
 *
 * date 2019-09-14 21:17
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface AccountApi {

    /**
     * <h2>初始化登录环境</h2>
     * date 2020-08-04 10:12
     *
     * @param token 已有的token信息
     * @return void
     * @author DingPengwei[www.dingpengwei@foxmail.com]
     * @since DistributionVersion
     */
    @ApiOperation(value = "初始化登录环境")
    @GetMapping(value = {"/init"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void createInitializr(@RequestHeader(value = "x-token", required = false) String token);

    /**
     * <h2>刷新注册验证码</h2>
     * date 2020-08-04 10:13
     *
     * @return void
     * @author DingPengwei[www.dingpengwei@foxmail.com]
     * @since DistributionVersion
     */
    @ApiOperation(value = "验证码刷新")
    @GetMapping(value = {"/captcha"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void loginCaptchaRefresh();


  /**
   * <h2>通过账号和密码注册新账号</h2>
   * date 2020-08-02 23:14
   *
   * @param input 输入对象
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  @ApiOperation(value = "通过用户名和密码注册注册账号")
  @PostMapping(value = {"/primeval"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void createByNameAndPwd(
      @RequestBody
      @Validated
      @ApiParam(required = true)
      @NotNull(message = "参数不能为空")
          SignUpByNamePwdInput input);

  /**
   * <h2>通过账号ID删除账号</h2>
   * date 2020-08-02 23:19
   *
   * @param id 账号ID
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  @ApiOperation(value = "通过账号ID删除账号")
  @DeleteMapping(value = {"/{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void deleteById(
      @NotNull(message = "参数不能为空")
      @PathVariable(name = "id", required = false)
      @Min(value = 1, message = "参数不能小于1")
          Long id);

  /**
   * <h2>通过账号ID禁用账号</h2>
   * date 2020-08-02 23:20
   *
   * @param id 账号ID
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  @ApiOperation(value = "通过账号ID禁用账号")
  @PutMapping(value = {"/disable/{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void disableById(
      @NotNull(message = "参数不能为空")
      @PathVariable(name = "id", required = false)
      @Min(value = 1, message = "参数不能小于1")
          Long id);

  /**
   * <h2>通过账号ID启用账号</h2>
   * date 2020-08-02 23:20
   *
   * @param id 账号ID
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  @ApiOperation(value = "通过账号ID启用账号")
  @PutMapping(value = {"/enable/{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void enableById(
      @NotNull(message = "参数不能为空")
      @PathVariable(name = "id", required = false)
      @Min(value = 1, message = "参数不能小于1")
          Long id);

  /**
   * <h2>通过用户ID查找其下所有账号</h2>
   * date 2020-08-02 23:16
   *
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  @ApiOperation(value = "通过用户ID查找其下所有账号")
  @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void findByUserId();

  /**
   * <h2>检测账号可用性</h2>
   * date 2020-08-02 23:45
   *
   * @param loginName 账号
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  void usabilityCheck(
      @NotBlank(message = "参数不能为空")
      @PathVariable(name = "loginName", required = false)
      @Size(min = 6, max = 18, message = "账号长度必须在[6,18]之间")
          String loginName);

}
