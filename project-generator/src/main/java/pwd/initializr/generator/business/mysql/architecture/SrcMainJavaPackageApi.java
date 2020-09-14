package pwd.initializr.generator.business.mysql.architecture;

import java.io.File;
import java.util.Map;

/**
 * pwd.initializr.generator.business.mysql.architecture@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-09-14 16:58
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class SrcMainJavaPackageApi extends SrcMainJavaPackage {

    private String className;

    public SrcMainJavaPackageApi(ProjectBO projectBO,String className) {
        super(projectBO);
        this.packagePath += File.separator + "api";
        this.className = className;
    }

    @Override
    protected String getClassName() {
        return className + "Api";
    }

    @Override
    protected Map<String, Object> getData() {
        this.data.put("className", this.className);
        return this.data;
    }

    @Override
    protected String getTemplate() {
        return "mysql/src/main/java/Api.java.ftl";
    }
}
