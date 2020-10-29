package pwd.initializr.common.mw.monitor.client.linux;

import com.alibaba.fastjson.JSON;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import pwd.initializr.common.mw.monitor.MonitorClient;
import pwd.initializr.common.mw.monitor.MonitorClientConfig;
import pwd.initializr.common.mw.monitor.index.MonitorByShellOnLinux;
import pwd.initializr.monitor.rpc.IHost;

/**
 * pwd.initializr.common.mw.monitor.client@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-23 15:04
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Singleton
@Slf4j
public class HostClientOnLinux extends MonitorClient {

  public HostClientOnLinux(MonitorClientConfig monitorClientConfig) {
    super(monitorClientConfig);
  }

  public static void main(String[] args) throws Exception {
    new HostClientOnLinux(null);
    Thread.sleep(Integer.MAX_VALUE);
  }

  @Override
  protected String getScheduleName() {
    return "Monitor-Host-Client";
  }

  @Override
  protected int getScheduleSecondRate() {
    return monitorClientConfig.getHostRateSecond();
  }

  @Override
  protected void refresh() {
    try {
      MonitorByShellOnLinux monitorByShellOnLinux = new MonitorByShellOnLinux();
      IHost host = monitorByShellOnLinux.getHost();
      String jsonString = JSON.toJSONString(host);
      httpX.putJson(monitorClientConfig.getHostUrl(), jsonString);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
