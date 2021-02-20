package pwd.initializr.generator.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pwd.initializr.common.utils.DateTimeUtil;
import pwd.initializr.common.utils.StringUtils;
import pwd.initializr.common.utils.ZipUtil;
import pwd.initializr.common.web.api.admin.AdminController;
import pwd.initializr.generator.api.vo.GeneratorInput;
import pwd.initializr.generator.business.mysql.architecture.ArchitectureBoot;
import pwd.initializr.generator.business.mysql.architecture.ProjectBO;
import pwd.initializr.generator.business.mysql.architecture.SrcMainResourcesTemplatesIndex;
import pwd.initializr.generator.business.mysql.database.DataSourceBO;
import pwd.initializr.generator.business.mysql.database.DataSourceTableColumn;
import pwd.initializr.generator.business.mysql.database.DatabaseBoot;
import pwd.initializr.generator.business.mysql.database.TableColumnBO;
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

  @Value("${project.generator.storage}")
  private String projectGeneratorStorage;

  @ApiOperation(value = "工程代码下载")
  @GetMapping(value = {"/{filename}"})
  public void download(@PathVariable("filename") @NotNull(message = "参数不能为空") String filename) {
    File file = new File(projectGeneratorStorage + File.separator + filename + ".zip");
    try {
      this.outputAttachmentFile(file);
    } catch (Exception e) {
      e.printStackTrace();
      outputException(500);
    }
  }

  @ApiOperation(value = "工程代码生成")
  @PostMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public void generate(@RequestBody @NotNull(message = "参数不能为空") GeneratorInput input) {
    String yyyyMMddHHmmssSSS = DateTimeUtil.getCurrent("yyyyMMddHHmmssSSS");
    ProjectBO projectBO = new ProjectBO();
    BeanUtils.copyProperties(input, projectBO);
    projectBO.setProjectDir(projectBO.getProjectName() + "-" + yyyyMMddHHmmssSSS);
    projectBO.setExportDir(projectGeneratorStorage);
    projectBO.setProjectPort(80);
    ArchitectureBoot architectureBoot = new ArchitectureBoot();
    architectureBoot.generateProjectArchitecture(projectBO);

    DataSourceBO dataSourceBO = new DataSourceBO();
    BeanUtils.copyProperties(input, dataSourceBO);
    DataSourceTableColumn dataSourceTableColumn = new DataSourceTableColumn(dataSourceBO,
        input.getName(), input.getTables());
    Map<String, Object> exec = null;
    try {
      exec = dataSourceTableColumn.exec();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      outputException(500);
      return;
    } catch (SQLException e) {
      e.printStackTrace();
      outputException(500);
      return;
    }

    try {
      Set<String> tables = exec.keySet();
      Set<String> classNames = new LinkedHashSet<>();
      for (String table : tables) {
        String tableName = table;
        String className = VariableName
            .upperInitials(VariableName.underlineToHump(tableName));
        classNames.add(StringUtils.toLowerCaseFirstLetter(className));
        List<TableColumnBO> tableColumnBOList = (List<TableColumnBO>) exec.get(table);
        DatabaseBoot databaseBoot = new DatabaseBoot();
        databaseBoot.generateProjectSrc(projectBO, tableName, className, tableColumnBOList);
      }

      new SrcMainResourcesTemplatesIndex(projectBO, classNames).createProjectFile();

    } catch (Exception e) {
      log.error(e.getMessage());
      outputException(500);
      return;
    }

    String sourceDir = projectGeneratorStorage + File.separator + projectBO.getProjectDir();
    String fileName = projectBO.getProjectDir();
    String fullFileName = fileName + ".zip";
    String targetFile = projectGeneratorStorage + File.separator + fullFileName;
    try {
      ZipUtil.zip(sourceDir, targetFile);
    } catch (IOException e) {
      e.printStackTrace();
      outputException(500);
      return;
    }
    outputData(200, fileName);
  }
}
