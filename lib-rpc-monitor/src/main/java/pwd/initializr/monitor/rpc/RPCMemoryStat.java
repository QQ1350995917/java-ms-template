package pwd.initializr.monitor.rpc;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.monitor.rpc@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-28 18:39
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class RPCMemoryStat implements IMemoryStat {

    private long memTotal;
    private long memFree;
    private long memAvailable;
    private long buffers;
    private long cached;
    private long swapCached;
    private long active;
    private long inactive;
    private long activeAnon;
    private long inactiveAnon;
    private long activeFile;
    private long inactiveFile;
    private long unevictable;
    private long mlocked;
    private long swapTotal;
    private long swapFree;
    private long dirty;
    private long writeback;
    private long anonPages;
    private long mapped;
    private long shmem;
    private long slab;
    private long sReclaimable;
    private long sUnreclaim;
    private long kernelStack;
    private long pageTables;
    private long nFSUnstable;
    private long bounce;
    private long writebackTmp;
    private long commitLimit;
    private long committedAS;
    private long vmallocTotal;
    private long vmallocUsed;
    private long vmallocChunk;
    private long hardwareCorrupted;
    private long anonHugePages;
    private long hugePagesTotal;
    private long hugePagesFree;
    private long hugePagesRsvd;
    private long hugePagesSurp;
    private long hugePagesize;
    private long directMap4k;
    private long directMap2M;

    @Override
    public long getMemTotal() {
        return memTotal;
    }

    @Override
    public long getMemFree() {
        return memFree;
    }

    @Override
    public long getMemAvailable() {
        return memAvailable;
    }

    @Override
    public long getBuffers() {
        return buffers;
    }

    @Override
    public long getCached() {
        return cached;
    }

    @Override
    public long getSwapCached() {
        return swapCached;
    }

    @Override
    public long getActive() {
        return active;
    }

    @Override
    public long getInactive() {
        return inactive;
    }

    @Override
    public long getActiveAnon() {
        return activeAnon;
    }

    @Override
    public long getInactiveAnon() {
        return inactiveAnon;
    }

    @Override
    public long getActiveFile() {
        return activeFile;
    }

    @Override
    public long getInactiveFile() {
        return inactiveFile;
    }

    @Override
    public long getUnevictable() {
        return unevictable;
    }

    @Override
    public long getMlocked() {
        return mlocked;
    }

    @Override
    public long getSwapTotal() {
        return swapTotal;
    }

    @Override
    public long getSwapFree() {
        return swapFree;
    }

    @Override
    public long getDirty() {
        return dirty;
    }

    @Override
    public long getWriteback() {
        return writeback;
    }

    @Override
    public long getAnonPages() {
        return anonPages;
    }

    @Override
    public long getMapped() {
        return mapped;
    }

    @Override
    public long getShmem() {
        return shmem;
    }

    @Override
    public long getSlab() {
        return slab;
    }

    @Override
    public long getSReclaimable() {
        return sReclaimable;
    }

    @Override
    public long getSUnreclaim() {
        return sUnreclaim;
    }

    @Override
    public long getKernelStack() {
        return kernelStack;
    }

    @Override
    public long getPageTables() {
        return pageTables;
    }

    @Override
    public long getNFSUnstable() {
        return nFSUnstable;
    }

    @Override
    public long getBounce() {
        return bounce;
    }

    @Override
    public long getWritebackTmp() {
        return writebackTmp;
    }

    @Override
    public long getCommitLimit() {
        return commitLimit;
    }

    @Override
    public long getCommittedAS() {
        return committedAS;
    }

    @Override
    public long getVmallocTotal() {
        return vmallocTotal;
    }

    @Override
    public long getVmallocUsed() {
        return vmallocUsed;
    }

    @Override
    public long getVmallocChunk() {
        return vmallocChunk;
    }

    @Override
    public long getHardwareCorrupted() {
        return hardwareCorrupted;
    }

    @Override
    public long getAnonHugePages() {
        return anonHugePages;
    }

    @Override
    public long getHugePagesTotal() {
        return hugePagesTotal;
    }

    @Override
    public long getHugePagesFree() {
        return hugePagesFree;
    }

    @Override
    public long getHugePagesRsvd() {
        return hugePagesRsvd;
    }

    @Override
    public long getHugePagesSurp() {
        return hugePagesSurp;
    }

    @Override
    public long getHugePagesize() {
        return hugePagesize;
    }

    @Override
    public long getDirectMap4k() {
        return directMap4k;
    }

    @Override
    public long getDirectMap2M() {
        return directMap2M;
    }
}
