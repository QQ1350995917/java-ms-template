package pwd.initializr.common.mw.monitor.client;

import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import pwd.initializr.common.mw.monitor.MonitorClient;

/**
 * pwd.initializr.common.mw.monitor.client@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-23 17:05
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Singleton
@Slf4j
public class CpuClient extends MonitorClient {

    @Override
    protected void refresh() {

    }

    @Override
    protected String getScheduleName() {
        return null;
    }

    @Override
    protected int getScheduleSecondRate() {
        return 0;
    }
}
