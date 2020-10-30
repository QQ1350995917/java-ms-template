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

    private boolean ethernetStatEnable;
    private int ethernetStatRateSecond;
    private String ethernetStatUrl;
    private int ethernetStatConnectionRequestTimeoutMillisecond;
    private int ethernetStatConnectTimeoutMillisecond;
    private int ethernetStatSocketTimeoutMillisecond;

    private boolean loadStatEnable;
    private int loadStatRateSecond;
    private String loadStatUrl;
    private int loadStatConnectionRequestTimeoutMillisecond;
    private int loadStatConnectTimeoutMillisecond;
    private int loadStatSocketTimeoutMillisecond;

    private boolean loggedStatEnable;
    private int loggedStatRateSecond;
    private String loggedStatUrl;
    private int loggedStatConnectionRequestTimeoutMillisecond;
    private int loggedStatConnectTimeoutMillisecond;
    private int loggedStatSocketTimeoutMillisecond;

    private boolean memoryStatEnable;
    private int memoryStatRateSecond;
    private String memoryStatUrl;
    private int memoryStatConnectionRequestTimeoutMillisecond;
    private int memoryStatConnectTimeoutMillisecond;
    private int memoryStatSocketTimeoutMillisecond;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public int getRateSecond() {
        return rateSecond;
    }

    public void setRateSecond(int rateSecond) {
        this.rateSecond = rateSecond;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getConnectionRequestTimeoutMillisecond() {
        return connectionRequestTimeoutMillisecond;
    }

    public void setConnectionRequestTimeoutMillisecond(int connectionRequestTimeoutMillisecond) {
        this.connectionRequestTimeoutMillisecond = connectionRequestTimeoutMillisecond;
    }

    public int getConnectTimeoutMillisecond() {
        return connectTimeoutMillisecond;
    }

    public void setConnectTimeoutMillisecond(int connectTimeoutMillisecond) {
        this.connectTimeoutMillisecond = connectTimeoutMillisecond;
    }

    public int getSocketTimeoutMillisecond() {
        return socketTimeoutMillisecond;
    }

    public void setSocketTimeoutMillisecond(int socketTimeoutMillisecond) {
        this.socketTimeoutMillisecond = socketTimeoutMillisecond;
    }

    public boolean isHostEnable() {
        return hostEnable;
    }

    public void setHostEnable(boolean hostEnable) {
        this.hostEnable = hostEnable;
    }

    public int getHostRateSecond() {
        return hostRateSecond;
    }

    public void setHostRateSecond(int hostRateSecond) {
        this.hostRateSecond = hostRateSecond;
    }

    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public int getHostConnectionRequestTimeoutMillisecond() {
        return hostConnectionRequestTimeoutMillisecond;
    }

    public void setHostConnectionRequestTimeoutMillisecond(
        int hostConnectionRequestTimeoutMillisecond) {
        this.hostConnectionRequestTimeoutMillisecond = hostConnectionRequestTimeoutMillisecond;
    }

    public int getHostConnectTimeoutMillisecond() {
        return hostConnectTimeoutMillisecond;
    }

    public void setHostConnectTimeoutMillisecond(int hostConnectTimeoutMillisecond) {
        this.hostConnectTimeoutMillisecond = hostConnectTimeoutMillisecond;
    }

    public int getHostSocketTimeoutMillisecond() {
        return hostSocketTimeoutMillisecond;
    }

    public void setHostSocketTimeoutMillisecond(int hostSocketTimeoutMillisecond) {
        this.hostSocketTimeoutMillisecond = hostSocketTimeoutMillisecond;
    }

    public boolean isWhoEnable() {
        return whoEnable;
    }

    public void setWhoEnable(boolean whoEnable) {
        this.whoEnable = whoEnable;
    }

    public int getWhoRateSecond() {
        return whoRateSecond;
    }

    public void setWhoRateSecond(int whoRateSecond) {
        this.whoRateSecond = whoRateSecond;
    }

    public String getWhoUrl() {
        return whoUrl;
    }

    public void setWhoUrl(String whoUrl) {
        this.whoUrl = whoUrl;
    }

    public int getWhoConnectionRequestTimeoutMillisecond() {
        return whoConnectionRequestTimeoutMillisecond;
    }

    public void setWhoConnectionRequestTimeoutMillisecond(
        int whoConnectionRequestTimeoutMillisecond) {
        this.whoConnectionRequestTimeoutMillisecond = whoConnectionRequestTimeoutMillisecond;
    }

    public int getWhoConnectTimeoutMillisecond() {
        return whoConnectTimeoutMillisecond;
    }

    public void setWhoConnectTimeoutMillisecond(int whoConnectTimeoutMillisecond) {
        this.whoConnectTimeoutMillisecond = whoConnectTimeoutMillisecond;
    }

    public int getWhoSocketTimeoutMillisecond() {
        return whoSocketTimeoutMillisecond;
    }

    public void setWhoSocketTimeoutMillisecond(int whoSocketTimeoutMillisecond) {
        this.whoSocketTimeoutMillisecond = whoSocketTimeoutMillisecond;
    }

    public boolean isCpuEnable() {
        return cpuEnable;
    }

    public void setCpuEnable(boolean cpuEnable) {
        this.cpuEnable = cpuEnable;
    }

    public int getCpuRateSecond() {
        return cpuRateSecond;
    }

    public void setCpuRateSecond(int cpuRateSecond) {
        this.cpuRateSecond = cpuRateSecond;
    }

    public String getCpuUrl() {
        return cpuUrl;
    }

    public void setCpuUrl(String cpuUrl) {
        this.cpuUrl = cpuUrl;
    }

    public int getCpuConnectionRequestTimeoutMillisecond() {
        return cpuConnectionRequestTimeoutMillisecond;
    }

    public void setCpuConnectionRequestTimeoutMillisecond(
        int cpuConnectionRequestTimeoutMillisecond) {
        this.cpuConnectionRequestTimeoutMillisecond = cpuConnectionRequestTimeoutMillisecond;
    }

    public int getCpuConnectTimeoutMillisecond() {
        return cpuConnectTimeoutMillisecond;
    }

    public void setCpuConnectTimeoutMillisecond(int cpuConnectTimeoutMillisecond) {
        this.cpuConnectTimeoutMillisecond = cpuConnectTimeoutMillisecond;
    }

    public int getCpuSocketTimeoutMillisecond() {
        return cpuSocketTimeoutMillisecond;
    }

    public void setCpuSocketTimeoutMillisecond(int cpuSocketTimeoutMillisecond) {
        this.cpuSocketTimeoutMillisecond = cpuSocketTimeoutMillisecond;
    }

    public boolean isCpuStatEnable() {
        return cpuStatEnable;
    }

    public void setCpuStatEnable(boolean cpuStatEnable) {
        this.cpuStatEnable = cpuStatEnable;
    }

    public int getCpuStatRateSecond() {
        return cpuStatRateSecond;
    }

    public void setCpuStatRateSecond(int cpuStatRateSecond) {
        this.cpuStatRateSecond = cpuStatRateSecond;
    }

    public String getCpuStatUrl() {
        return cpuStatUrl;
    }

    public void setCpuStatUrl(String cpuStatUrl) {
        this.cpuStatUrl = cpuStatUrl;
    }

    public int getCpuStatConnectionRequestTimeoutMillisecond() {
        return cpuStatConnectionRequestTimeoutMillisecond;
    }

    public void setCpuStatConnectionRequestTimeoutMillisecond(
        int cpuStatConnectionRequestTimeoutMillisecond) {
        this.cpuStatConnectionRequestTimeoutMillisecond = cpuStatConnectionRequestTimeoutMillisecond;
    }

    public int getCpuStatConnectTimeoutMillisecond() {
        return cpuStatConnectTimeoutMillisecond;
    }

    public void setCpuStatConnectTimeoutMillisecond(int cpuStatConnectTimeoutMillisecond) {
        this.cpuStatConnectTimeoutMillisecond = cpuStatConnectTimeoutMillisecond;
    }

    public int getCpuStatSocketTimeoutMillisecond() {
        return cpuStatSocketTimeoutMillisecond;
    }

    public void setCpuStatSocketTimeoutMillisecond(int cpuStatSocketTimeoutMillisecond) {
        this.cpuStatSocketTimeoutMillisecond = cpuStatSocketTimeoutMillisecond;
    }

    public boolean isDiskStatEnable() {
        return diskStatEnable;
    }

    public void setDiskStatEnable(boolean diskStatEnable) {
        this.diskStatEnable = diskStatEnable;
    }

    public int getDiskStatRateSecond() {
        return diskStatRateSecond;
    }

    public void setDiskStatRateSecond(int diskStatRateSecond) {
        this.diskStatRateSecond = diskStatRateSecond;
    }

    public String getDiskStatUrl() {
        return diskStatUrl;
    }

    public void setDiskStatUrl(String diskStatUrl) {
        this.diskStatUrl = diskStatUrl;
    }

    public int getDiskStatConnectionRequestTimeoutMillisecond() {
        return diskStatConnectionRequestTimeoutMillisecond;
    }

    public void setDiskStatConnectionRequestTimeoutMillisecond(
        int diskStatConnectionRequestTimeoutMillisecond) {
        this.diskStatConnectionRequestTimeoutMillisecond = diskStatConnectionRequestTimeoutMillisecond;
    }

    public int getDiskStatConnectTimeoutMillisecond() {
        return diskStatConnectTimeoutMillisecond;
    }

    public void setDiskStatConnectTimeoutMillisecond(int diskStatConnectTimeoutMillisecond) {
        this.diskStatConnectTimeoutMillisecond = diskStatConnectTimeoutMillisecond;
    }

    public int getDiskStatSocketTimeoutMillisecond() {
        return diskStatSocketTimeoutMillisecond;
    }

    public void setDiskStatSocketTimeoutMillisecond(int diskStatSocketTimeoutMillisecond) {
        this.diskStatSocketTimeoutMillisecond = diskStatSocketTimeoutMillisecond;
    }

    public boolean isEthernetStatEnable() {
        return ethernetStatEnable;
    }

    public void setEthernetStatEnable(boolean ethernetStatEnable) {
        this.ethernetStatEnable = ethernetStatEnable;
    }

    public int getEthernetStatRateSecond() {
        return ethernetStatRateSecond;
    }

    public void setEthernetStatRateSecond(int ethernetStatRateSecond) {
        this.ethernetStatRateSecond = ethernetStatRateSecond;
    }

    public String getEthernetStatUrl() {
        return ethernetStatUrl;
    }

    public void setEthernetStatUrl(String ethernetStatUrl) {
        this.ethernetStatUrl = ethernetStatUrl;
    }

    public int getEthernetStatConnectionRequestTimeoutMillisecond() {
        return ethernetStatConnectionRequestTimeoutMillisecond;
    }

    public void setEthernetStatConnectionRequestTimeoutMillisecond(
        int ethernetStatConnectionRequestTimeoutMillisecond) {
        this.ethernetStatConnectionRequestTimeoutMillisecond = ethernetStatConnectionRequestTimeoutMillisecond;
    }

    public int getEthernetStatConnectTimeoutMillisecond() {
        return ethernetStatConnectTimeoutMillisecond;
    }

    public void setEthernetStatConnectTimeoutMillisecond(
        int ethernetStatConnectTimeoutMillisecond) {
        this.ethernetStatConnectTimeoutMillisecond = ethernetStatConnectTimeoutMillisecond;
    }

    public int getEthernetStatSocketTimeoutMillisecond() {
        return ethernetStatSocketTimeoutMillisecond;
    }

    public void setEthernetStatSocketTimeoutMillisecond(int ethernetStatSocketTimeoutMillisecond) {
        this.ethernetStatSocketTimeoutMillisecond = ethernetStatSocketTimeoutMillisecond;
    }

    public boolean isLoadStatEnable() {
        return loadStatEnable;
    }

    public void setLoadStatEnable(boolean loadStatEnable) {
        this.loadStatEnable = loadStatEnable;
    }

    public int getLoadStatRateSecond() {
        return loadStatRateSecond;
    }

    public void setLoadStatRateSecond(int loadStatRateSecond) {
        this.loadStatRateSecond = loadStatRateSecond;
    }

    public String getLoadStatUrl() {
        return loadStatUrl;
    }

    public void setLoadStatUrl(String loadStatUrl) {
        this.loadStatUrl = loadStatUrl;
    }

    public int getLoadStatConnectionRequestTimeoutMillisecond() {
        return loadStatConnectionRequestTimeoutMillisecond;
    }

    public void setLoadStatConnectionRequestTimeoutMillisecond(
        int loadStatConnectionRequestTimeoutMillisecond) {
        this.loadStatConnectionRequestTimeoutMillisecond = loadStatConnectionRequestTimeoutMillisecond;
    }

    public int getLoadStatConnectTimeoutMillisecond() {
        return loadStatConnectTimeoutMillisecond;
    }

    public void setLoadStatConnectTimeoutMillisecond(int loadStatConnectTimeoutMillisecond) {
        this.loadStatConnectTimeoutMillisecond = loadStatConnectTimeoutMillisecond;
    }

    public int getLoadStatSocketTimeoutMillisecond() {
        return loadStatSocketTimeoutMillisecond;
    }

    public void setLoadStatSocketTimeoutMillisecond(int loadStatSocketTimeoutMillisecond) {
        this.loadStatSocketTimeoutMillisecond = loadStatSocketTimeoutMillisecond;
    }

    public boolean isLoggedStatEnable() {
        return loggedStatEnable;
    }

    public void setLoggedStatEnable(boolean loggedStatEnable) {
        this.loggedStatEnable = loggedStatEnable;
    }

    public int getLoggedStatRateSecond() {
        return loggedStatRateSecond;
    }

    public void setLoggedStatRateSecond(int loggedStatRateSecond) {
        this.loggedStatRateSecond = loggedStatRateSecond;
    }

    public String getLoggedStatUrl() {
        return loggedStatUrl;
    }

    public void setLoggedStatUrl(String loggedStatUrl) {
        this.loggedStatUrl = loggedStatUrl;
    }

    public int getLoggedStatConnectionRequestTimeoutMillisecond() {
        return loggedStatConnectionRequestTimeoutMillisecond;
    }

    public void setLoggedStatConnectionRequestTimeoutMillisecond(
        int loggedStatConnectionRequestTimeoutMillisecond) {
        this.loggedStatConnectionRequestTimeoutMillisecond = loggedStatConnectionRequestTimeoutMillisecond;
    }

    public int getLoggedStatConnectTimeoutMillisecond() {
        return loggedStatConnectTimeoutMillisecond;
    }

    public void setLoggedStatConnectTimeoutMillisecond(int loggedStatConnectTimeoutMillisecond) {
        this.loggedStatConnectTimeoutMillisecond = loggedStatConnectTimeoutMillisecond;
    }

    public int getLoggedStatSocketTimeoutMillisecond() {
        return loggedStatSocketTimeoutMillisecond;
    }

    public void setLoggedStatSocketTimeoutMillisecond(int loggedStatSocketTimeoutMillisecond) {
        this.loggedStatSocketTimeoutMillisecond = loggedStatSocketTimeoutMillisecond;
    }

    public boolean isMemoryStatEnable() {
        return memoryStatEnable;
    }

    public void setMemoryStatEnable(boolean memoryStatEnable) {
        this.memoryStatEnable = memoryStatEnable;
    }

    public int getMemoryStatRateSecond() {
        return memoryStatRateSecond;
    }

    public void setMemoryStatRateSecond(int memoryStatRateSecond) {
        this.memoryStatRateSecond = memoryStatRateSecond;
    }

    public String getMemoryStatUrl() {
        return memoryStatUrl;
    }

    public void setMemoryStatUrl(String memoryStatUrl) {
        this.memoryStatUrl = memoryStatUrl;
    }

    public int getMemoryStatConnectionRequestTimeoutMillisecond() {
        return memoryStatConnectionRequestTimeoutMillisecond;
    }

    public void setMemoryStatConnectionRequestTimeoutMillisecond(
        int memoryStatConnectionRequestTimeoutMillisecond) {
        this.memoryStatConnectionRequestTimeoutMillisecond = memoryStatConnectionRequestTimeoutMillisecond;
    }

    public int getMemoryStatConnectTimeoutMillisecond() {
        return memoryStatConnectTimeoutMillisecond;
    }

    public void setMemoryStatConnectTimeoutMillisecond(int memoryStatConnectTimeoutMillisecond) {
        this.memoryStatConnectTimeoutMillisecond = memoryStatConnectTimeoutMillisecond;
    }

    public int getMemoryStatSocketTimeoutMillisecond() {
        return memoryStatSocketTimeoutMillisecond;
    }

    public void setMemoryStatSocketTimeoutMillisecond(int memoryStatSocketTimeoutMillisecond) {
        this.memoryStatSocketTimeoutMillisecond = memoryStatSocketTimeoutMillisecond;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
