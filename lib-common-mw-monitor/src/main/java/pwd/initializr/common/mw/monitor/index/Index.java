package pwd.initializr.common.mw.monitor.index;

import java.util.List;
import pwd.initializr.monitor.rpc.ICpuCore;
import pwd.initializr.monitor.rpc.ICpuCoreStat;
import pwd.initializr.monitor.rpc.IDiskStat;
import pwd.initializr.monitor.rpc.IEthernetStat;
import pwd.initializr.monitor.rpc.IHost;
import pwd.initializr.monitor.rpc.ILoadStat;
import pwd.initializr.monitor.rpc.ILoggedStat;
import pwd.initializr.monitor.rpc.IMemoryStat;

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

    ILoadStat getLoadStat();

    List<ILoggedStat> getLoggedStat();

    List<ICpuCore> getCpuCore();

    List<ICpuCoreStat> getCpuCoreStat();

    IMemoryStat getMemoryStat();

    List<IDiskStat> getDiskStat();

    List<IEthernetStat> getEthernetStat();

}
