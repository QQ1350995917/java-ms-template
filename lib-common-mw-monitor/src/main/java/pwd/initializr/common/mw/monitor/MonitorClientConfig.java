package pwd.initializr.common.mw.monitor;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * pwd.initializr.common.mw.monitor@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-20 20:17
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class MonitorClientConfig implements EnvironmentAware {

    public static final String PREFIX = "monitor.cloud.client";
    private Environment environment;

    private boolean enable = true;
    private int rateSecond;
    private int threadMaxTotal;
    private int threadPerRoute;
    private boolean retryEnable;
    private int retryCount;

    private String url;
    private int connectionRequestTimeoutMillisecond;
    private int connectTimeoutMillisecond;
    private int socketTimeoutMillisecond;

    private boolean hostEnable;
    private int hostRateSecond;
    private String hostUrl;
    private int hostConnectionRequestTimeoutMillisecond;
    private int hostConnectTimeoutMillisecond;
    private int hostSocketTimeoutMillisecond;

    private boolean whoEnable;
    private int whoRateSecond;
    private String whoUrl;
    private int whoConnectionRequestTimeoutMillisecond;
    private int whoConnectTimeoutMillisecond;
    private int whoSocketTimeoutMillisecond;

    private boolean cpuEnable;
    private int cpuRateSecond;
    private String cpuUrl;
    private int cpuConnectionRequestTimeoutMillisecond;
    private int cpuConnectTimeoutMillisecond;
    private int cpuSocketTimeoutMillisecond;

    private boolean cpuStatEnable;
    private int cpuStatRateSecond;
    private String cpuStatUrl;
    private int cpuStatConnectionRequestTimeoutMillisecond;
    private int cpuStatConnectTimeoutMillisecond;
    private int cpuStatSocketTimeoutMillisecond;

    private boolean diskStatEnable;
    private int diskStatRateSecond;
    private String diskStatUrl;
    private int diskStatConnectionRequestTimeoutMillisecond;
    private int diskStatConnectTimeoutMillisecond;
    private int diskStatSocketTimeoutMillisecond;

    private boolean ethernetEnable;
    private int ethernetRateSecond;
    private String ethernetUrl;
    private int ethernetConnectionRequestTimeoutMillisecond;
    private int ethernetConnectTimeoutMillisecond;
    private int ethernetSocketTimeoutMillisecond;

    private boolean ethernetStatEnable;
    private int ethernetStatRateSecond;
    private String ethernetStatUrl;
    private int ethernetStatConnectionRequestTimeoutMillisecond;
    private int ethernetStatConnectTimeoutMillisecond;
    private int ethernetStatSocketTimeoutMillisecond;

    private boolean memoryEnable;
    private int memoryRateSecond;
    private String memoryUrl;
    private int memoryConnectionRequestTimeoutMillisecond;
    private int memoryConnectTimeoutMillisecond;
    private int memorySocketTimeoutMillisecond;

    private boolean memorySwapEnable;
    private int memorySwapRateSecond;
    private String memorySwapUrl;
    private int memorySwapConnectionRequestTimeoutMillisecond;
    private int memorySwapConnectTimeoutMillisecond;
    private int memorySwapSocketTimeoutMillisecond;

    public int getThreadMaxTotal() {
        return threadMaxTotal;
    }

    public void setThreadMaxTotal(int threadMaxTotal) {
        this.threadMaxTotal = threadMaxTotal;
    }

    public int getThreadPerRoute() {
        return threadPerRoute;
    }

    public void setThreadPerRoute(int threadPerRoute) {
        this.threadPerRoute = threadPerRoute;
    }

    public boolean isRetryEnable() {
        return retryEnable;
    }

    public void setRetryEnable(boolean retryEnable) {
        this.retryEnable = retryEnable;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public int getConnectTimeoutMillisecond() {
        return connectTimeoutMillisecond;
    }

    public void setConnectTimeoutMillisecond(int connectTimeoutMillisecond) {
        this.connectTimeoutMillisecond = connectTimeoutMillisecond;
    }

    public int getConnectionRequestTimeoutMillisecond() {
        return connectionRequestTimeoutMillisecond;
    }

    public void setConnectionRequestTimeoutMillisecond(int connectionRequestTimeoutMillisecond) {
        this.connectionRequestTimeoutMillisecond = connectionRequestTimeoutMillisecond;
    }

    public int getCpuConnectTimeoutMillisecond() {
        return cpuConnectTimeoutMillisecond;
    }

    public void setCpuConnectTimeoutMillisecond(int cpuConnectTimeoutMillisecond) {
        this.cpuConnectTimeoutMillisecond = cpuConnectTimeoutMillisecond;
    }

    public int getCpuConnectionRequestTimeoutMillisecond() {
        return cpuConnectionRequestTimeoutMillisecond;
    }

    public void setCpuConnectionRequestTimeoutMillisecond(
        int cpuConnectionRequestTimeoutMillisecond) {
        this.cpuConnectionRequestTimeoutMillisecond = cpuConnectionRequestTimeoutMillisecond;
    }

    public int getCpuStatConnectTimeoutMillisecond() {
        return cpuStatConnectTimeoutMillisecond;
    }

    public void setCpuStatConnectTimeoutMillisecond(int cpuStatConnectTimeoutMillisecond) {
        this.cpuStatConnectTimeoutMillisecond = cpuStatConnectTimeoutMillisecond;
    }

    public int getCpuStatConnectionRequestTimeoutMillisecond() {
        return cpuStatConnectionRequestTimeoutMillisecond;
    }

    public void setCpuStatConnectionRequestTimeoutMillisecond(
        int cpuStatConnectionRequestTimeoutMillisecond) {
        this.cpuStatConnectionRequestTimeoutMillisecond = cpuStatConnectionRequestTimeoutMillisecond;
    }

    public int getCpuStatRateSecond() {
        return cpuStatRateSecond;
    }

    public void setCpuStatRateSecond(int cpuStatRateSecond) {
        this.cpuStatRateSecond = cpuStatRateSecond;
    }

    public int getCpuStatSocketTimeoutMillisecond() {
        return cpuStatSocketTimeoutMillisecond;
    }

    public void setCpuStatSocketTimeoutMillisecond(int cpuStatSocketTimeoutMillisecond) {
        this.cpuStatSocketTimeoutMillisecond = cpuStatSocketTimeoutMillisecond;
    }

    public String getCpuStatUrl() {
        return cpuStatUrl;
    }

    public void setCpuStatUrl(String cpuStatUrl) {
        this.cpuStatUrl = cpuStatUrl;
    }

    public int getCpuRateSecond() {
        return cpuRateSecond;
    }

    public void setCpuRateSecond(int cpuRateSecond) {
        this.cpuRateSecond = cpuRateSecond;
    }

    public int getCpuSocketTimeoutMillisecond() {
        return cpuSocketTimeoutMillisecond;
    }

    public void setCpuSocketTimeoutMillisecond(int cpuSocketTimeoutMillisecond) {
        this.cpuSocketTimeoutMillisecond = cpuSocketTimeoutMillisecond;
    }

    public String getCpuUrl() {
        return cpuUrl;
    }

    public void setCpuUrl(String cpuUrl) {
        this.cpuUrl = cpuUrl;
    }

    public int getDiskStatConnectTimeoutMillisecond() {
        return diskStatConnectTimeoutMillisecond;
    }

    public void setDiskStatConnectTimeoutMillisecond(int diskStatConnectTimeoutMillisecond) {
        this.diskStatConnectTimeoutMillisecond = diskStatConnectTimeoutMillisecond;
    }

    public int getDiskStatConnectionRequestTimeoutMillisecond() {
        return diskStatConnectionRequestTimeoutMillisecond;
    }

    public void setDiskStatConnectionRequestTimeoutMillisecond(
        int diskStatConnectionRequestTimeoutMillisecond) {
        this.diskStatConnectionRequestTimeoutMillisecond = diskStatConnectionRequestTimeoutMillisecond;
    }

    public int getDiskStatRateSecond() {
        return diskStatRateSecond;
    }

    public void setDiskStatRateSecond(int diskStatRateSecond) {
        this.diskStatRateSecond = diskStatRateSecond;
    }

    public int getDiskStatSocketTimeoutMillisecond() {
        return diskStatSocketTimeoutMillisecond;
    }

    public void setDiskStatSocketTimeoutMillisecond(int diskStatSocketTimeoutMillisecond) {
        this.diskStatSocketTimeoutMillisecond = diskStatSocketTimeoutMillisecond;
    }

    public String getDiskStatUrl() {
        return diskStatUrl;
    }

    public void setDiskStatUrl(String diskStatUrl) {
        this.diskStatUrl = diskStatUrl;
    }

    public int getEthernetConnectTimeoutMillisecond() {
        return ethernetConnectTimeoutMillisecond;
    }

    public void setEthernetConnectTimeoutMillisecond(int ethernetConnectTimeoutMillisecond) {
        this.ethernetConnectTimeoutMillisecond = ethernetConnectTimeoutMillisecond;
    }

    public int getEthernetConnectionRequestTimeoutMillisecond() {
        return ethernetConnectionRequestTimeoutMillisecond;
    }

    public void setEthernetConnectionRequestTimeoutMillisecond(
        int ethernetConnectionRequestTimeoutMillisecond) {
        this.ethernetConnectionRequestTimeoutMillisecond = ethernetConnectionRequestTimeoutMillisecond;
    }

    public int getEthernetRateSecond() {
        return ethernetRateSecond;
    }

    public void setEthernetRateSecond(int ethernetRateSecond) {
        this.ethernetRateSecond = ethernetRateSecond;
    }

    public int getEthernetSocketTimeoutMillisecond() {
        return ethernetSocketTimeoutMillisecond;
    }

    public void setEthernetSocketTimeoutMillisecond(int ethernetSocketTimeoutMillisecond) {
        this.ethernetSocketTimeoutMillisecond = ethernetSocketTimeoutMillisecond;
    }

    public int getEthernetStatConnectTimeoutMillisecond() {
        return ethernetStatConnectTimeoutMillisecond;
    }

    public void setEthernetStatConnectTimeoutMillisecond(
        int ethernetStatConnectTimeoutMillisecond) {
        this.ethernetStatConnectTimeoutMillisecond = ethernetStatConnectTimeoutMillisecond;
    }

    public int getEthernetStatConnectionRequestTimeoutMillisecond() {
        return ethernetStatConnectionRequestTimeoutMillisecond;
    }

    public void setEthernetStatConnectionRequestTimeoutMillisecond(
        int ethernetStatConnectionRequestTimeoutMillisecond) {
        this.ethernetStatConnectionRequestTimeoutMillisecond = ethernetStatConnectionRequestTimeoutMillisecond;
    }

    public int getEthernetStatRateSecond() {
        return ethernetStatRateSecond;
    }

    public void setEthernetStatRateSecond(int ethernetStatRateSecond) {
        this.ethernetStatRateSecond = ethernetStatRateSecond;
    }

    public int getEthernetStatSocketTimeoutMillisecond() {
        return ethernetStatSocketTimeoutMillisecond;
    }

    public void setEthernetStatSocketTimeoutMillisecond(int ethernetStatSocketTimeoutMillisecond) {
        this.ethernetStatSocketTimeoutMillisecond = ethernetStatSocketTimeoutMillisecond;
    }

    public String getEthernetStatUrl() {
        return ethernetStatUrl;
    }

    public void setEthernetStatUrl(String ethernetStatUrl) {
        this.ethernetStatUrl = ethernetStatUrl;
    }

    public String getEthernetUrl() {
        return ethernetUrl;
    }

    public void setEthernetUrl(String ethernetUrl) {
        this.ethernetUrl = ethernetUrl;
    }

    public int getMemoryConnectTimeoutMillisecond() {
        return memoryConnectTimeoutMillisecond;
    }

    public void setMemoryConnectTimeoutMillisecond(int memoryConnectTimeoutMillisecond) {
        this.memoryConnectTimeoutMillisecond = memoryConnectTimeoutMillisecond;
    }

    public int getMemoryConnectionRequestTimeoutMillisecond() {
        return memoryConnectionRequestTimeoutMillisecond;
    }

    public void setMemoryConnectionRequestTimeoutMillisecond(
        int memoryConnectionRequestTimeoutMillisecond) {
        this.memoryConnectionRequestTimeoutMillisecond = memoryConnectionRequestTimeoutMillisecond;
    }

    public int getMemoryRateSecond() {
        return memoryRateSecond;
    }

    public void setMemoryRateSecond(int memoryRateSecond) {
        this.memoryRateSecond = memoryRateSecond;
    }

    public int getMemorySocketTimeoutMillisecond() {
        return memorySocketTimeoutMillisecond;
    }

    public void setMemorySocketTimeoutMillisecond(int memorySocketTimeoutMillisecond) {
        this.memorySocketTimeoutMillisecond = memorySocketTimeoutMillisecond;
    }

    public int getMemorySwapConnectTimeoutMillisecond() {
        return memorySwapConnectTimeoutMillisecond;
    }

    public void setMemorySwapConnectTimeoutMillisecond(int memorySwapConnectTimeoutMillisecond) {
        this.memorySwapConnectTimeoutMillisecond = memorySwapConnectTimeoutMillisecond;
    }

    public int getMemorySwapConnectionRequestTimeoutMillisecond() {
        return memorySwapConnectionRequestTimeoutMillisecond;
    }

    public void setMemorySwapConnectionRequestTimeoutMillisecond(
        int memorySwapConnectionRequestTimeoutMillisecond) {
        this.memorySwapConnectionRequestTimeoutMillisecond = memorySwapConnectionRequestTimeoutMillisecond;
    }

    public int getMemorySwapRateSecond() {
        return memorySwapRateSecond;
    }

    public void setMemorySwapRateSecond(int memorySwapRateSecond) {
        this.memorySwapRateSecond = memorySwapRateSecond;
    }

    public int getMemorySwapSocketTimeoutMillisecond() {
        return memorySwapSocketTimeoutMillisecond;
    }

    public void setMemorySwapSocketTimeoutMillisecond(int memorySwapSocketTimeoutMillisecond) {
        this.memorySwapSocketTimeoutMillisecond = memorySwapSocketTimeoutMillisecond;
    }

    public String getMemorySwapUrl() {
        return memorySwapUrl;
    }

    public void setMemorySwapUrl(String memorySwapUrl) {
        this.memorySwapUrl = memorySwapUrl;
    }

    public String getMemoryUrl() {
        return memoryUrl;
    }

    public void setMemoryUrl(String memoryUrl) {
        this.memoryUrl = memoryUrl;
    }

    public int getHostConnectTimeoutMillisecond() {
        return hostConnectTimeoutMillisecond;
    }

    public void setHostConnectTimeoutMillisecond(int hostConnectTimeoutMillisecond) {
        this.hostConnectTimeoutMillisecond = hostConnectTimeoutMillisecond;
    }

    public int getHostConnectionRequestTimeoutMillisecond() {
        return hostConnectionRequestTimeoutMillisecond;
    }

    public void setHostConnectionRequestTimeoutMillisecond(
        int hostConnectionRequestTimeoutMillisecond) {
        this.hostConnectionRequestTimeoutMillisecond = hostConnectionRequestTimeoutMillisecond;
    }

    public int getHostRateSecond() {
        return hostRateSecond;
    }

    public void setHostRateSecond(int hostRateSecond) {
        this.hostRateSecond = hostRateSecond;
    }

    public int getHostSocketTimeoutMillisecond() {
        return hostSocketTimeoutMillisecond;
    }

    public void setHostSocketTimeoutMillisecond(int hostSocketTimeoutMillisecond) {
        this.hostSocketTimeoutMillisecond = hostSocketTimeoutMillisecond;
    }

    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public int getRateSecond() {
        return rateSecond;
    }

    public void setRateSecond(int rateSecond) {
        this.rateSecond = rateSecond;
    }

    public int getSocketTimeoutMillisecond() {
        return socketTimeoutMillisecond;
    }

    public void setSocketTimeoutMillisecond(int socketTimeoutMillisecond) {
        this.socketTimeoutMillisecond = socketTimeoutMillisecond;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWhoConnectTimeoutMillisecond() {
        return whoConnectTimeoutMillisecond;
    }

    public void setWhoConnectTimeoutMillisecond(int whoConnectTimeoutMillisecond) {
        this.whoConnectTimeoutMillisecond = whoConnectTimeoutMillisecond;
    }

    public int getWhoConnectionRequestTimeoutMillisecond() {
        return whoConnectionRequestTimeoutMillisecond;
    }

    public void setWhoConnectionRequestTimeoutMillisecond(
        int whoConnectionRequestTimeoutMillisecond) {
        this.whoConnectionRequestTimeoutMillisecond = whoConnectionRequestTimeoutMillisecond;
    }

    public int getWhoRateSecond() {
        return whoRateSecond;
    }

    public void setWhoRateSecond(int whoRateSecond) {
        this.whoRateSecond = whoRateSecond;
    }

    public int getWhoSocketTimeoutMillisecond() {
        return whoSocketTimeoutMillisecond;
    }

    public void setWhoSocketTimeoutMillisecond(int whoSocketTimeoutMillisecond) {
        this.whoSocketTimeoutMillisecond = whoSocketTimeoutMillisecond;
    }

    public String getWhoUrl() {
        return whoUrl;
    }

    public void setWhoUrl(String whoUrl) {
        this.whoUrl = whoUrl;
    }

    public boolean isCpuStatEnable() {
        return cpuStatEnable;
    }

    public void setCpuStatEnable(boolean cpuStatEnable) {
        this.cpuStatEnable = cpuStatEnable;
    }

    public boolean isCpuEnable() {
        return cpuEnable;
    }

    public void setCpuEnable(boolean cpuEnable) {
        this.cpuEnable = cpuEnable;
    }

    public boolean isDiskStatEnable() {
        return diskStatEnable;
    }

    public void setDiskStatEnable(boolean diskStatEnable) {
        this.diskStatEnable = diskStatEnable;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isEthernetEnable() {
        return ethernetEnable;
    }

    public void setEthernetEnable(boolean ethernetEnable) {
        this.ethernetEnable = ethernetEnable;
    }

    public boolean isEthernetStatEnable() {
        return ethernetStatEnable;
    }

    public void setEthernetStatEnable(boolean ethernetStatEnable) {
        this.ethernetStatEnable = ethernetStatEnable;
    }

    public boolean isMemoryEnable() {
        return memoryEnable;
    }

    public void setMemoryEnable(boolean memoryEnable) {
        this.memoryEnable = memoryEnable;
    }

    public boolean isMemorySwapEnable() {
        return memorySwapEnable;
    }

    public void setMemorySwapEnable(boolean memorySwapEnable) {
        this.memorySwapEnable = memorySwapEnable;
    }

    public boolean isOsEnable() {
        return hostEnable;
    }

    public void setHostEnable(boolean hostEnable) {
        this.hostEnable = hostEnable;
    }

    public boolean isWhoEnable() {
        return whoEnable;
    }

    public void setWhoEnable(boolean whoEnable) {
        this.whoEnable = whoEnable;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
