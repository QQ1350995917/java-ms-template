package pwd.initializr.generator.business.mysql.database;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import pwd.initializr.generator.business.mysql.project.ProjectBO;
import pwd.initializr.generator.business.mysql.project.SrcMainJavaPackagePersistenceEntity;

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
        DataSourceComponent dataSourceComponent = new DataSourceTableColumn(dataSourceBO,dataSourceBO.getDatabaseName(),tables);
        Map<String, Object> exec = dataSourceComponent.exec();
        List<TableColumnBO> tableColumnBOList = (List<TableColumnBO>)exec.get("admin_account");
        ProjectBO projectBO = new ProjectBO();
        String tableName = "admin_account";
        String className = "AdminAccount";
        new SrcMainJavaPackagePersistenceEntity(projectBO, tableName, className,
            tableColumnBOList).createProjectFile();
    }
}
