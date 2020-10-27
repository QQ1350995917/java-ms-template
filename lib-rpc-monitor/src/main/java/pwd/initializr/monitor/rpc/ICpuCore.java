package pwd.initializr.monitor.rpc;

/**
 * pwd.initializr.monitor.rpc@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-27 20:46
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface ICpuCore {

    String getProcessor();

    String getVendorId();

    String getCpuFamily();

    String getModel();

    String getModelMame();

    String getStepping();

    String getMicrocode();

    String getCpuMHz();

    String getCacheSize();

    String getPhysicalId();

    String getSiblings();

    String getCoreId();

    String getCpuCores();

    String getApicid();

    String getInitialApicid();

    String getFpu();

    String getFpuException();

    String getCpuidLevel();

    String getWp();

    String getFlags();

    String getBogomips();

    String getClflushSize();

    String getCacheAlignment();

    String getAddressSizes();

    String getPowerManagement();

}
