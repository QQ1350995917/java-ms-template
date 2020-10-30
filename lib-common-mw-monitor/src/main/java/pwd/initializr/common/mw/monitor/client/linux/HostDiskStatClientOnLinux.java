package pwd.initializr.common.mw.monitor.client.linux;

import com.alibaba.fastjson.JSON;
import java.util.List;
import pwd.initializr.common.mw.monitor.MonitorClient;
import pwd.initializr.common.mw.monitor.MonitorClientConfig;
import pwd.initializr.common.mw.monitor.client.win.HostClientOnWin;
import pwd.initializr.common.mw.monitor.index.MonitorByShellOnLinux;
import pwd.initializr.monitor.rpc.IHostDiskStat;

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
public class HostDiskStatClientOnLinux extends MonitorClient {

  public HostDiskStatClientOnLinux(MonitorClientConfig monitorClientConfig) {
    super(monitorClientConfig);
  }

  public static void main(String[] args) throws Exception {
    new HostClientOnWin(null);
    Thread.sleep(Integer.MAX_VALUE);
  }

  @Override
  protected String getScheduleName() {
    return "Monitor-DiskStat-Client";
  }

  @Override
  protected int getScheduleSecondRate() {
    return monitorClientConfig.getDiskStatRateSecond();
  }

  @Override
  protected void refresh() {
    try {
      MonitorByShellOnLinux monitorByShellOnLinux = new MonitorByShellOnLinux();
      List<IHostDiskStat> diskStat = monitorByShellOnLinux.getDiskStat();
      String jsonString = JSON.toJSONString(diskStat);
      httpX.postJson(monitorClientConfig.getDiskStatUrl(), jsonString);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


}
