package pwd.initializr.generator.business.mysql.architecture;

import java.io.File;
import java.util.Map;

/**
 * pwd.initializr.generator.business.mysql.project@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-09-11 16:32
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class SrcMainJavaPackageBusinessBO extends SrcMainJavaPackage {

  private String className;

  public SrcMainJavaPackageBusinessBO(ProjectBO projectBO, String className) {
    super(projectBO);
    this.packagePath += File.separator + "business" + File.separator + "bo";
    this.className = className;
  }

  @Override
  protected String getClassName() {
    return className + "BO";
  }

  @Override
  protected String getTemplate() {
    return "mysql/src/main/java/BusinessBO.java.ftl";
  }

  @Override
  protected Map<String, Object> getData() {
    this.data.put("className", this.className);
    return this.data;
  }
}
