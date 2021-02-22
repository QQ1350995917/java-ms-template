package pwd.initializr.generator.business.mysql.architecture;

import java.io.File;

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
public abstract class SrcMainResourcesTemplate extends SrcMainResources {

  public SrcMainResourcesTemplate(ProjectBO projectBO) {
    super(projectBO);
    this.filePath = this.fileDir
        + File.separator + "src"
        + File.separator + "main"
        + File.separator + "resources"
        + File.separator + "templates"
    ;
  }
}
