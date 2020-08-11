package pwd.initializr.account.api.user;

import io.swagger.annotations.Api;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.api.admin.vo.UserUserOutput;
import pwd.initializr.account.api.user.vo.UserUpdateInput;
import pwd.initializr.account.business.user.UserUserService;
import pwd.initializr.account.business.user.bo.UserUserBO;
import pwd.initializr.common.web.api.vo.Meta;

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
public class UserController extends pwd.initializr.common.web.api.user.UserController implements
    UserApi {

  @Autowired
  private UserUserService userUserService;

  @Override
  public void queryUser() {
    Long userId = getUid();
    UserUserBO userUserBO = userUserService.queryById(userId);
    UserUserOutput userUserOutput = new UserUserOutput();
    BeanUtils.copyProperties(userUserBO, userUserOutput);
    outputData(userUserOutput);
  }

  @Override
  public void updateUser(@Valid @NotNull(message = "参数不能为空") UserUpdateInput input) {
    Long userId = getUid();
    UserUserBO userUserBO = new UserUserBO();
    BeanUtils.copyProperties(input, userUserBO);
    userUserBO.setId(userId);
    Integer update = userUserService.update(userUserBO);
    outputData(new Meta(), update);
  }
}
