package pwd.initializr.common.mw.monitor.client.linux;

import com.alibaba.fastjson.JSON;
import pwd.initializr.common.mw.monitor.MonitorClient;
import pwd.initializr.common.mw.monitor.MonitorClientConfig;
import pwd.initializr.common.mw.monitor.client.win.HostClientOnWin;
import pwd.initializr.common.mw.monitor.index.MonitorByShellOnLinux;
import pwd.initializr.monitor.rpc.IHostMemoryStat;

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
public class HostMemoryStatClientOnLinux extends MonitorClient {

    public HostMemoryStatClientOnLinux(MonitorClientConfig monitorClientConfig) {
        super(monitorClientConfig);
    }

    public static void main(String[] args) throws Exception {
        new HostClientOnWin(null);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    protected String getScheduleName() {
        return "Monitor-MemoryStat-Client";
    }

    @Override
    protected int getScheduleSecondRate() {
        return monitorClientConfig.getMemoryStatRateSecond();
    }

    @Override
    protected void refresh() {
        try {
            MonitorByShellOnLinux monitorByShellOnLinux = new MonitorByShellOnLinux();
            IHostMemoryStat memoryStat = monitorByShellOnLinux.getMemoryStat();
            String jsonString = JSON.toJSONString(memoryStat);
            httpX.postJson(monitorClientConfig.getMemoryStatUrl(), jsonString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
