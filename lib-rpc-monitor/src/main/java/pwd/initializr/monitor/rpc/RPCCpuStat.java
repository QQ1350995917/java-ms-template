package pwd.initializr.monitor.rpc;

import java.util.LinkedList;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.monitor.rpc@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-28 18:24
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class RPCCpuStat implements ICpuStat {

    private String groupName;
    private String nodeName;
    private ICpuCoreStat cpuStat;
    private LinkedList<ICpuCoreStat> cpuCoreStat;

    @Override
    public ICpuCoreStat getCpuStat() {
        return cpuStat;
    }

    @Override
    public LinkedList<ICpuCoreStat> getCpuCoreStat() {
        return cpuCoreStat;
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
