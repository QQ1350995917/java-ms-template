package pwd.initializr.monitor.rpc;

import java.io.Serializable;

/**
 * pwd.initializr.monitor.rpc@ms-web-initializr
 *
 * <h1>负载信息</h1>
 * <p>在特定时间间隔内运行队列中的平均进程数。如果一个进程满足以下条件则其就会位于运行队列中：</p>
 * <p>- 它没有在等待I/O操作的结果</p>
 * <p>- 它没有主动进入等待状态(也就是没有调用’wait’)</p>
 * <p>- 没有被停止(例如：等待终止)</p>
 *
 *
 * <p>值越大就说明服务器压力越大。一般情况下这个值只要不超过服务器的cpu数量就没有关系</p>
 *
 * date 2020-10-28 15:59
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface ILoadStat extends Serializable {


    /**
     * 逻辑组名
     */
    String getGroupName();

    /**
     * 主机名 network node hostname
     */
    String getNodeName();

    /**
     * 1分钟平均负载
     */
    String getLoadIn1m();

    /**
     * 5分钟平均负载
     */
    String getLoadIn5m();

    /**
     * 15分钟平均负载
     */
    String getLoadIn15m();

    /**
     * 1/228（分子是当前正在运行的进程数，分母是总的进程数）
     */
    String getProcessRate();

    /**
     * 最近运行进程的ID
     */
    String getLastProcessId();
}
