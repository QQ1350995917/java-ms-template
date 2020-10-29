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
 * date 2020-10-28 17:31
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class RPCLoadStat implements ILoadStat {
    private String groupName;
    private String nodeName;
    private String loadIn1m;
    private String loadIn5m;
    private String loadIn15m;
    private String processRate;
    private String lastProcessId;

    @Override
    public String getGroupName() {
        return groupName;
    }

    @Override
    public String getNodeName() {
        return nodeName;
    }

    @Override
    public String getLoadIn1m() {
        return loadIn1m;
    }

    @Override
    public String getLoadIn5m() {
        return loadIn5m;
    }

    @Override
    public String getLoadIn15m() {
        return loadIn15m;
    }

    @Override
    public String getProcessRate() {
        return processRate;
    }

    @Override
    public String getLastProcessId() {
        return lastProcessId;
    }
}
