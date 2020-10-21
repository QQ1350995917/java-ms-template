package pwd.initializr.monitor.api.robot;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.monitor.api.robot.vo.ServiceInstanceInput;

/**
 * pwd.initializr.monitor.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-20 21:36
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "服务实例信息",
    value = "服务实例信息",
    description = "[JST，JRT，FILE，USER]"
)
@RestController(value = "robot")
@RequestMapping(value = "/api/robot/service")
public interface InstanceApi {

  /**
   * <h2>TODO what you want to do</h2>
   * date 2020-10-21 14:28
   *
   * @param input
   * @return void
   * @author DingPengwei[www.dingpengwei@foxmail.com]
   * @since DistributionVersion
   */
  @PostMapping("")
  void renewInstance(@RequestBody ServiceInstanceInput input);

}
