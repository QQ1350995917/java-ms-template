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
 * date 2021-08-22 21:10
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public abstract class SrcMainResourcesVue extends SrcMainResources {

  private String tableName;
  private String className;
  private String apiPath;
  private List<TableColumnBO> tableColumnBOList;

  public SrcMainResourcesVue(ProjectBO projectBO,String tableName,
      String className, List<TableColumnBO> tableColumnBOList) {
    super(projectBO);
    this.filePath = this.filePath
        + File.separator + "vue";
    this.tableName = tableName;
    this.className = className;
    this.tableColumnBOList = tableColumnBOList;

  }

  @Override
  protected Map<String, Object> getData() {
    this.data.put("serviceName","access");
    this.data.put("className", this.className);
    this.data.put("tableName", this.tableName);
    // TODO 所有的可展示项
    this.data.put("columns", tableColumnBOList);
    return this.data;
  }

}
