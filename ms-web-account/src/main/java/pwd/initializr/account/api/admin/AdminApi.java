package pwd.initializr.account.api.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.api.admin.vo.AdminAccountLoginInput;
import pwd.initializr.account.api.admin.vo.AdminCreateInput;
import pwd.initializr.account.api.admin.vo.AdminUpdateInput;
import pwd.initializr.account.api.admin.vo.AdminUserCreateInput;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>控制层接口：管理员信息</h1>
 *
 * date 2019-10-26 8:13
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "后台人员管理",
    value = "adminManageApi",
    description = "[创建管理员/账号，人员/账号列表，人员/账号启/禁用，人员/账号删除]"
)
@RestController(value = "admin")
@RequestMapping(value = "/api/admin")
public interface AdminApi {

  @ApiOperation(value = "创建管理员用户信息以及账户信息")
  @PostMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void create(@RequestBody @Valid @NotNull(message = "参数不能为空") AdminCreateInput input);

  @ApiOperation(value = "创建管理员用户信息以及账户信息")
  @PutMapping(value = {"/{uid}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void edit(@RequestBody @Valid @NotNull(message = "参数不能为空") AdminUpdateInput input);

  @ApiOperation(value = "删除用户，同时删除其下所有账户")
  @DeleteMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void delete(
      @RequestBody @Valid @NotNull(message = "参数不能为空") @Size(message = "参数不能为空") List<Long> ids);

  @ApiOperation(value = "禁用用户，同时禁用其下所有账户")
  @PatchMapping(value = {"/disable"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void disable(@RequestBody @Valid @NotNull(message = "参数不能为空") List<Long> ids);

  @ApiOperation(value = "启用用户，同时启用其下所有账户")
  @PatchMapping(value = {"/enable"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void enable(@RequestBody @Valid @NotNull(message = "参数不能为空") List<Long> ids);

  @ApiOperation(value = "更新用户信息")
  @PatchMapping(value = {"/{uid}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void update(@PathVariable("uid") @Valid @NotNull(message = "参数不能为空") Long uid,
      @RequestBody @Valid @NotNull(message = "参数不能为空") AdminUserCreateInput input);

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
  @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void list(
      @ApiParam(name = "scopes", value = "[{\"hit\":\"指定查询方式[E:精确,AL:前后模糊,LL:左模糊,RL:右模糊,S:范围]\",\"fieldName\":\"指定查询字段\",\"fieldValue\":\"指定查询目标\",\"start\":\"范围查询起始值，区间包含\",\"end\":\"范围查询结束值，区间包含\"}]") @RequestParam(value = "scopes", required = false) String scopes,
      @ApiParam(name = "sorts", value = "[{\"fieldName\":\"指定排序字段\",\"sort\":\"[desc|asc]\"}]") @RequestParam(value = "sorts", required = false) String sorts,
      @ApiParam(name = "page", value = "{\"index\":0,\"size\":12}") @RequestParam(value = "page", required = false) String page);

  @ApiOperation(value = "用户查询，查询对应的管理员用户")
  @GetMapping(value = {"/{uid}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void get(@PathVariable("uid") @Valid @NotNull(message = "参数不能为空") Long uid);

  @ApiOperation(value = "密码重置，重置用户名下静态登录账号的密码为默认密码")
  @PatchMapping(value = {"/{uid}/pwd/reset"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void resetPwd(@PathVariable("uid") @Valid @NotNull(message = "参数不能为空") Long uid);

  @ApiOperation(value = "新增账户，同一种类型的账号只能创建一个")
  @PostMapping(value = {"/{uid}/{type}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void createAccount(
      @RequestParam("uid") @Valid @NotNull(message = "参数不能为空") Long uid,
      @RequestParam("type") @Valid @NotNull(message = "参数不能为空") Long type
  );

  @ApiOperation(value = "删除账户，最后一个可用账户不可被删除")
  @DeleteMapping(value = {"/{uid}/{aid}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void deleteAccount(
      @RequestParam("uid") @Valid @NotNull(message = "参数不能为空") Long uid,
      @RequestParam("aid") @Valid @NotNull(message = "参数不能为空") Long aid
  );

  @ApiOperation(value = "禁用账户，最后一个可用账户不可被禁用")
  @PatchMapping(value = {"/{uid}/{aid}/disable"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void disableAccount(
      @RequestParam("uid") @Valid @NotNull(message = "参数不能为空") Long uid,
      @RequestParam("aid") @Valid @NotNull(message = "参数不能为空") Long aid
  );

  @ApiOperation(value = "启用账户")
  @PatchMapping(value = {"/{uid}/{aid}/enable"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void enableAccount(
      @RequestParam("uid") @Valid @NotNull(message = "参数不能为空") Long uid,
      @RequestParam("aid") @Valid @NotNull(message = "参数不能为空") Long aid
  );

  @ApiOperation(value = "更新账户信息")
  @PatchMapping(value = {"/{uid}/{aid}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void updateAccount(
      @PathVariable("uid") @Valid @NotNull(message = "参数不能为空") Long uid,
      @PathVariable("aid") @Valid @NotNull(message = "参数不能为空") Long aid,
      @RequestBody @Valid @NotNull(message = "参数不能为空") AdminAccountLoginInput input);
  
  @ApiOperation(value = "更新账户密码")
  @PatchMapping(value = {"/{uid}/{aid}/pwd"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void updateAccountPwd(
      @PathVariable("uid") @Valid @NotNull(message = "参数不能为空") Long uid,
      @PathVariable("aid") @Valid @NotNull(message = "参数不能为空") Long aid,
      @RequestBody @Valid @NotNull(message = "参数不能为空") AdminAccountLoginInput input);

  /**
   * <h2>根据普通用户ID查询该用户的账号信息</h2>
   * date 2020-08-08 19:39
   *¶
   * @param uid 用户ID
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  @ApiOperation(value = "根据用户ID查询账户信息")
  @GetMapping(value = {"/{uid}/*"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void listAccount(@PathVariable("uid") @Valid @NotNull(message = "参数不能为空") Long uid);

  /**
   * <h2>根据普通用户ID以及账号ID查询该用户的账号信息</h2>
   * date 2020-08-08 19:39
   *
   * @param uid 用户ID
   * @param aid 账号ID
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  @ApiOperation(value = "根据用户ID查询账户信息")
  @GetMapping(value = {"/{uid}/{aid}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void getAccount(
      @PathVariable("uid") @Valid @NotNull(message = "用户ID不能为空") Long uid,
      @PathVariable("uid") @Valid @NotNull(message = "账号ID不能为空") Long aid
  );

  @ApiOperation(value = "密码重置，重置用户名下指定的静态登录账号的密码为默认密码")
  @PatchMapping(value = {"/{uid}/{aid}/pwd/reset"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void resetAccountPwd(
      @PathVariable("uid") @Valid @NotNull(message = "用户ID不能为空") Long uid,
      @PathVariable("aid") @Valid @NotNull(message = "账号ID不能为空") Long aid
  );

}
