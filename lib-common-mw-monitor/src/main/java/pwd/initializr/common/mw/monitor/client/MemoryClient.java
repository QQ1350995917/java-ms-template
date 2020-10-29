package pwd.initializr.common.mw.monitor.client;

import pwd.initializr.common.mw.monitor.MonitorClient;
import pwd.initializr.common.mw.monitor.MonitorClientConfig;

/**
 * pwd.initializr.common.mw.monitor.client@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-25 13:59
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class MemoryClient extends MonitorClient {

  public MemoryClient(MonitorClientConfig monitorClientConfig) {
    super(monitorClientConfig);
  }

  @Override
  protected String getScheduleName() {
    return "Monitor-Memory-Client";
  }

  @Override
  protected int getScheduleSecondRate() {
    return monitorClientConfig.getMemoryRateSecond();
  }

  @Override
  protected void refresh() {
    try {
//      RPCHostMemory memory = MonitorBySigar.memory();
//      String jsonString = JSON.toJSONString(memory);
//      httpX.postJson(monitorClientConfig.getMemoryUrl(), jsonString);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
