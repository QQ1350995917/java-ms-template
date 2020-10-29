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
public class MemorySwapClient extends MonitorClient {

  public MemorySwapClient(MonitorClientConfig monitorClientConfig) {
    super(monitorClientConfig);
  }

  @Override
  protected String getScheduleName() {
    return "Monitor-Memory-Swap-Client";
  }

  @Override
  protected int getScheduleSecondRate() {
    return monitorClientConfig.getMemorySwapRateSecond();
  }

  @Override
  protected void refresh() {
    try {
//      RPCHostMemorySwap rpcHostMemorySwap = MonitorBySigar.memorySwap();
//      String jsonString = JSON.toJSONString(rpcHostMemorySwap);
//      httpX.postJson(monitorClientConfig.getMemorySwapUrl(), jsonString);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
