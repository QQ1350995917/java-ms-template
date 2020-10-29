package pwd.initializr.monitor.rpc;

import java.io.Serializable;

/**
 * pwd.initializr.monitor.rpc@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-28 19:44
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface IEthernetStat extends Serializable {

    /**
     * 逻辑组名
     */
    String getGroupName();

    /**
     * 主机名 network node hostname
     */
    String getNodeName();

    String getInterFace();

    long getReceiveBytes();

    long getReceivePackets();

    long getReceiveErrs();

    long getReceiveDrop();

    long getReceiveFifo();

    long getReceiveFrame();

    long getReceiveCompressed();

    long getReceiveMulticast();

    long getTransmitBytes();

    long getTransmitPackets();

    long getTransmitErrs();

    long getTransmitDrop();

    long getTransmitFifo();

    long getTransmitColls();

    long getTransmitCarrier();

    long getTransmitCompressed();
}
