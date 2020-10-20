package pwd.initializr.common.mw.monitor;

import java.util.Map;

/**
 * pwd.initializr.common.mw.monitor@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-20 20:17
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface MonitorClientConfig {

  String getUrl();

  Map<String,String> getHeaders();

}
