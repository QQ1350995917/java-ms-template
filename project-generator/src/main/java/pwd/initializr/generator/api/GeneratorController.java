package pwd.initializr.generator.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pwd.initializr.common.web.api.admin.AdminController;
import pwd.initializr.generator.api.vo.GeneratorInput;
import pwd.initializr.generator.business.mysql.database.DataSourceBO;
import pwd.initializr.generator.business.mysql.database.DataSourceTableColumn;
import pwd.initializr.generator.business.mysql.database.TableColumnBO;
import pwd.initializr.generator.business.mysql.architecture.ProjectBO;
import pwd.initializr.generator.business.mysql.architecture.Springboot;
import pwd.initializr.generator.business.mysql.architecture.SrcMainJavaPackagePersistenceDao;
import pwd.initializr.generator.business.mysql.architecture.SrcMainJavaPackagePersistenceEntity;
import pwd.initializr.generator.util.VariableName;

/**
 * pwd.initializr.generator.api@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-09-10 12:43
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "代码生成",
    value = "generatorApi",
    description = "[工程代码生成]"
)
@Controller(value = "generator")
@RequestMapping(value = "/api/admin/generator")
@Slf4j
public class GeneratorController extends AdminController {

    @Value("${project.genetator.storage}")
    private String projectGenetatorStorage;

    @ApiOperation(value = "连接测试同时获取表名")
    @PostMapping(value = {
        ""}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void generate(@RequestBody @NotNull(message = "参数不能为空") GeneratorInput input) {
        DataSourceBO dataSourceBO = new DataSourceBO();
        dataSourceBO.setDatabaseName(input.getName());
        dataSourceBO.setDriver(input.getDriver());
        dataSourceBO.setUrl(input.getUrl());
        dataSourceBO.setUsername(input.getUser());
        dataSourceBO.setPassword(input.getPwd());
        DataSourceTableColumn dataSourceTableColumn = new DataSourceTableColumn(dataSourceBO,
            input.getName(), input.getTables());
        Map<String, Object> exec = dataSourceTableColumn.exec();

        ProjectBO projectBO = new ProjectBO();
        projectBO.setExportDir(projectGenetatorStorage);
        projectBO.setProjectName(input.getProjectName());
        projectBO.setPackageName(input.getPackageName());
        projectBO.setProjectVersion(input.getProjectVersion());
        projectBO.setApplicationName(input.getApplicationName());
        projectBO.setProjectPort(80);

        Springboot springboot = new Springboot();
        springboot.generateProjectFrame(projectBO);

        Set<String> tables = exec.keySet();
        for (String table : tables) {
            try {
                String tableName = table;
                String className = VariableName.underlineToHump(tableName);
                List<TableColumnBO> tableColumnBOList = (List<TableColumnBO>) exec.get(table);
                new SrcMainJavaPackagePersistenceDao(projectBO, tableName, className)
                    .createProjectFile();
                new SrcMainJavaPackagePersistenceEntity(projectBO, tableName, className,
                    tableColumnBOList).createProjectFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
