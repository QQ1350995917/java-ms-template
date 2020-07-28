package pwd.initializr.account.api.user;

import io.swagger.annotations.Api;
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
public class UserController extends pwd.initializr.common.web.api.user.UserController implements
    UserApi {

  @Override
  public void queryUser() {

  }

  @Override
  public void updateUser(UserUpdateInput input) {

  }
}
