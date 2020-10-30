package pwd.initializr.common.mw.monitor.client.win;

import com.alibaba.fastjson.JSON;
import java.text.DecimalFormat;
import java.util.LinkedList;
import pwd.initializr.common.mw.monitor.MonitorClient;
import pwd.initializr.common.mw.monitor.MonitorClientConfig;
import pwd.initializr.monitor.rpc.RPCHostLoadStat;
import pwd.initializr.monitor.rpc.RPCHostLoggedStat;

/**
 * pwd.initializr.common.mw.monitor.client.win@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-30 10:52
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class HostLoggedStatClientOnWin extends MonitorClient {

    public HostLoggedStatClientOnWin(MonitorClientConfig monitorClientConfig) {
        super(monitorClientConfig);
    }

    public static void main(String[] args) throws Exception {
        new HostClientOnWin(null);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    protected String getScheduleName() {
        return "Monitor-LoggedStat-Client";
    }

    @Override
    protected int getScheduleSecondRate() {
        return monitorClientConfig.getLoggedStatRateSecond();
    }

    @Override
    protected void refresh() {
        try {
            int users = (int) (Math.random() * 5) + 1;
            LinkedList<RPCHostLoggedStat> rpcHostLoggedStats = new LinkedList<>();
            for (int i = 0; i < users; i++) {
                RPCHostLoggedStat rpcHostLoggedStat = new RPCHostLoggedStat();
                rpcHostLoggedStat.setGroupName("test-group");
                rpcHostLoggedStat.setNodeName("test-nodename");
                rpcHostLoggedStat.setUser("root");
                rpcHostLoggedStat.setTty("pts/" + i);
                rpcHostLoggedStat.setFrom("192.168.10." + i);
                rpcHostLoggedStat.setLogin("10:" + i);
                rpcHostLoggedStat.setIdle( i + "s");
                rpcHostLoggedStat.setJcpu( i + "s");
                rpcHostLoggedStat.setPcpu(i + "s");
                rpcHostLoggedStat.setWhat("w");
                rpcHostLoggedStats.add(rpcHostLoggedStat);
            }
            String jsonString = JSON.toJSONString(rpcHostLoggedStats);
            httpX.postJson(monitorClientConfig.getLoggedStatUrl(), jsonString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
