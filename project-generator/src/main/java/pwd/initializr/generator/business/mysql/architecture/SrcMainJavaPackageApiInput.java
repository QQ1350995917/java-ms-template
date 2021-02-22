package pwd.initializr.generator.business.mysql.architecture;

import java.io.File;
import java.util.List;
import java.util.Map;
import pwd.initializr.generator.business.mysql.database.TableColumnBO;

/**
 * pwd.initializr.generator.business.mysql.architecture@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-09-11 17:52
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class SrcMainJavaPackageApiInput extends SrcMainJavaPackage {

  private String className;
  private List<TableColumnBO> tableColumnBOList;

  public SrcMainJavaPackageApiInput(ProjectBO projectBO, String className,
      List<TableColumnBO> tableColumnBOList) {
    super(projectBO);
    this.packagePath += File.separator + "api" + File.separator + "vo";
    this.className = className;
    this.tableColumnBOList = tableColumnBOList;
  }

  @Override
  protected String getClassName() {
    return className + "Input";
  }

  @Override
  protected String getTemplate() {
    return "mysql/src/main/java/ApiInput.java.ftl";
  }

  @Override
  protected Map<String, Object> getData() {
    this.data.put("className", this.className);
    this.data.put("columns", this.tableColumnBOList);
    return this.data;
  }
}
