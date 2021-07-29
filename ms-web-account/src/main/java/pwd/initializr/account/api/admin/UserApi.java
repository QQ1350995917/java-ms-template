package pwd.initializr.account.api.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.api.admin.vo.UserCreateInput;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>控制层接口：用户信息</h1>
 *
 * date 2019-09-15 09:03
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "前台用户管理",
    value = "userManageApi",
    description = "[用户/账户列表，用户/账户详情，用户/账户禁用/启用，用户/账户删除]"
)
@RestController(value = "userManageApi")
@RequestMapping(value = "/api/admin/user")
public interface UserApi {

  @ApiOperation(value = "创建管理员用户信息以及账户信息")
  @PostMapping(value = {
      ""}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void create(@RequestBody @Valid @NotNull(message = "参数不能为空") UserCreateInput input);

  @ApiOperation(value = "删除用户，同时删除其下所有账户")
  @DeleteMapping(value = {"/user"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void delUser(@RequestBody @Valid @NotNull(message = "参数不能为空") List<Long> ids);

  @ApiOperation(value = "禁用用户")
  @PatchMapping(value = {"/user/disable"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void disableUser(@RequestBody @Valid @NotNull(message = "参数不能为空") List<Long> ids);

  @ApiOperation(value = "启用用户")
  @PatchMapping(value = {"/user/enable"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void enableUser(@RequestBody @Valid @NotNull(message = "参数不能为空") List<Long> ids);

  @ApiOperation(value = "用户查询，查询对应的用户信息")
  @GetMapping(value = {"/{uid}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void getUser(@PathVariable("uid") @Valid @NotNull(message = "参数不能为空") Long userId);

  @ApiOperation(value = "根据用户查询账户信息")
  @GetMapping(value = {"/account/{uid}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void listAccount(@PathVariable("uid") @Valid @NotNull(message = "参数不能为空") Long userId);

  /**
   * <h2>根据分页信息以及分页条件查找用户</h2>
   * date 2020-08-08 19:43
   *
   * @param scopes 查询参数
   * @param sorts 排序信息
   * @param page 分页信息
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  @ApiOperation(value = "根据条件分页查询用户信息")
  @GetMapping(value = {"/user"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void listUser(
      @ApiParam(name = "scopes", value = "[{\"hit\":\"指定查询方式[EM:精确,ENM:精确非,AL:前后模糊,LL:左模糊,RL:右模糊,S:范围]\",\"fieldName\":\"指定查询字段\",\"fieldValue\":\"指定查询目标\",\"start\":\"范围查询起始值，区间包含\",\"end\":\"范围查询结束值，区间包含\"}]") @RequestParam(value = "scopes", required = false) String scopes,
      @ApiParam(name = "sorts", value = "[{\"fieldName\":\"指定排序字段\",\"sort\":\"[desc|asc]\"}]") @RequestParam(value = "sorts", required = false) String sorts,
      @ApiParam(name = "page", value = "{\"index\":0,\"size\":12}") @RequestParam(value = "page", required = false) String page);


}
