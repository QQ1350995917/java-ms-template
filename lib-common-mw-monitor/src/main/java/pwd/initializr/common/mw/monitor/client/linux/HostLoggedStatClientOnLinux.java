package pwd.initializr.common.mw.monitor.client.linux;

import com.alibaba.fastjson.JSON;
import java.util.List;
import pwd.initializr.common.mw.monitor.MonitorClient;
import pwd.initializr.common.mw.monitor.MonitorClientConfig;
import pwd.initializr.common.mw.monitor.client.win.HostClientOnWin;
import pwd.initializr.common.mw.monitor.index.MonitorByShellOnLinux;
import pwd.initializr.monitor.rpc.IHostLoggedStat;

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
public class HostLoggedStatClientOnLinux extends MonitorClient {

    public HostLoggedStatClientOnLinux(MonitorClientConfig monitorClientConfig) {
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
            MonitorByShellOnLinux monitorByShellOnLinux = new MonitorByShellOnLinux();
            List<IHostLoggedStat> loggedStat = monitorByShellOnLinux.getLoggedStat();
            String jsonString = JSON.toJSONString(loggedStat);
            httpX.postJson(monitorClientConfig.getLoggedStatUrl(), jsonString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
