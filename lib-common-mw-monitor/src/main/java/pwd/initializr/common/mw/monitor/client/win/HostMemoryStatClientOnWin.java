package pwd.initializr.common.mw.monitor.client.win;

import com.alibaba.fastjson.JSON;
import pwd.initializr.common.mw.monitor.MonitorClient;
import pwd.initializr.common.mw.monitor.MonitorClientConfig;
import pwd.initializr.monitor.rpc.RPCHostMemoryStat;

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
public class HostMemoryStatClientOnWin extends MonitorClient {


    public HostMemoryStatClientOnWin(MonitorClientConfig monitorClientConfig) {
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
            RPCHostMemoryStat rpcHostMemoryStat = new RPCHostMemoryStat();
            rpcHostMemoryStat.setGroupName("test-group");
            rpcHostMemoryStat.setNodeName("test-nodename");
            rpcHostMemoryStat.setMemTotal((((1000000) + 2L)));
            rpcHostMemoryStat.setMemFree(((long) ((Math.random() * 500000) + 1L)));
            rpcHostMemoryStat.setMemAvailable(((long) ((Math.random() * 500000) + 1L)));
            rpcHostMemoryStat.setBuffers(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setCached(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setSwapCached(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setActive(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setInactive(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setActiveAnon(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setInactiveAnon(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setActiveFile(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setInactiveFile(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setUnevictable(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setMlocked(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setSwapTotal(((long) ((Math.random() * 10000) + 1L)));
            rpcHostMemoryStat.setSwapFree(((long) ((Math.random() * 1000) + 1L)));
            rpcHostMemoryStat.setDirty(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setWriteback(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setAnonPages(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setMapped(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setShmem(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setSlab(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setSReclaimable(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setSUnreclaim(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setKernelStack(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setPageTables(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setNFSUnstable(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setBounce(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setWritebackTmp(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setCommitLimit(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setCommittedAS(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setVmallocTotal(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setVmallocUsed(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setVmallocChunk(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setHardwareCorrupted(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setAnonHugePages(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setHugePagesTotal(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setHugePagesFree(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setHugePagesRsvd(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setHugePagesSurp(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setHugePagesize(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setDirectMap4k(((long) ((Math.random() * 1000000) + 1L)));
            rpcHostMemoryStat.setDirectMap2M(((long) ((Math.random() * 1000000) + 1L)));
            String jsonString = JSON.toJSONString(rpcHostMemoryStat);
            httpX.postJson(monitorClientConfig.getMemoryStatUrl(), jsonString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
