package pwd.initializr.monitor.rpc;

import java.io.Serializable;

/**
 * pwd.initializr.monitor.rpc@ms-web-initializr
 *
 * <h1>磁盘状态</h1>
 *
 * <p>除正在处理的输入/输出请求数这项是非累积值外，其他磁盘统计都是累积值。</p>
 * <p>磁盘使用率计算方式为</p>
 * <p>两次采集的输入/输出操作花费的毫秒数之差 / 采集间隔时间</p>
 * <p>例如：第一次采集输入/输出操作花费的毫秒数为90258834，间隔10秒后采集的值为90258710</p>
 * <p>那么磁盘使用率为 （90258710ms - 90258834ms）/ 10*1000ms = 0.0124，也就是1.24%</p>
 *
 * date 2020-10-28 19:01
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface IDiskStat extends Serializable {

    /**
     * 主设备号
     */
    long getMajorDeviceNumber();

    /**
     * 次设备号
     */
    long getMinorDeviceNumber();

    /**
     * 设备名称
     */
    String getDeviceName();

    /**
     * 读完成次数
     */
    long getRead();

    /**
     * 读合并完成次数
     */
    long getReadMerge();

    /**
     * 读扇区次数
     */
    long getReadSector();

    /**
     * 读操作花费毫秒数
     */
    long getReadSpentMilliseconds();

    /**
     * 写完成次数
     */
    long getWrite();

    /**
     * 写合并次数
     */
    long getWriteMerge();

    /**
     * 写扇区次数
     */
    long getWriteSector();

    /**
     * 写操作花费的毫秒数
     */
    long getWriteSpentMilliseconds();

    /**
     * 正在处理的输入/输出请求数
     */
    long getIoRequest();

    /**
     * 输入/输出操作花费的毫秒数
     */
    long getIoSpentMilliseconds();

    /**
     * 输入/输出操作花费的加权毫秒数
     */
    long getIoSpentAllMilliseconds();
}