package pwd.initializr.automatic.business.mysql.project;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
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
public class SrcMainJavaPackageApplication extends SrcMainJavaPackage {

    private String applicationName;
    public SrcMainJavaPackageApplication(ProjectBO projectBO) {
        super(projectBO);
        this.applicationName = projectBO.getApplicationName();
    }

    @Override
    protected Map<String, Object> getData() {
        return this.data;
    }

    @Override
    protected String getTemplate() {
        return "mysql/src/main/java/Application.java.ftl";
    }

    @Override
    protected String getClassName() {
        return applicationName;
    }
}
