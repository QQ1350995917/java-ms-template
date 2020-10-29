package pwd.initializr.common.mw.monitor.client.linux;

import com.alibaba.fastjson.JSON;
import java.util.List;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import pwd.initializr.common.mw.monitor.MonitorClient;
import pwd.initializr.common.mw.monitor.MonitorClientConfig;
import pwd.initializr.common.mw.monitor.index.MonitorByShellOnLinux;
import pwd.initializr.monitor.rpc.ICpuCore;
import pwd.initializr.monitor.rpc.IHost;

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
public class HostCpuClientOnLinux extends MonitorClient {

  public HostCpuClientOnLinux(MonitorClientConfig monitorClientConfig) {
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
        MonitorByShellOnLinux monitorByShellOnLinux = new MonitorByShellOnLinux();
        List<ICpuCore> cpuCore = monitorByShellOnLinux.getCpuCore();
        String jsonString = JSON.toJSONString(cpuCore);
        httpX.putJson(monitorClientConfig.getCpuUrl(), jsonString);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
