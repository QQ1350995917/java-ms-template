package pwd.initializr.monitor.rpc;

import java.util.List;

/**
 * pwd.initializr.monitor.rpc@ms-web-initializr
 *
 * <h1>CPU状态：cat /proc/stat</h1>
 *
 * https://man7.org/linux/man-pages/man5/proc.5.html
 *
 * CPU时间=user+system+nice+idle+iowait+irq+softirq
 *
 * 单位：jiffies
 *
 * date 2020-10-27 16:48
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface ICpu {

    List<ICpuCore> getCpuCore();

    List<ICpuStat> getCpuStat();


}
