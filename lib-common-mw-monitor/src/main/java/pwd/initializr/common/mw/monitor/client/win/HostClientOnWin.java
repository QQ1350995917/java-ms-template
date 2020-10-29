package pwd.initializr.common.mw.monitor.client.win;

import com.alibaba.fastjson.JSON;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import pwd.initializr.common.mw.monitor.MonitorClient;
import pwd.initializr.common.mw.monitor.MonitorClientConfig;
import pwd.initializr.common.mw.monitor.index.MonitorByShellOnLinux;
import pwd.initializr.monitor.rpc.IHost;
import pwd.initializr.monitor.rpc.RPCHost;

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
public class HostClientOnWin extends MonitorClient {

  public HostClientOnWin(MonitorClientConfig monitorClientConfig) {
    super(monitorClientConfig);
  }

  public static void main(String[] args) throws Exception {
    new HostClientOnWin(null);
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
      RPCHost host = new RPCHost();
      host.setGroupName("test-group");
      host.setNodeName("test-nodename");
      host.setOperatingSystem("windows 10");
      host.setHardwarePlatform("x86_64");
      host.setSystemUpSince("2020-02-02 02:02:02");
      host.setKernelName("windows");
      host.setKernelVersion("10");
      host.setKernelRelease("windows 10");
      host.setMachine("x86_64");
      host.setProcessor("");
      String jsonString = JSON.toJSONString(host);
      httpX.putJson(monitorClientConfig.getHostUrl(), jsonString);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
