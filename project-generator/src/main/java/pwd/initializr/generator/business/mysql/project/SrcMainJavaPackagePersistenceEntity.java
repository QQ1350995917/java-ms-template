package pwd.initializr.generator.business.mysql.project;

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
public class SrcMainJavaPackagePersistenceEntity extends SrcMainJavaPackage {

    private String className;
    private String tableName;
    public SrcMainJavaPackagePersistenceEntity(ProjectBO projectBO) {
        super(projectBO);
        this.packagePath += File.separator + "persistence" + File.separator + "entity";
        this.className = "TestClass";
        this.tableName = "test_class";
    }

    @Override
    protected Map<String, Object> getData() {
        this.data.put("className",this.className);
        this.data.put("tableName",this.tableName);
        return this.data;
    }

    @Override
    protected String getTemplate() {
        return "mysql/src/main/java/Entity.java.ftl";
    }

    @Override
    protected String getClassName() {
        return className + "Entity";
    }
}
