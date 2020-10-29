package pwd.initializr.common.mw.monitor.client;

import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import pwd.initializr.common.mw.monitor.MonitorClient;
import pwd.initializr.common.mw.monitor.MonitorClientConfig;

/**
 * pwd.initializr.common.mw.monitor.client@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-23 17:05
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Singleton
@Slf4j
public class CpuClient extends MonitorClient {

  public CpuClient(MonitorClientConfig monitorClientConfig) {
    super(monitorClientConfig);
  }

  @Override
  protected String getScheduleName() {
    return "Monitor-CPU-Client";
  }

  @Override
  protected int getScheduleSecondRate() {
    return monitorClientConfig.getCpuRateSecond();
  }

  @Override
  protected void refresh() {
    try {
//      RPCHostCpu cpu = MonitorBySigar.cpu();
//      String jsonString = JSON.toJSONString(cpu);
//      httpX.putJson(monitorClientConfig.getCpuUrl(), jsonString);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
