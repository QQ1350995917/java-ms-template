package pwd.initializr.generator.business.mysql.architecture;

/**
 * pwd.initializr.generator.business.mysql.architecture@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-09-14 17:22
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class SrcMainJavaPackageApiController extends SrcMainJavaPackageApi {

  private String className;

  public SrcMainJavaPackageApiController(ProjectBO projectBO, String className, String apiPath) {
    super(projectBO, className, apiPath);
    this.className = className;
  }

  @Override
  protected String getClassName() {
    return className + "Controller";
  }

  @Override
  protected String getTemplate() {
    return "mysql/src/main/java/ApiController.java.ftl";
  }
}
