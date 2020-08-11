package pwd.initializr.account.api.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.api.user.vo.UserUpdateInput;

/**
 * pwd.initializr.account.api.user@ms-web-initializr
 *
 * <h1>控制层接口：用户信息</h1>
 *
 * date 2020-07-28 23:05
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "用户管理",
    value = "userApi",
    description = "[用户信息查询，用户信息修改]"
)
@RestController(value = "userApi")
@RequestMapping(value = "/api/user")
public interface UserApi {

  /**
   * <h2>通过用户ID查找用户信息</h2>
   * date 2020-08-02 23:38
   *
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  @ApiOperation(value = "通过用户ID查找用户信息")
  @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void queryUser();

  @ApiOperation(value = "更新用户信息")
  @PutMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void updateUser(@RequestBody @Valid @NotNull(message = "参数不能为空") UserUpdateInput input);
}
