package pwd.initializr.common.mw.monitor.client.win;

import com.alibaba.fastjson.JSON;
import java.util.LinkedList;
import pwd.initializr.common.mw.monitor.MonitorClient;
import pwd.initializr.common.mw.monitor.MonitorClientConfig;
import pwd.initializr.monitor.rpc.RPCHostEthernetStat;

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
public class HostEthernetStatClientOnWin extends MonitorClient {

  public HostEthernetStatClientOnWin(MonitorClientConfig monitorClientConfig) {
    super(monitorClientConfig);
  }

  public static void main(String[] args) throws Exception {
    new HostClientOnWin(null);
    Thread.sleep(Integer.MAX_VALUE);
  }

  @Override
  protected String getScheduleName() {
    return "Monitor-EthernetStat-Client";
  }

  @Override
  protected int getScheduleSecondRate() {
    return monitorClientConfig.getEthernetStatRateSecond();
  }

  @Override
  protected void refresh() {
    try {
      int ethernet = 4;
      LinkedList<RPCHostEthernetStat> rpcEthernetStats = new LinkedList<>();
      for (int i = 0; i < ethernet; i++) {
        RPCHostEthernetStat rpcEthernetStat = new RPCHostEthernetStat();
        rpcEthernetStat.setGroupName("test-group");
        rpcEthernetStat.setNodeName("test-nodename");
        rpcEthernetStat.setInterFace("eth" + i);
        rpcEthernetStat.setReceiveBytes(((Double) (Math.random() * 100)).longValue());
        rpcEthernetStat.setReceivePackets(((Double) (Math.random() * 100)).longValue());
        rpcEthernetStat.setReceiveErrs(((Double) (Math.random() * 100)).longValue());
        rpcEthernetStat.setReceiveDrop(((Double) (Math.random() * 100)).longValue());
        rpcEthernetStat.setReceiveFifo(((Double) (Math.random() * 100)).longValue());
        rpcEthernetStat.setReceiveFrame(((Double) (Math.random() * 100)).longValue());
        rpcEthernetStat.setTransmitBytes(((Double) (Math.random() * 100)).longValue());
        rpcEthernetStat.setTransmitPackets(((Double) (Math.random() * 100)).longValue());
        rpcEthernetStat.setTransmitErrs(((Double) (Math.random() * 100)).longValue());
        rpcEthernetStat.setTransmitDrop(((Double) (Math.random() * 100)).longValue());
        rpcEthernetStat.setTransmitFifo(((Double) (Math.random() * 100)).longValue());
        rpcEthernetStats.add(rpcEthernetStat);
      }
      String jsonString = JSON.toJSONString(rpcEthernetStats);
      httpX.postJson(monitorClientConfig.getEthernetStatUrl(), jsonString);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


}
