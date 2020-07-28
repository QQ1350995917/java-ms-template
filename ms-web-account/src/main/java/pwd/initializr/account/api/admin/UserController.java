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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.api.admin.vo.UserAccountInput;
import pwd.initializr.account.api.admin.vo.UserAccountOutput;
import pwd.initializr.account.api.admin.vo.UserUserInput;
import pwd.initializr.account.api.admin.vo.UserUserOutput;
import pwd.initializr.account.business.user.UserAccountService;
import pwd.initializr.account.business.user.UserUserService;
import pwd.initializr.account.business.user.UserUserServiceWrap;
import pwd.initializr.account.business.user.bo.UserAccountBO;
import pwd.initializr.account.business.user.bo.UserUserBO;
import pwd.initializr.common.web.api.admin.AdminController;
import pwd.initializr.common.web.api.vo.PageInput;
import pwd.initializr.common.web.api.vo.PageOutput;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.common.web.persistence.entity.EntityAble;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>控制层接口：用户信息</h1>
 *
 * date 2019-09-14 21:15
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "系统用户管理",
    value = "userManageApi",
    description = "[用户/账户列表，用户/账户详情，用户/账户禁用/启用，用户/账户删除]"
)
@RestController(value = "userManageApi")
@RequestMapping(value = "/api/admin/user")
public class UserController extends AdminController implements UserApi {

  @Autowired
  private UserAccountService userAccountService;

  @Autowired
  private UserUserService userUserService;

  @Autowired
  private UserUserServiceWrap userUserServiceWrap;

  @ApiOperation(value = "删除账户，最后一个可用账户不可被删除")
  @DeleteMapping(value = {"/account"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void delAccount(@RequestBody List<Long> ids) {

  }

  @ApiOperation(value = "删除用户，同时删除其下所有账户")
  @DeleteMapping(value = {"/user"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void delUser(@RequestBody List<Long> ids) {
    Boolean result = userUserServiceWrap.deleteByUserId(ids);
    outputData(result);
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
    Boolean result = userUserServiceWrap.ableByUserId(ids, EntityAble.DISABLE);
    outputData(result);
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
    Boolean result = userUserServiceWrap.ableByUserId(ids, EntityAble.ENABLE);
    outputData(result);
  }

  @ApiOperation(value = "根据用户查询账户信息")
  @GetMapping(value = {"/account/{uid}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void listAccount(@PathVariable("uid") Long userId,
      @RequestParam UserAccountInput input) {
    PageOutput<UserAccountOutput> responseData = new PageOutput<>();
    ObjectList<UserAccountBO> queryResult = userAccountService
        .queryAllByUserId(userId);
    queryResult.getElements().forEach(element -> {
      UserAccountOutput userAccountOutput = new UserAccountOutput();
      BeanUtils.copyProperties(element, userAccountOutput);
      responseData.getElements().add(userAccountOutput);
    });
    outputData(responseData);
  }

  @ApiOperation(value = "根据条件分页查询用户信息")
  @GetMapping(value = {"/user"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void listUser(@RequestParam PageInput pageInput, @RequestParam UserUserInput input) {
    PageOutput<UserUserOutput> responseData = new PageOutput<>();
    UserUserBO userUserBO = new UserUserBO();
    ObjectList<UserUserBO> queryResult = userUserService
        .queryAllByCondition(userUserBO, pageInput.getIndex(), pageInput.getSize());
    queryResult.getElements().forEach(element -> {
      UserUserOutput userUserOutput = new UserUserOutput();
      BeanUtils.copyProperties(element, userUserOutput);
      responseData.getElements().add(userUserOutput);
    });
    responseData.setSize(queryResult.getSize());
    responseData.setIndex(queryResult.getIndex());
    responseData.setTotal(queryResult.getTotal());
    outputData(responseData);
  }
}
