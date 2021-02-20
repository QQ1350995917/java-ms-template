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
public class SrcMainResourcesTemplatesCss extends SrcMainResourcesTemplate {

    public SrcMainResourcesTemplatesCss(ProjectBO projectBO) {
        super(projectBO);
        this.filePath += File.separator + "static" + File.separator + "css";
    }

    @Override
    protected Map<String, Object> getData() {
        return this.data;
    }

    @Override
    protected String getTemplate() {
        return "mysql/src/main/resources/templates/static/css/template.css.ftl";
    }

    @Override
    protected String getResourceName(){
        return "template.css";
    }
}