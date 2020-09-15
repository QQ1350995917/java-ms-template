package pwd.initializr.generator.business.mysql.database;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import pwd.initializr.generator.business.mysql.architecture.ProjectBO;
import pwd.initializr.generator.business.mysql.architecture.SrcMainJavaPackageApi;
import pwd.initializr.generator.business.mysql.architecture.SrcMainJavaPackageApiController;
import pwd.initializr.generator.business.mysql.architecture.SrcMainJavaPackageApiInput;
import pwd.initializr.generator.business.mysql.architecture.SrcMainJavaPackageApiOutput;
import pwd.initializr.generator.business.mysql.architecture.SrcMainJavaPackageBusinessBO;
import pwd.initializr.generator.business.mysql.architecture.SrcMainJavaPackageBusinessService;
import pwd.initializr.generator.business.mysql.architecture.SrcMainJavaPackageBusinessServiceImpl;
import pwd.initializr.generator.business.mysql.architecture.SrcMainJavaPackagePersistenceDao;
import pwd.initializr.generator.business.mysql.architecture.SrcMainJavaPackagePersistenceEntity;
import pwd.initializr.generator.business.mysql.architecture.SrcMainResourcesMapper;

/**
 * pwd.initializr.generator.business.mysql.database@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-09-10 14:51
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class DatabaseBoot {

    public static void main(String[] args) throws Exception {
        DataSourceBO dataSourceBO = new DataSourceBO();
        Set<String> tables = new HashSet<>();
        tables.add("admin_account");
        DataSourceComponent dataSourceComponent = new DataSourceTableColumn(dataSourceBO,dataSourceBO.getName(),tables);
        Map<String, Object> exec = dataSourceComponent.exec();
        List<TableColumnBO> tableColumnBOList = (List<TableColumnBO>)exec.get("admin_account");
        ProjectBO projectBO = new ProjectBO();
        String tableName = "admin_account";
        String className = "AdminAccount";

        DatabaseBoot databaseBoot = new DatabaseBoot();
        databaseBoot.generateProjectSrc(projectBO,tableName,className,tableColumnBOList);

    }

    public void generateProjectSrc(ProjectBO projectBO,String tableName,String className,List<TableColumnBO> tableColumnBOList) throws Exception {
        new SrcMainResourcesMapper(projectBO, tableName, className,
            tableColumnBOList).createProjectFile();
        new SrcMainJavaPackagePersistenceDao(projectBO, tableName, className).createProjectFile();
        new SrcMainJavaPackagePersistenceEntity(projectBO, tableName, className,
            tableColumnBOList).createProjectFile();
        new SrcMainJavaPackageBusinessBO(projectBO,className).createProjectFile();

        new SrcMainJavaPackageBusinessService(projectBO,className).createProjectFile();
        new SrcMainJavaPackageBusinessServiceImpl(projectBO,className).createProjectFile();
        new SrcMainJavaPackageApiInput(projectBO,className, tableColumnBOList).createProjectFile();
        new SrcMainJavaPackageApiOutput(projectBO,className, tableColumnBOList).createProjectFile();
        new SrcMainJavaPackageApi(projectBO,className).createProjectFile();
        new SrcMainJavaPackageApiController(projectBO,className).createProjectFile();
    }
}
