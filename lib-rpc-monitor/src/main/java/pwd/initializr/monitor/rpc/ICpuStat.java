package pwd.initializr.monitor.rpc;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * pwd.initializr.monitor.rpc@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-27 20:46
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface ICpuStat extends Serializable {

    /**
     * CPU总体状态
     */
    ICpuCoreStat getCpuStat();

    /**
     * CPU各内核状态
     */
    LinkedList<ICpuCoreStat> getCpuCoreStat();
}
