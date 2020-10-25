package pwd.initializr.common.mw.monitor.client;

import com.alibaba.fastjson.JSON;
import java.util.List;
import pwd.initializr.common.mw.monitor.MonitorClient;
import pwd.initializr.common.mw.monitor.MonitorClientConfig;
import pwd.initializr.common.mw.monitor.index.Host;
import pwd.initializr.monitor.rpc.RPCHostEthernet;

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
public class EthernetClient extends MonitorClient {

  public EthernetClient(MonitorClientConfig monitorClientConfig) {
    super(monitorClientConfig);
  }

  @Override
  protected String getScheduleName() {
    return "Monitor-Ethernet-Client";
  }

  @Override
  protected int getScheduleSecondRate() {
    return monitorClientConfig.getEthernetRateSecond();
  }

  @Override
  protected void refresh() {
    try {
      List<RPCHostEthernet> ethernet = Host.ethernet();
      String jsonString = JSON.toJSONString(ethernet);
      httpX.putJson(monitorClientConfig.getEthernetUrl(), jsonString);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}