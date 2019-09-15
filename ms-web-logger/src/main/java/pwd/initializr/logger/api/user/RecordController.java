package pwd.initializr.logger.api.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.user.UserController;

/**
 * pwd.initializr.logger.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-15 10:11
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "日志记录",
    value = "loggerRecordApi",
    description = "日志记录API"
)
@RestController(value = "loggerRecordApi")
@RequestMapping(value = "/api/user/logger")
public class RecordController extends UserController implements RecordApi {

    @ApiOperation(value = "日志记录")
    @PostMapping(value = {"/"}, produces = "application/json;charset=UTF-8")
    @Override
    public void record() {
        super.outputData();
    }
}
