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
    public Double read;
    public Double readMerge;
    public Double readSector;
    public Double readSpentMilliseconds;
    public Double write;
    public Double writeMerge;
    public Double writeSector;
    public Double writeSpentMilliseconds;
    public Double ioRequest;
    public Double ioSpentMilliseconds;
    public Double ioSpentAllMilliseconds;

    @Override
    public String getGroupName() {
        return groupName;
    }

    @Override
    public String getNodeName() {
        return nodeName;
    }

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
    public Double getRead() {
        return read;
    }

    @Override
    public Double getReadMerge() {
        return readMerge;
    }

    @Override
    public Double getReadSector() {
        return readSector;
    }

    @Override
    public Double getReadSpentMilliseconds() {
        return readSpentMilliseconds;
    }

    @Override
    public Double getWrite() {
        return write;
    }

    @Override
    public Double getWriteMerge() {
        return writeMerge;
    }

    @Override
    public Double getWriteSector() {
        return writeSector;
    }

    @Override
    public Double getWriteSpentMilliseconds() {
        return writeSpentMilliseconds;
    }

    @Override
    public Double getIoRequest() {
        return ioRequest;
    }

    @Override
    public Double getIoSpentMilliseconds() {
        return ioSpentMilliseconds;
    }

    @Override
    public Double getIoSpentAllMilliseconds() {
        return ioSpentAllMilliseconds;
    }
}
