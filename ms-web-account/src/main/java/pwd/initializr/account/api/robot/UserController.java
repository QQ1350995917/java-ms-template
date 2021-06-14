package pwd.initializr.account.api.robot;

import io.swagger.annotations.Api;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import net.bytebuddy.dynamic.scaffold.MethodGraph.Linked;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.api.robot.vo.ListUserInput;
import pwd.initializr.account.business.robot.UserService;
import pwd.initializr.account.business.robot.bo.UserBO;
import pwd.initializr.account.rpc.RPCUser;
import rx.internal.util.LinkedArrayList;

/**
 * pwd.initializr.account.api.robot@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-05 15:32
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "用户信息管理",
    value = "userInfoApi",
    description = "[用户信息查询]"
)
@RestController(value = "userInfoApi")
@RequestMapping(value = "/api/robot/user")
public class UserController extends pwd.initializr.common.web.api.user.UserController implements
    UserApi {

  @Autowired
  private UserService userService;

  @Override
  public void listById(@RequestParam("ids") Long[] ids) {
    List<UserBO> userBOS = userService.listByUserId(ids);
    List<RPCUser> result = userBOS.stream().map(this::convertUserBO2RPC)
        .collect(Collectors.toList());
    outputData(result);
  }

  private RPCUser convertUserBO2RPC(UserBO bo){
    RPCUser rpcUser = new RPCUser();
    BeanUtils.copyProperties(bo,rpcUser);
    return rpcUser;
  }
}
