package pwd.initializr.generator.business.mysql.architecture;

import java.io.File;
import java.util.Map;

/**
 * pwd.initializr.automatic.business.mysql.project@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-09-09 10:37
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class SrcMainJavaPackagePersistenceDao extends SrcMainJavaPackage {

    private String tableName;
    private String className;

    public SrcMainJavaPackagePersistenceDao(ProjectBO projectBO, String tableName,
        String className) {
        super(projectBO);
        this.packagePath += File.separator + "persistence" + File.separator + "dao";
        this.tableName = tableName;
        this.className = className;
    }

    @Override
    protected Map<String, Object> getData() {
        this.data.put("className", this.className);
        this.data.put("tableName", this.tableName);
        return this.data;
    }

    @Override
    protected String getTemplate() {
        return "mysql/src/main/java/PersistenceDao.java.ftl";
    }

    @Override
    protected String getClassName() {
        return className + "Dao";
    }
}