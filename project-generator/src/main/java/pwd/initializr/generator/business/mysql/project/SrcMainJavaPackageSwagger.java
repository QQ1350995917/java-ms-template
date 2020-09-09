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
public class SrcMainJavaPackageSwagger extends SrcMainJavaPackage {

    public SrcMainJavaPackageSwagger(ProjectBO projectBO) {
        super(projectBO);
        this.packagePath += File.separator + "api";
    }

    @Override
    protected String getClassName() {
        return "Swagger2";
    }

    @Override
    protected Map<String, Object> getData() {
        return this.data;
    }

    @Override
    protected String getTemplate() {
        return "mysql/src/main/java/Swagger2.java.ftl";
    }
}
