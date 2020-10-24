package pwd.initializr.common.mw.monitor.client;

import com.alibaba.fastjson.JSON;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import pwd.initializr.common.http.HttpX;
import pwd.initializr.common.mw.monitor.MonitorClient;
import pwd.initializr.common.mw.monitor.index.Host;
import pwd.initializr.monitor.rpc.RPCHostOS;

/**
 * pwd.initializr.common.mw.monitor.client@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-23 15:04
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Singleton
@Slf4j
public class OSClient extends MonitorClient {

    @Autowired
    private HttpX httpX;

    public static void main(String[] args) throws Exception {
        new OSClient();
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    protected void refresh() {
        try {
            RPCHostOS os = Host.os();
            String jsonString = JSON.toJSONString(os);
            httpX.putJson("http://localhost:11260/api/robot/os", jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getScheduleName() {
        return "Monitor-OS-Client";
    }

    @Override
    protected int getScheduleSecondRate() {
        return 5;
    }
}
