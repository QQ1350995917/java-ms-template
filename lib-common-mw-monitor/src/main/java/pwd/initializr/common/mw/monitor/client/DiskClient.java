package pwd.initializr.common.mw.monitor.client;

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
public class DiskClient extends MonitorClient {

  public DiskClient(MonitorClientConfig monitorClientConfig) {
    super(monitorClientConfig);
  }

  @Override
  protected String getScheduleName() {
    return "Monitor-Disk-Client";
  }

  @Override
  protected int getScheduleSecondRate() {
    return monitorClientConfig.getDiskRateSecond();
  }

  @Override
  protected void refresh() {
    try {
//      List<RPCHostDisk> disk = MonitorBySigar.disk();
//      String jsonString = JSON.toJSONString(disk);
//      httpX.putJson(monitorClientConfig.getDiskUrl(), jsonString);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
