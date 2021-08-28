package pwd.initializr.generator.business.mysql.architecture;

import java.io.File;
import java.util.List;
import pwd.initializr.generator.business.mysql.database.TableColumnBO;

/**
 * pwd.initializr.generator.business.mysql.architecture@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-08-22 21:26
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class SrcMainResourcesVueSrcApiIndex extends SrcMainResourcesVue {

  public SrcMainResourcesVueSrcApiIndex(
      ProjectBO projectBO, String tableName, String className,
      List<TableColumnBO> tableColumnBOList) {
    super(projectBO, tableName, className, tableColumnBOList);
    this.filePath = this.filePath
        + File.separator + "src"
        + File.separator + "api";
  }

  @Override
  protected String getResourceName() {
    return "index.js";
  }

  @Override
  protected String getTemplate() {
    return "mysql/vue/api/index.js.ftl";
  }


}
