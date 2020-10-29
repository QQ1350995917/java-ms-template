package pwd.initializr.common.mw.monitor.client.linux;

import pwd.initializr.common.mw.monitor.MonitorClient;
import pwd.initializr.common.mw.monitor.MonitorClientConfig;

/**
 * pwd.initializr.common.mw.monitor.client@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-25 13:57
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class CpuCoreUsageClient extends MonitorClient {

  public CpuCoreUsageClient(MonitorClientConfig monitorClientConfig) {
    super(monitorClientConfig);
  }

  @Override
  protected String getScheduleName() {
    return "Monitor-CPU-Core-Usage-Client";
  }

  @Override
  protected int getScheduleSecondRate() {
    return monitorClientConfig.getCpuCoreUsageRateSecond();
  }

  @Override
  protected void refresh() {
    try {
//      LinkedList<RPCHostCpuCoreUsage> rpcHostCpuCoreUsages = MonitorBySigar.cpuCoreUsage();
//      String jsonString = JSON.toJSONString(rpcHostCpuCoreUsages);
//      httpX.postJson(monitorClientConfig.getCpuCoreUsageUrl(), jsonString);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
