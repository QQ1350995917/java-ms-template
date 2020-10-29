package pwd.initializr.common.mw.monitor.client.win;

import com.alibaba.fastjson.JSON;
import java.util.LinkedList;
import java.util.List;
import net.bytebuddy.dynamic.scaffold.MethodGraph.Linked;
import pwd.initializr.common.mw.monitor.MonitorClient;
import pwd.initializr.common.mw.monitor.MonitorClientConfig;
import pwd.initializr.monitor.rpc.RPCCpuCore;

/**
 * pwd.initializr.common.mw.monitor.client.win@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-29 19:11
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class HostCpuClientOnWin extends MonitorClient {

    public HostCpuClientOnWin(MonitorClientConfig monitorClientConfig) {
        super(monitorClientConfig);
    }

    public static void main(String[] args) throws Exception {
        new HostClientOnWin(null);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    protected String getScheduleName() {
        return "Monitor-Cpu-Client";
    }

    @Override
    protected int getScheduleSecondRate() {
        return monitorClientConfig.getCpuRateSecond();
    }

    @Override
    protected void refresh() {
        try {
            int cores = 4;
            List<RPCCpuCore> cpuCoreList = new LinkedList<>();
            for (int i = 0; i < 4; i++) {
                RPCCpuCore rpcCpuCore = new RPCCpuCore();
                rpcCpuCore.setGroupName("test-group");
                rpcCpuCore.setNodeName("test-nodename");
                rpcCpuCore.setProcessor("processor");
                rpcCpuCore.setVendorId("1");
                rpcCpuCore.setCpuFamily("cpuFamily");
                rpcCpuCore.setModel("model");
                rpcCpuCore.setModelName("modelName");
                rpcCpuCore.setStepping("1");
                rpcCpuCore.setMicrocode("1");
                rpcCpuCore.setCpuMHz("120");
                rpcCpuCore.setCacheSize("1");
                rpcCpuCore.setPhysicalId("1");
                rpcCpuCore.setSiblings("1");
                rpcCpuCore.setCoreId(i + "");
                rpcCpuCore.setCpuCores(cores + "");
                rpcCpuCore.setApicid("1");
                rpcCpuCore.setInitialApicid("1");
                rpcCpuCore.setFpu("fpu");
                rpcCpuCore.setFpuException("fpuException");
                rpcCpuCore.setCpuidLevel("cpuidlevel");
                rpcCpuCore.setWp("wp");
                rpcCpuCore.setFlags("flags");
                rpcCpuCore.setBogomips("bogomips");
                rpcCpuCore.setClflushSize("1");
                rpcCpuCore.setCacheAlignment("1");
                rpcCpuCore.setAddressSizes("1");
                cpuCoreList.add(rpcCpuCore);
            }
            String jsonString = JSON.toJSONString(cpuCoreList);
            httpX.putJson(monitorClientConfig.getCpuUrl(), jsonString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
