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
 * date 2020-10-28 13:41
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class RPCHost implements IHost {

    private String groupName;
    private String nodeName;
    private String operatingSystem;
    private String hardwarePlatform;
    private String systemUpSince;
    private String kernelName;
    private String kernelVersion;
    private String kernelRelease;
    private String machine;
    private String processor;

    @Override
    public String getGroupName() {
        return groupName;
    }

    @Override
    public String getNodeName() {
        return nodeName;
    }

    @Override
    public String getOperatingSystem() {
        return operatingSystem;
    }

    @Override
    public String getHardwarePlatform() {
        return hardwarePlatform;
    }

    @Override
    public String getSystemUpSince() {
        return systemUpSince;
    }

    @Override
    public String getKernelName() {
        return kernelName;
    }

    @Override
    public String getKernelVersion() {
        return kernelVersion;
    }

    @Override
    public String getKernelRelease() {
        return kernelRelease;
    }

    @Override
    public String getMachine() {
        return machine;
    }

    @Override
    public String getProcessor() {
        return processor;
    }
}
