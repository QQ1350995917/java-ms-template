package pwd.initializr.monitor.api.robot;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.robot.RobotController;
import pwd.initializr.monitor.api.robot.vo.ServiceInstanceInput;

/**
 * pwd.initializr.monitor.api.robot@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-21 18:34
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "服务实例信息",
    value = "服务实例信息",
    description = "[JST，JRT，FILE，USER]"
)
@RestController(value = "robot")
@RequestMapping(value = "/api/robot/instance")
@Slf4j
public class InstanceController extends RobotController implements InstanceApi {

    @Override
    public void renewInstance(@NotNull(message = "参数不能为空") ServiceInstanceInput input) {
        log.info(JSON.toJSONString(input));
        this.outputData();
    }
}
