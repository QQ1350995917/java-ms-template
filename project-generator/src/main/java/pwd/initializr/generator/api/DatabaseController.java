package pwd.initializr.generator.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.admin.AdminController;
import pwd.initializr.generator.api.vo.DatabaseInput;
import pwd.initializr.generator.business.mysql.database.DataSourceComponent;
import pwd.initializr.generator.business.mysql.database.DataSourceBO;
import pwd.initializr.generator.business.mysql.database.DataSourceTable;

/**
 * pwd.initializr.generator.api@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-09-10 11:26
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "数据库",
    value = "databaseApi",
    description = "[连接测试同时获取表名]"
)
@RestController(value = "database")
@RequestMapping(value = "/api/admin/database")
@Slf4j
public class DatabaseController extends AdminController {

    @ApiOperation(value = "连接测试同时获取表名")
    @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void getTables(@Valid @NotNull(message = "参数不能为空") DatabaseInput input) {
        DataSourceBO dataSourceBO = new DataSourceBO();
        dataSourceBO.setDatabaseName(input.getName());
        dataSourceBO.setDriver(input.getDriver());
        dataSourceBO.setUrl(input.getUrl());
        dataSourceBO.setUsername(input.getUser());
        dataSourceBO.setPassword(input.getPwd());
        DataSourceComponent dataSourceComponent = new DataSourceTable(dataSourceBO,input.getName());
        Map<String, Object> exec = dataSourceComponent.exec();
        outputData(exec);
    }
}
