package pwd.initializr.common.mw.monitor.client;

import com.alibaba.fastjson.JSON;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import pwd.initializr.common.mw.monitor.MonitorClient;
import pwd.initializr.common.mw.monitor.MonitorClientConfig;
import pwd.initializr.common.mw.monitor.index.Host;
import pwd.initializr.monitor.rpc.RPCHostEthernetStat;

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
public class EthernetStatClient extends MonitorClient {

  public EthernetStatClient(MonitorClientConfig monitorClientConfig) {
    super(monitorClientConfig);
  }

  @Override
  protected String getScheduleName() {
    return "Monitor-Ethernet-Stat-Client";
  }

  @Override
  protected int getScheduleSecondRate() {
    return monitorClientConfig.getEthernetStatRateSecond();
  }

  @Override
  protected void refresh() {
    try {
      List<RPCHostEthernetStat> rpcHostEthernetStats = Host.ethernetStat();
      for (Iterator<RPCHostEthernetStat> it = rpcHostEthernetStats.iterator(); it.hasNext();) {
        RPCHostEthernetStat next = it.next();
        if (StringUtils.isBlank(next.getId()) || StringUtils.isBlank(next.getHwaddr())){
          it.remove();
        }
      }
      String jsonString = JSON.toJSONString(rpcHostEthernetStats);
      httpX.postJson(monitorClientConfig.getEthernetStatUrl(), jsonString);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
