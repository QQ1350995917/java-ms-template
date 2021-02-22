package pwd.initializr.generator.business.mysql.architecture;

import java.util.Map;
import java.util.Set;

/**
 * pwd.initializr.generator.business.mysql.architecture@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-20 17:43
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class SrcMainResourcesTemplatesIndex extends SrcMainResourcesTemplate {

  private Set<String> classNames;

  public SrcMainResourcesTemplatesIndex(
      ProjectBO projectBO, Set<String> classNames) {
    super(projectBO);
    this.classNames = classNames;
  }

  @Override
  protected String getResourceName() {
    return "index.html";
  }

  @Override
  protected String getTemplate() {
    return "mysql/src/main/resources/templates/index.html.ftl";
  }

  @Override
  protected Map<String, Object> getData() {
    this.data.put("classNames", this.classNames);
    return this.data;
  }


}
