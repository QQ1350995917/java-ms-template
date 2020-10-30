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
 * date 2020-10-28 19:02
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
public class RPCHostDiskStat implements IHostDiskStat {

    private String groupName;
    private String nodeName;
    public long majorDeviceNumber;
    public long minorDeviceNumber;
    public String deviceName;
    public long read;
    public long readMerge;
    public long readSector;
    public long readSpentMilliseconds;
    public long write;
    public long writeMerge;
    public long writeSector;
    public long writeSpentMilliseconds;
    public long ioRequest;
    public long ioSpentMilliseconds;
    public long ioSpentAllMilliseconds;

    @Override
    public long getMajorDeviceNumber() {
        return majorDeviceNumber;
    }

    @Override
    public long getMinorDeviceNumber() {
        return minorDeviceNumber;
    }

    @Override
    public String getDeviceName() {
        return deviceName;
    }

    @Override
    public long getRead() {
        return read;
    }

    @Override
    public long getReadMerge() {
        return readMerge;
    }

    @Override
    public long getReadSector() {
        return readSector;
    }

    @Override
    public long getReadSpentMilliseconds() {
        return readSpentMilliseconds;
    }

    @Override
    public long getWrite() {
        return write;
    }

    @Override
    public long getWriteMerge() {
        return writeMerge;
    }

    @Override
    public long getWriteSector() {
        return writeSector;
    }

    @Override
    public long getWriteSpentMilliseconds() {
        return writeSpentMilliseconds;
    }

    @Override
    public long getIoRequest() {
        return ioRequest;
    }

    @Override
    public long getIoSpentMilliseconds() {
        return ioSpentMilliseconds;
    }

    @Override
    public long getIoSpentAllMilliseconds() {
        return ioSpentAllMilliseconds;
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
