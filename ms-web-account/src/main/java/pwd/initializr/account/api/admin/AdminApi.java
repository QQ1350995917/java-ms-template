package pwd.initializr.account.api.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.LinkedHashSet;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.api.admin.vo.AdminAccountInput;
import pwd.initializr.account.api.admin.vo.AdminUserInput;
import pwd.initializr.account.api.admin.vo.AdminUserQueryInput;
import pwd.initializr.account.api.admin.vo.CreateAdminInput;
import pwd.initializr.common.web.api.vo.PageInput;
import pwd.initializr.common.web.api.vo.ScopeInput;
import pwd.initializr.common.web.api.vo.SortInput;
import pwd.initializr.common.web.persistence.entity.ScopeEntity;

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
    tags = "人员管理",
    value = "adminManageApi",
    description = "[创建管理员/账号，人员/账号列表，人员/账号启/禁用，人员/账号删除]"
)
@RestController(value = "admin")
@RequestMapping(value = "/api/admin/admin")
public interface AdminApi {

  @ApiOperation(value = "创建管理员用户信息以及账户信息")
  @PostMapping(value = {
      ""}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void create(@RequestBody @Valid @NotNull(message = "参数不能为空") CreateAdminInput input);

  @ApiOperation(value = "删除账户，最后一个可用账户不可被删除")
  @DeleteMapping(value = {"/account"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void delAccount(
      @RequestBody @Valid @NotNull(message = "参数不能为空") @Size(message = "参数不能为空") List<Long> ids);

  @ApiOperation(value = "删除用户，同时删除其下所有账户")
  @DeleteMapping(value = {"/user"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void delUser(
      @RequestBody @Valid @NotNull(message = "参数不能为空") @Size(message = "参数不能为空") List<Long> ids);

  @ApiOperation(value = "禁用账户，最后一个可用账户不可被禁用")
  @PatchMapping(value = {"/account/disable"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void disableAccount(@RequestBody @Valid @NotNull(message = "参数不能为空") List<Long> ids);

  @ApiOperation(value = "禁用用户，同时禁用其下所有账户")
  @PatchMapping(value = {"/user/disable"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void disableUser(@RequestBody @Valid @NotNull(message = "参数不能为空") List<Long> ids);

  @ApiOperation(value = "启用账户")
  @PatchMapping(value = {"/account/enable"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void enableAccount(@RequestBody @Valid @NotNull(message = "参数不能为空") List<Long> ids);

  @ApiOperation(value = "启用用户，同时启用其下所有账户")
  @PatchMapping(value = {"/user/enable"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void enableUser(@RequestBody @Valid @NotNull(message = "参数不能为空") List<Long> ids);

  /**
   * <h2>根据普通用户ID查询该用户的账号信息</h2>
   * date 2020-08-08 19:39
   *
   * @param userId 用户ID
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  @ApiOperation(value = "根据用户ID查询账户信息")
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
//  @ApiImplicitParams(value = {
//      @ApiImplicitParam(name = "scopes", value = "查询条件", paramType = "query", allowMultiple = true, dataType = "scopeInput"),
//      @ApiImplicitParam(name = "sorts", value = "排序条件", paramType = "query", allowMultiple = true, dataType = "sortInput"),
//      @ApiImplicitParam(name = "page", value = "分页条件", paramType = "query", allowMultiple = false, dataType = "pageInput")
//  })
  void listUser(
      @ApiParam(allowMultiple = true,type = "scopeInput") @RequestParam(value = "scopes",required = false) LinkedHashSet<ScopeInput> scopes,
      @ApiParam(allowMultiple = true,type = "sortInput") @RequestParam(value = "sorts",required = false) LinkedHashSet<SortInput> sorts,
      @ApiParam(allowMultiple = false,type = "page") @RequestParam(value = "page",required = false) PageInput page);

  @ApiOperation(value = "更新账户信息")
  @PutMapping(value = {"/account/{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void updateAccount(@PathVariable("id") @Valid @NotNull(message = "参数不能为空") Long id,
      @RequestBody @Valid @NotNull(message = "参数不能为空") AdminAccountInput input);

  @ApiOperation(value = "更新用户信息")
  @PutMapping(value = {"/user/{uid}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void updateUser(@PathVariable("uid") @Valid @NotNull(message = "参数不能为空") Long id,
      @RequestBody @Valid @NotNull(message = "参数不能为空") AdminUserInput input);

}
