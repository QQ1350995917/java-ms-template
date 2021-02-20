package pwd.initializr.generator.business.mysql.architecture;

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
public class ProjectZip extends ProjectFile {

    public ProjectZip(ProjectBO projectBO) {
        super(projectBO);
    }

    @Override
    protected Map<String, Object> getData() {
        return this.data;
    }

    @Override
    protected String getTemplate() {
        return "mysql/zip.xml.ftl";
    }

    @Override
    protected File getOutputFile() throws IOException {
        File fileDir = new File(this.fileDir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        String filePath = this.fileDir + File.separator + "zip.xml";
        return new File(filePath);
    }
}
