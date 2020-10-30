package pwd.initializr.common.mw.monitor.client.win;

import com.alibaba.fastjson.JSON;
import java.util.LinkedList;
import java.util.List;
import pwd.initializr.common.mw.monitor.MonitorClient;
import pwd.initializr.common.mw.monitor.MonitorClientConfig;
import pwd.initializr.monitor.rpc.RPCHostCpuCoreStat;

/**
 * pwd.initializr.common.mw.monitor.client.win@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-29 19:46
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class HostCpuStatClientOnWin extends MonitorClient {

    public HostCpuStatClientOnWin(MonitorClientConfig monitorClientConfig) {
        super(monitorClientConfig);
    }

    public static void main(String[] args) throws Exception {
        new HostClientOnWin(null);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    protected String getScheduleName() {
        return "Monitor-CpuStat-Client";
    }

    @Override
    protected int getScheduleSecondRate() {
        return monitorClientConfig.getCpuStatRateSecond();
    }

    @Override
    protected void refresh() {
        try {
            int cores = 4;
            List<RPCHostCpuCoreStat> cpuCoreStatList = new LinkedList<>();
            for (int i = 0; i < 4; i++) {
                RPCHostCpuCoreStat rpcCpuCoreStat = new RPCHostCpuCoreStat();
                rpcCpuCoreStat.setGroupName("test-group");
                rpcCpuCoreStat.setNodeName("test-nodename");
                rpcCpuCoreStat.setUser(((Double) (Math.random() * 100)).longValue());
                rpcCpuCoreStat.setNice(((Double) (Math.random() * 100)).longValue());
                rpcCpuCoreStat.setSystem(((Double) (Math.random() * 100)).longValue());
                rpcCpuCoreStat.setIdle(((Double) (Math.random() * 100)).longValue());
                rpcCpuCoreStat.setIowait(((Double) (Math.random() * 100)).longValue());
                rpcCpuCoreStat.setIrq(((Double) (Math.random() * 100)).longValue());
                rpcCpuCoreStat.setSoftirq(((Double) (Math.random() * 100)).longValue());
                rpcCpuCoreStat.setSteal(((Double) (Math.random() * 100)).longValue());
                rpcCpuCoreStat.setGuest(((Double) (Math.random() * 100)).longValue());
                rpcCpuCoreStat.setGuestNice(((Double) (Math.random() * 100)).longValue());
                cpuCoreStatList.add(rpcCpuCoreStat);
            }
            String jsonString = JSON.toJSONString(cpuCoreStatList);
            httpX.postJson(monitorClientConfig.getCpuStatUrl(), jsonString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
