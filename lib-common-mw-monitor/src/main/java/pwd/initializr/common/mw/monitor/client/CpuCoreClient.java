package pwd.initializr.common.mw.monitor.client;

import com.alibaba.fastjson.JSON;
import java.util.LinkedList;
import pwd.initializr.common.mw.monitor.MonitorClient;
import pwd.initializr.common.mw.monitor.MonitorClientConfig;
import pwd.initializr.common.mw.monitor.index.Host;
import pwd.initializr.monitor.rpc.RPCHostCpuCore;

/**
 * pwd.initializr.common.mw.monitor.client@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-25 13:56
 *
 * @author DingPengwei[www¡.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class CpuCoreClient extends MonitorClient {

  public CpuCoreClient(MonitorClientConfig monitorClientConfig) {
    super(monitorClientConfig);
  }

  @Override
  protected String getScheduleName() {
    return "Monitor-CPU-Core-Client";
  }

  @Override
  protected int getScheduleSecondRate() {
    return monitorClientConfig.getCpuCoreRateSecond();
  }

  @Override
  protected void refresh() {
    try {
      LinkedList<RPCHostCpuCore> rpcHostCpuCores = Host.cpuCore();
      String jsonString = JSON.toJSONString(rpcHostCpuCores);
      httpX.putJson(monitorClientConfig.getCpuCoreUrl(), jsonString);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
