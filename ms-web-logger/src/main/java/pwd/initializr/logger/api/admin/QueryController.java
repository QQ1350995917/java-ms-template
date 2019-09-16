package pwd.initializr.logger.api.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.admin.AdminController;

/**
 * pwd.initializr.logger.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-15 10:10
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "日志查询",
    value = "loggerQueryApi",
    description = "日志查询API"
)
@RestController(value = "loggerQueryApi")
@RequestMapping(value = "/logger/api/admin/logger")
public class QueryController extends AdminController implements QueryApi {

    @ApiOperation(value = "用户轨迹")
    @GetMapping(value = {"/account"}, produces = "application/json;charset=UTF-8")
    @Override
    public void queryByAccount() {
        super.outputData();
    }

    @ApiOperation(value = "操作轨迹")
    @GetMapping(value = {"/operate"}, produces = "application/json;charset=UTF-8")
    @Override
    public void queryByOperate() {
        super.outputData();
    }
}
