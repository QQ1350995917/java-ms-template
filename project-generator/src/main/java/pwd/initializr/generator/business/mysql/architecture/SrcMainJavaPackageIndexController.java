package pwd.initializr.generator.business.mysql.architecture;

import java.io.File;
import java.util.Map;

/**
 * pwd.initializr.generator.business.mysql.architecture@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-20 15:51
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class SrcMainJavaPackageIndexController extends SrcMainJavaPackage {

  public SrcMainJavaPackageIndexController(
      ProjectBO projectBO) {
    super(projectBO);
    this.packagePath += File.separator + "api";
  }

  @Override
  protected String getClassName() {
    return "IndexController";
  }

  @Override
  protected String getTemplate() {
    return "mysql/src/main/java/IndexController.java.ftl";
  }

  @Override
  protected Map<String, Object> getData() {
    return this.data;
  }
}
