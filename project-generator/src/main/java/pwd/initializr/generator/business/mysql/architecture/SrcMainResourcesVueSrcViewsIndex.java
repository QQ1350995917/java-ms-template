package pwd.initializr.generator.business.mysql.architecture;

import java.io.File;
import java.util.List;
import pwd.initializr.generator.business.mysql.database.TableColumnBO;

/**
 * pwd.initializr.generator.business.mysql.architecture@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-08-22 21:12
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class SrcMainResourcesVueSrcViewsIndex extends SrcMainResourcesVue {

  public SrcMainResourcesVueSrcViewsIndex(
      ProjectBO projectBO, String tableName, String className,
      List<TableColumnBO> tableColumnBOList) {
    super(projectBO, tableName, className, tableColumnBOList);
    this.filePath = this.filePath
        + File.separator + "src"
        + File.separator + "views";
  }

  @Override
  protected String getResourceName() {
    return className + ".vue";
  }

  @Override
  protected String getTemplate() {
    return "mysql/vue/views/index.vue.ftl";
  }

}
