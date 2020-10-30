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
 * date 2020-10-28 19:48
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class RPCHostEthernetStat implements IHostEthernetStat {

    private String groupName;
    private String nodeName;
    private String interFace;
    private long receiveBytes;
    private long receivePackets;
    private long receiveErrs;
    private long receiveDrop;
    private long receiveFifo;
    private long receiveFrame;
    private long receiveCompressed;
    private long receiveMulticast;
    private long transmitBytes;
    private long transmitPackets;
    private long transmitErrs;
    private long transmitDrop;
    private long transmitFifo;
    private long transmitColls;
    private long transmitCarrier;
    private long transmitCompressed;

    @Override
    public String getInterFace() {
        return interFace;
    }

    @Override
    public long getReceiveBytes() {
        return receiveBytes;
    }

    @Override
    public long getReceivePackets() {
        return receivePackets;
    }

    @Override
    public long getReceiveErrs() {
        return receiveErrs;
    }

    @Override
    public long getReceiveDrop() {
        return receiveDrop;
    }

    @Override
    public long getReceiveFifo() {
        return receiveFifo;
    }

    @Override
    public long getReceiveFrame() {
        return receiveFrame;
    }

    @Override
    public long getReceiveCompressed() {
        return receiveCompressed;
    }

    @Override
    public long getReceiveMulticast() {
        return receiveMulticast;
    }

    @Override
    public long getTransmitBytes() {
        return transmitBytes;
    }

    @Override
    public long getTransmitPackets() {
        return transmitPackets;
    }

    @Override
    public long getTransmitErrs() {
        return transmitErrs;
    }

    @Override
    public long getTransmitDrop() {
        return transmitDrop;
    }

    @Override
    public long getTransmitFifo() {
        return transmitFifo;
    }

    @Override
    public long getTransmitColls() {
        return transmitColls;
    }

    @Override
    public long getTransmitCarrier() {
        return transmitCarrier;
    }

    @Override
    public long getTransmitCompressed() {
        return transmitCompressed;
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
