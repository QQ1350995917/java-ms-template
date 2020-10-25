package pwd.initializr.common.mw.monitor.client;

import com.alibaba.fastjson.JSON;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import pwd.initializr.common.mw.monitor.MonitorClient;
import pwd.initializr.common.mw.monitor.MonitorClientConfig;
import pwd.initializr.common.mw.monitor.index.Host;
import pwd.initializr.monitor.rpc.RPCHostCpu;

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
public class OSClient extends MonitorClient {

  public OSClient(MonitorClientConfig monitorClientConfig) {
    super(monitorClientConfig);
  }

  public static void main(String[] args) throws Exception {
    new OSClient(null);
    Thread.sleep(Integer.MAX_VALUE);
  }

  @Override
  protected String getScheduleName() {
    return "Monitor-OS-Client";
  }

  @Override
  protected int getScheduleSecondRate() {
    return monitorClientConfig.getOsRateSecond();
  }

  @Override
  protected void refresh() {
    try {
      RPCHostCpu cpu = Host.cpu();
      String jsonString = JSON.toJSONString(cpu);
      httpX.putJson(monitorClientConfig.getOsUrl(), jsonString);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
