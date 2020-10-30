package pwd.initializr.common.mw.monitor.client.win;

import com.alibaba.fastjson.JSON;
import java.util.LinkedList;
import pwd.initializr.common.mw.monitor.MonitorClient;
import pwd.initializr.common.mw.monitor.MonitorClientConfig;
import pwd.initializr.monitor.rpc.RPCHostDiskStat;

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
public class HostDiskStatClientOnWin extends MonitorClient {

  public HostDiskStatClientOnWin(MonitorClientConfig monitorClientConfig) {
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
      RPCHostDiskStat rpcDiskStat0 = new RPCHostDiskStat();
      rpcDiskStat0.setGroupName("test-group");
      rpcDiskStat0.setNodeName("test-nodename");
      rpcDiskStat0.setMajorDeviceNumber(8);
      rpcDiskStat0.setMinorDeviceNumber(0);
      rpcDiskStat0.setDeviceName("sda");
      rpcDiskStat0.setRead(17901464);
      rpcDiskStat0.setReadMerge(2276295);
      rpcDiskStat0.setReadSector(436039647);
      rpcDiskStat0.setReadSpentMilliseconds(31628468);
      rpcDiskStat0.setIoRequest(33779408);
      rpcDiskStat0.setIoSpentMilliseconds(2746750);
      rpcDiskStat0.setIoSpentAllMilliseconds(1439523404);

      RPCHostDiskStat rpcDiskStat1 = new RPCHostDiskStat();
      rpcDiskStat1.setGroupName("test-group");
      rpcDiskStat1.setNodeName("test-nodename");
      rpcDiskStat1.setMajorDeviceNumber(8);
      rpcDiskStat1.setMinorDeviceNumber(1);
      rpcDiskStat1.setDeviceName("sda1");
      rpcDiskStat1.setRead(1180);
      rpcDiskStat1.setReadMerge(3);
      rpcDiskStat1.setReadSector(58995);
      rpcDiskStat1.setReadSpentMilliseconds(2160);
      rpcDiskStat1.setIoRequest(13);
      rpcDiskStat1.setIoSpentMilliseconds(0);
      rpcDiskStat1.setIoSpentAllMilliseconds(123);

      RPCHostDiskStat rpcDiskStat2 = new RPCHostDiskStat();
      rpcDiskStat2.setGroupName("test-group");
      rpcDiskStat2.setNodeName("test-nodename");
      rpcDiskStat2.setMajorDeviceNumber(8);
      rpcDiskStat2.setMinorDeviceNumber(2);
      rpcDiskStat2.setDeviceName("sda2");
      rpcDiskStat2.setRead(17900206);
      rpcDiskStat2.setReadMerge(2276292);
      rpcDiskStat2.setReadSector(436033297);
      rpcDiskStat2.setReadSpentMilliseconds(31625254);
      rpcDiskStat2.setIoRequest(1399);
      rpcDiskStat2.setIoSpentMilliseconds(0);
      rpcDiskStat2.setIoSpentAllMilliseconds(12312);

      LinkedList<RPCHostDiskStat> rpcDiskStats = new LinkedList<>();
      rpcDiskStats.add(rpcDiskStat0);
      rpcDiskStats.add(rpcDiskStat1);
      rpcDiskStats.add(rpcDiskStat2);
      String jsonString = JSON.toJSONString(rpcDiskStats);
      httpX.postJson(monitorClientConfig.getDiskStatUrl(), jsonString);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


}
