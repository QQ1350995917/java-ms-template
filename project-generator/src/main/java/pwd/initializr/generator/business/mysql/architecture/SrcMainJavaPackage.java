package pwd.initializr.generator.business.mysql.architecture;

import java.io.File;
import java.io.IOException;

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


  protected String packagePath;

  public SrcMainJavaPackage(ProjectBO projectBO) {
    super(projectBO);

    this.packagePath = this.fileDir
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
