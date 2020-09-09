package pwd.initializr.generator.business.mysql.project;

import java.io.File;
import java.io.IOException;
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
public class ProjectPom extends ProjectFile {

    private Map<String, Object> data = new LinkedHashMap<>();
    private String fileDir;

    public ProjectPom(ProjectBO projectBO) {
        this.data.put("projectName", projectBO.getProjectName());
        this.data.put("projectVersion", projectBO.getProjectVersion());
        this.data.put("projectPackage", projectBO.getPackageName());
        this.data.put("applicationName", projectBO.getApplicationName());
        this.fileDir = projectBO.getExportDir() + File.separator + projectBO.getProjectName();
    }

    @Override
    protected Map<String, Object> getData() {
        return this.data;
    }

    @Override
    protected String getTemplate() {
        return "mysql/pom.xml.ftl";
    }

    @Override
    protected File getOutputFile() throws IOException {
        File fileDir = new File(this.fileDir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        String filePath = this.fileDir + File.separator + "pom.xml";
        return new File(filePath);
    }
}
