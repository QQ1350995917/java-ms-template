package pwd.initializr.generator.business.mysql.architecture;

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
public class SrcMainResourcesApplicationDev extends SrcMainResources {

    public SrcMainResourcesApplicationDev(ProjectBO projectBO) {
        super(projectBO);
        this.data.put("projectPort",projectBO.getProjectPort());
        this.data.put("databaseName",projectBO.getName());
        this.data.put("databaseUrl",projectBO.getUrl());
        this.data.put("databaseUser",projectBO.getUser());
        this.data.put("databasePwd",projectBO.getPwd());
        this.data.put("databaseDriver",projectBO.getDriver());
    }

    @Override
    protected Map<String, Object> getData() {
        return this.data;
    }

    @Override
    protected String getTemplate() {
        return "mysql/src/main/resources/application-dev.properties.ftl";
    }

    @Override
    protected String getResourceName(){
        return "application-dev.properties";
    }

}
