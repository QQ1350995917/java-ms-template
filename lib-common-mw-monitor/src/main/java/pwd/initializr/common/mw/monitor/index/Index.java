package pwd.initializr.common.mw.monitor.index;

import java.util.List;
import pwd.initializr.monitor.rpc.IHostCpuCore;
import pwd.initializr.monitor.rpc.IHostCpuCoreStat;
import pwd.initializr.monitor.rpc.IHostDiskStat;
import pwd.initializr.monitor.rpc.IHostEthernetStat;
import pwd.initializr.monitor.rpc.IHost;
import pwd.initializr.monitor.rpc.IHostLoadStat;
import pwd.initializr.monitor.rpc.IHostLoggedStat;
import pwd.initializr.monitor.rpc.IHostMemoryStat;

/**
 * pwd.initializr.common.mw.monitor.index@ms-web-initializr
 *
 * <h1>指标采集</h1>
 * <p>https://man7.org/linux/man-pages/man5/proc.5.html</p>
 *
 * date 2020-10-27 20:43
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface Index {

    IHost getHost();

    IHostLoadStat getLoadStat();

    List<IHostLoggedStat> getLoggedStat();

    List<IHostCpuCore> getCpuCore();

    List<IHostCpuCoreStat> getCpuCoreStat();

    IHostMemoryStat getMemoryStat();

    List<IHostDiskStat> getDiskStat();

    List<IHostEthernetStat> getEthernetStat();

}
