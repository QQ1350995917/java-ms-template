package pwd.initializr.account.api.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.business.admin.UserService;
import pwd.initializr.common.web.api.admin.AdminController;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
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
    description = "[用户列表，用户详情，禁用用户，启用用户]"
)
@RestController(value = "userManageApi")
@RequestMapping(value = "/api/admin/user")
public class UserController extends AdminController implements UserApi {

  @Autowired
  private UserService userService;

  @ApiOperation(value = "用户列表")
  @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void listUser() {
//    ObjectList<UserBO> userObjectList = userService.listUser();
//    outputData(userObjectList);// TODO 转化为VO
  }

  @ApiOperation(value = "用户详情")
  @GetMapping(value = {"/{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void detailedUser(@PathVariable Long id) {
//    UserBO user = userService.findById(id);
//    outputData(user);// TODO 转化为VO
  }

  @ApiOperation(value = "禁用用户")
  @PatchMapping(value = {"/block/{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void blockUser(@PathVariable Long id) {

  }

  @ApiOperation(value = "启用用户")
  @PatchMapping(value = {"/unBlock/{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @Override
  public void unblockUser(@PathVariable Long id) {

  }
}
