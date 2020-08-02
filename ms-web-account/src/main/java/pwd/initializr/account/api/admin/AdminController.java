package pwd.initializr.account.api.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import pwd.initializr.account.api.admin.vo.AdminAccountInput;
import pwd.initializr.account.api.admin.vo.AdminAccountOutput;
import pwd.initializr.account.api.admin.vo.AdminUserInput;
import pwd.initializr.account.api.admin.vo.AdminUserOutput;
import pwd.initializr.account.api.admin.vo.CreateAdminInput;
import pwd.initializr.account.business.admin.AdminAccountService;
import pwd.initializr.account.business.admin.AdminUserService;
import pwd.initializr.account.business.admin.AdminUserServiceWrap;
import pwd.initializr.account.business.admin.bo.AdminAccountBO;
import pwd.initializr.account.business.admin.bo.AdminUserBO;
import pwd.initializr.common.web.api.vo.PageInput;
import pwd.initializr.common.web.api.vo.PageOutput;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.persistence.entity.EntityAble;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>控制层接口：管理员信息</h1>
 *
 * date 2019-10-26 8:14
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
public class AdminController extends pwd.initializr.common.web.api.admin.AdminController implements
    AdminApi {

  @Autowired
  private AdminUserServiceWrap adminUserServiceWrap;
  @Autowired
  private AdminUserService adminUserService;
  @Autowired
  private AdminAccountService adminAccountService;

  @ApiOperation(value = "创建管理员用户信息以及账户信息")
  @PostMapping(value = {
      ""}, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void create(CreateAdminInput input) {
    AdminUserBO adminUserBO = new AdminUserBO();
    AdminAccountBO adminAccountBO = new AdminAccountBO();
    BeanUtils.copyProperties(input.getUser(), adminUserBO);
    BeanUtils.copyProperties(input.getAccount(), adminAccountBO);
    AdminUserBO userBO = adminUserServiceWrap.insert(adminUserBO, adminAccountBO);
    super.outputData(userBO.getId());
  }

  @ApiOperation(value = "删除账户，最后一个可用账户不可被删除")
  @DeleteMapping(value = {"/account"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void delAccount(@RequestBody List<Long> ids) {

  }

  @ApiOperation(value = "删除用户，同时删除其下所有账户")
  @DeleteMapping(value = {"/user"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void delUser(@RequestBody List<Long> ids) {
    Boolean del = adminUserServiceWrap.deleteByUserId(ids);
    outputData(del);
  }

  @ApiOperation(value = "禁用账户，最后一个可用账户不可被禁用")
  @PatchMapping(value = {"/account/disable"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void disableAccount(@RequestBody List<Long> ids) {

  }

  @ApiOperation(value = "禁用用户，同时禁用其下所有账户")
  @PatchMapping(value = {"/user/disable"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void disableUser(@RequestBody List<Long> ids) {
    Boolean able = adminUserServiceWrap.ableByUserId(ids, EntityAble.DISABLE);
    outputData(able);
  }

  @ApiOperation(value = "启用账户")
  @PatchMapping(value = {"/account/enable"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void enableAccount(@RequestBody List<Long> ids) {

  }

  @ApiOperation(value = "启用用户，同时启用其下所有账户")
  @PatchMapping(value = {"/user/enable"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void enableUser(@RequestBody List<Long> ids) {
    Boolean able = adminUserServiceWrap.ableByUserId(ids, EntityAble.ENABLE);
    outputData(able);
  }

  @ApiOperation(value = "根据用户查询账户信息")
  @GetMapping(value = {"/account/{uid}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void listAccount(@PathVariable("uid") Long userId,
      @RequestParam AdminAccountInput input) {
    AdminAccountBO queryCondition = new AdminAccountBO();
    List<AdminAccountBO> adminAccountBOS = adminAccountService.queryByUserId(userId);
    PageOutput<AdminAccountOutput> result = new PageOutput<>();
    adminAccountBOS.forEach(adminAccountBO -> {
      AdminAccountOutput adminAccountOutput = new AdminAccountOutput();
      BeanUtils.copyProperties(adminAccountBO, adminAccountOutput);
      result.getElements().add(adminAccountOutput);
    });
    outputData(result);
  }

  @ApiOperation(value = "根据条件分页查询用户信息")
  @GetMapping(value = {"/user"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void listUser(@RequestParam PageInput pageInput, @RequestParam AdminUserInput input) {
    AdminUserBO queryCondition = new AdminUserBO();
    PageableQueryResult<AdminUserBO> adminUserBOPageableQueryResult = adminUserService
        .queryAllByCondition(queryCondition, pageInput.getIndex(), pageInput.getSize());
    PageOutput<AdminUserOutput> result = new PageOutput<>();
    adminUserBOPageableQueryResult.getElements().forEach(adminUserBO -> {
      AdminUserOutput adminUserOutput = new AdminUserOutput();
      BeanUtils.copyProperties(adminUserBO, adminUserOutput);
      result.getElements().add(adminUserOutput);
    });
    result.setTotal(adminUserBOPageableQueryResult.getTotal());
    result.setIndex(adminUserBOPageableQueryResult.getIndex());
    result.setSize(adminUserBOPageableQueryResult.getSize());
    outputData(result);
  }

  @ApiOperation(value = "更新账户信息")
  @PutMapping(value = {"/account/{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void updateAccount(@PathVariable("id") Long id, AdminAccountInput input) {

  }

  @ApiOperation(value = "更新用户信息")
  @PutMapping(value = {"/user/{uid}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void updateUser(@PathVariable("uid") Long id, @RequestBody AdminUserInput input) {

  }
}
