package pwd.initializr.monitor.rpc;

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

    /**
     * 用户态时间（单位：jiffies）
     */
    long getUser();

    /**
     * nice值为负的进程所占用的CPU时间
     */
    long getNice();

    /**
     * 核心时间
     */
    long getSystem();

    /**
     * 除硬盘IO等待时间以外其它等待时间
     */
    long getIdle();

    /**
     * 硬盘IO等待时间
     */
    long getIowait();

    /**
     * 硬中断时间
     */
    long getIrq();

    /**
     * 软中断时间
     */
    long getSoftirq();

    /**
     * 虚拟化环境中运行其他操作系统上花费的时间（自Linux 2.6.11开始）
     */
    long getSteal();

    /**
     * 操作系统运行虚拟CPU花费的时间（自Linux 2.6.24开始）
     */
    long getGuest();

    /**
     * 运行一个带nice值的guest花费的时间（自Linux 2.6.33开始）
     */
    long getGuestNice();


}
