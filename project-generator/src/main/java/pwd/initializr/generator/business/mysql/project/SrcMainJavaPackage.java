package pwd.initializr.generator.business.mysql.project;

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
public abstract class SrcMainJavaPackage extends ProjectFile {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm");
    protected String packagePath;
    protected Map<String, Object> data = new LinkedHashMap<>();

    public SrcMainJavaPackage(ProjectBO projectBO) {
        this.data.put("projectPackage", projectBO.getPackageName());
        this.data.put("projectName", projectBO.getProjectName());
        this.data.put("projectVersion", projectBO.getProjectVersion());
        this.data.put("projectCreateDate", simpleDateFormat.format(new Date()));
        this.data.put("applicationName", projectBO.getApplicationName());

        this.packagePath = projectBO.getExportDir()
            + File.separator + projectBO.getProjectName()
            + File.separator + "src"
            + File.separator + "main"
            + File.separator + "java"
        ;

        String packageName = projectBO.getPackageName();
        String[] split = packageName.split("\\.");
        for (String s : split) {
            this.packagePath += File.separator + s;
        }
    }

    @Override
    protected File getOutputFile() throws IOException {
        File fileDir = new File(this.packagePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        String filePath = this.packagePath + File.separator + getClassName() + ".java";
        return new File(filePath);
    }

    protected abstract String getClassName();
}
