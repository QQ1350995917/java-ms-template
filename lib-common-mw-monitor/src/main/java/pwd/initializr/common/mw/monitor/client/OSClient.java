package pwd.initializr.common.mw.monitor.client;

import com.alibaba.fastjson.JSON;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import pwd.initializr.common.http.Post;
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

    @Override
    protected void refresh() {
        System.out.println(System.currentTimeMillis());
        try {
            RPCHostOS os = Host.os();
            String jsonString = JSON.toJSONString(os);
            Post.doPost("http://localhost:11260", "/api/robot/os", jsonString, 1000, 1000, 1000);
            Thread.sleep(5000);
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    @Override
    protected String getScheduleName() {
        return "Monitor-OS-Client";
    }

    @Override
    protected int getScheduleMillisecondTimeout() {
        return 1000;
    }

    @Override
    protected int getScheduleSecondRate() {
        return 5;
    }

    public static void main(String[] args) throws Exception {
        new OSClient();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
