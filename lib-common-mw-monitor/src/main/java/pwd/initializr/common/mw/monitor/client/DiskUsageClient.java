package pwd.initializr.common.mw.monitor.client;

import com.alibaba.fastjson.JSON;
import java.util.List;
import pwd.initializr.common.mw.monitor.MonitorClient;
import pwd.initializr.common.mw.monitor.MonitorClientConfig;
import pwd.initializr.common.mw.monitor.index.Host;
import pwd.initializr.monitor.rpc.RPCHostDiskUsage;

/**
 * pwd.initializr.common.mw.monitor.client@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-25 13:58
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class DiskUsageClient extends MonitorClient {

  public DiskUsageClient(MonitorClientConfig monitorClientConfig) {
    super(monitorClientConfig);
  }

  @Override
  protected String getScheduleName() {
    return "Monitor-Disk-Usage-Client";
  }

  @Override
  protected int getScheduleSecondRate() {
    return monitorClientConfig.getDiskUsageRateSecond();
  }

  @Override
  protected void refresh() {
    try {
      List<RPCHostDiskUsage> rpcHostDiskUsages = Host.diskUsage();
      String jsonString = JSON.toJSONString(rpcHostDiskUsages);
      httpX.postJson(monitorClientConfig.getDiskUsageUrl(), jsonString);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
