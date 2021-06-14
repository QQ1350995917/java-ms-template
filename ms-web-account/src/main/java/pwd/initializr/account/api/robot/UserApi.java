package pwd.initializr.account.api.robot;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.api.robot.vo.ListUserInput;

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
public interface UserApi {


  @ApiOperation(value = "用户信息查询")
  @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void listById(@RequestParam("ids") Long[] ids);
}
