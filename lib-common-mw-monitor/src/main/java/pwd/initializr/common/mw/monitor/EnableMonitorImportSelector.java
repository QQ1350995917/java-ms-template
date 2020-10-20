package pwd.initializr.common.mw.monitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.cloud.commons.util.SpringFactoryImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * pwd.initializr.common.mw.montor@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-19 23:07
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Order
public class EnableMonitorImportSelector extends SpringFactoryImportSelector<EnableMonitorClient> {

  @Override
  public String[] selectImports(AnnotationMetadata metadata) {
    String[] imports = super.selectImports(metadata);
    AnnotationAttributes attributes = AnnotationAttributes.fromMap(
        metadata.getAnnotationAttributes(getAnnotationClass().getName(), true));

    boolean autoRegister = attributes.getBoolean("autoMonitor");

    if (autoRegister) {
      List<String> importsList = new ArrayList<>(Arrays.asList(imports));
      importsList.add("pwd.initializr.common.mw.monitor.MonitorClientAutoConfiguration");
      imports = importsList.toArray(new String[0]);
    } else {

    }
    return imports;
  }

  @Override
  protected boolean isEnabled() {
    return this.getEnvironment().getProperty("monitor.cloud.client.enabled", Boolean.class, Boolean.TRUE);
  }

  @Override
  protected boolean hasDefaultFactory() {
    return true;
  }

  protected String getServerUrl() {
    return this.getEnvironment().getProperty("monitor.cloud.server.url", String.class, null);
  }



}
