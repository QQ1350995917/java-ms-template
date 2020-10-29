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
 * date 2020-10-28 17:52
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
public class RPCCpuCore implements ICpuCore {

    private String groupName;
    private String nodeName;
    private String processor;
    private String vendorId;
    private String cpuFamily;
    private String model;
    private String modelName;
    private String stepping;
    private String microcode;
    private String cpuMHz;
    private String cacheSize;
    private String physicalId;
    private String siblings;
    private String coreId;
    private String cpuCores;
    private String apicid;
    private String initialApicid;
    private String fpu;
    private String fpuException;
    private String cpuidLevel;
    private String wp;
    private String flags;
    private String bogomips;
    private String clflushSize;
    private String cacheAlignment;
    private String addressSizes;
    private String powerManagement;

    @Override
    public String getGroupName() {
        return groupName;
    }

    @Override
    public String getNodeName() {
        return nodeName;
    }

    @Override
    public String getProcessor() {
        return processor;
    }

    @Override
    public String getVendorId() {
        return vendorId;
    }

    @Override
    public String getCpuFamily() {
        return cpuFamily;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public String getModelName() {
        return modelName;
    }

    @Override
    public String getStepping() {
        return stepping;
    }

    @Override
    public String getMicrocode() {
        return microcode;
    }

    @Override
    public String getCpuMHz() {
        return cpuMHz;
    }

    @Override
    public String getCacheSize() {
        return cacheSize;
    }

    @Override
    public String getPhysicalId() {
        return physicalId;
    }

    @Override
    public String getSiblings() {
        return siblings;
    }

    @Override
    public String getCoreId() {
        return coreId;
    }

    @Override
    public String getCpuCores() {
        return cpuCores;
    }

    @Override
    public String getApicid() {
        return apicid;
    }

    @Override
    public String getInitialApicid() {
        return initialApicid;
    }

    @Override
    public String getFpu() {
        return fpu;
    }

    @Override
    public String getFpuException() {
        return fpuException;
    }

    @Override
    public String getCpuidLevel() {
        return cpuidLevel;
    }

    @Override
    public String getWp() {
        return wp;
    }

    @Override
    public String getFlags() {
        return flags;
    }

    @Override
    public String getBogomips() {
        return bogomips;
    }

    @Override
    public String getClflushSize() {
        return clflushSize;
    }

    @Override
    public String getCacheAlignment() {
        return cacheAlignment;
    }

    @Override
    public String getAddressSizes() {
        return addressSizes;
    }

    @Override
    public String getPowerManagement() {
        return powerManagement;
    }
}
