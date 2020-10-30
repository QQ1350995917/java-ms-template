package pwd.initializr.common.mw.monitor.client.win;

import com.alibaba.fastjson.JSON;
import java.text.DecimalFormat;
import pwd.initializr.common.mw.monitor.MonitorClient;
import pwd.initializr.common.mw.monitor.MonitorClientConfig;
import pwd.initializr.monitor.rpc.RPCHostLoadStat;

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
public class HostLoadStatClientOnWin extends MonitorClient {

    static DecimalFormat decimalFormat = new DecimalFormat("######0.00");

    public HostLoadStatClientOnWin(MonitorClientConfig monitorClientConfig) {
        super(monitorClientConfig);
    }

    public static void main(String[] args) throws Exception {
        new HostClientOnWin(null);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    protected String getScheduleName() {
        return "Monitor-LoadStat-Client";
    }

    @Override
    protected int getScheduleSecondRate() {
        return monitorClientConfig.getLoadStatRateSecond();
    }

    @Override
    protected void refresh() {
        try {
            int num0 = (int) (Math.random() * 10) + 1;
            int num1 = (int) (Math.random() * 100) + 10;
            int num3 = (int) (Math.random() * 10000);

            RPCHostLoadStat rpcHostLoadStat = new RPCHostLoadStat();
            rpcHostLoadStat.setGroupName("test-group");
            rpcHostLoadStat.setNodeName("test-nodename");
            rpcHostLoadStat.setLoadIn1m(decimalFormat.format(Math.random()));
            rpcHostLoadStat.setLoadIn5m(decimalFormat.format(Math.random()));
            rpcHostLoadStat.setLoadIn15m(decimalFormat.format(Math.random()));
            rpcHostLoadStat.setProcessRate(num0 + "/" + num1);
            rpcHostLoadStat.setLastProcessId(num3 + "");
            String jsonString = JSON.toJSONString(rpcHostLoadStat);
            httpX.postJson(monitorClientConfig.getLoadStatUrl(), jsonString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
