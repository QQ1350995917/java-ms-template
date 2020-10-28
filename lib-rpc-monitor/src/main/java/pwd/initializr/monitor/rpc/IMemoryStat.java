package pwd.initializr.monitor.rpc;

import java.io.Serializable;

/**
 * pwd.initializr.monitor.rpc@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-28 18:36
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface IMemoryStat extends Serializable {

    long getMemTotal();

    long getMemFree();

    long getMemAvailable();

    long getBuffers();

    long getCached();

    long getSwapCached();

    long getActive();

    long getInactive();

    long getActiveAnon();

    long getInactiveAnon();

    long getActiveFile();

    long getInactiveFile();

    long getUnevictable();

    long getMlocked();

    long getSwapTotal();

    long getSwapFree();

    long getDirty();

    long getWriteback();

    long getAnonPages();

    long getMapped();

    long getShmem();

    long getSlab();

    long getSReclaimable();

    long getSUnreclaim();

    long getKernelStack();

    long getPageTables();

    long getNFSUnstable();

    long getBounce();

    long getWritebackTmp();

    long getCommitLimit();

    long getCommittedAS();

    long getVmallocTotal();

    long getVmallocUsed();

    long getVmallocChunk();

    long getHardwareCorrupted();

    long getAnonHugePages();

    long getHugePagesTotal();

    long getHugePagesFree();

    long getHugePagesRsvd();

    long getHugePagesSurp();

    long getHugePagesize();

    long getDirectMap4k();

    long getDirectMap2M();

}
