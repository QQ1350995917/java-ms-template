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
 * date 2020-10-28 18:18
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class RPCHostCpuCoreStat implements IHostCpuCoreStat {

    private String groupName;
    private String nodeName;
    private String cpuCoreName;
    private long user;
    private long nice;
    private long system;
    private long idle;
    private long iowait;
    private long irq;
    private long softirq;
    private long steal;
    private long guest;
    private long guestNice;

    @Override
    public String getCpuCoreName() {
        return cpuCoreName;
    }

    @Override
    public long getUser() {
        return user;
    }

    @Override
    public long getNice() {
        return nice;
    }

    @Override
    public long getSystem() {
        return system;
    }

    @Override
    public long getIdle() {
        return idle;
    }

    @Override
    public long getIowait() {
        return iowait;
    }

    @Override
    public long getIrq() {
        return irq;
    }

    @Override
    public long getSoftirq() {
        return softirq;
    }

    @Override
    public long getSteal() {
        return steal;
    }

    @Override
    public long getGuest() {
        return guest;
    }

    @Override
    public long getGuestNice() {
        return guestNice;
    }

    @Override
    public String getGroupName() {
        return groupName;
    }

    @Override
    public String getNodeName() {
        return nodeName;
    }
}
