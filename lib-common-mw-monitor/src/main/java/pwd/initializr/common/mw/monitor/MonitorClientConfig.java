package pwd.initializr.common.mw.monitor;

import org.springframework.boot.context.properties.ConfigurationProperties;
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
@ConfigurationProperties(MonitorClientConfig.PREFIX)
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

  private boolean osEnable;
  private int osRateSecond;
  private String osUrl;
  private int osConnectionRequestTimeoutMillisecond;
  private int osConnectTimeoutMillisecond;
  private int osSocketTimeoutMillisecond;

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

  private boolean cpuCoreEnable;
  private int cpuCoreRateSecond;
  private String cpuCoreUrl;
  private int cpuCoreConnectionRequestTimeoutMillisecond;
  private int cpuCoreConnectTimeoutMillisecond;
  private int cpuCoreSocketTimeoutMillisecond;

  private boolean cpuCoreUsageEnable;
  private int cpuCoreUsageRateSecond;
  private String cpuCoreUsageUrl;
  private int cpuCoreUsageConnectionRequestTimeoutMillisecond;
  private int cpuCoreUsageConnectTimeoutMillisecond;
  private int cpuCoreUsageSocketTimeoutMillisecond;

  private boolean diskEnable;
  private int diskRateSecond;
  private String diskUrl;
  private int diskConnectionRequestTimeoutMillisecond;
  private int diskConnectTimeoutMillisecond;
  private int diskSocketTimeoutMillisecond;

  private boolean diskUsageEnable;
  private int diskUsageRateSecond;
  private String diskUsageUrl;
  private int diskUsageConnectionRequestTimeoutMillisecond;
  private int diskUsageConnectTimeoutMillisecond;
  private int diskUsageSocketTimeoutMillisecond;

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

  public int getCpuCoreConnectTimeoutMillisecond() {
    return cpuCoreConnectTimeoutMillisecond;
  }

  public void setCpuCoreConnectTimeoutMillisecond(int cpuCoreConnectTimeoutMillisecond) {
    this.cpuCoreConnectTimeoutMillisecond = cpuCoreConnectTimeoutMillisecond;
  }

  public int getCpuCoreConnectionRequestTimeoutMillisecond() {
    return cpuCoreConnectionRequestTimeoutMillisecond;
  }

  public void setCpuCoreConnectionRequestTimeoutMillisecond(
      int cpuCoreConnectionRequestTimeoutMillisecond) {
    this.cpuCoreConnectionRequestTimeoutMillisecond = cpuCoreConnectionRequestTimeoutMillisecond;
  }

  public int getCpuCoreRateSecond() {
    return cpuCoreRateSecond;
  }

  public void setCpuCoreRateSecond(int cpuCoreRateSecond) {
    this.cpuCoreRateSecond = cpuCoreRateSecond;
  }

  public int getCpuCoreSocketTimeoutMillisecond() {
    return cpuCoreSocketTimeoutMillisecond;
  }

  public void setCpuCoreSocketTimeoutMillisecond(int cpuCoreSocketTimeoutMillisecond) {
    this.cpuCoreSocketTimeoutMillisecond = cpuCoreSocketTimeoutMillisecond;
  }

  public String getCpuCoreUrl() {
    return cpuCoreUrl;
  }

  public void setCpuCoreUrl(String cpuCoreUrl) {
    this.cpuCoreUrl = cpuCoreUrl;
  }

  public int getCpuCoreUsageConnectTimeoutMillisecond() {
    return cpuCoreUsageConnectTimeoutMillisecond;
  }

  public void setCpuCoreUsageConnectTimeoutMillisecond(int cpuCoreUsageConnectTimeoutMillisecond) {
    this.cpuCoreUsageConnectTimeoutMillisecond = cpuCoreUsageConnectTimeoutMillisecond;
  }

  public int getCpuCoreUsageConnectionRequestTimeoutMillisecond() {
    return cpuCoreUsageConnectionRequestTimeoutMillisecond;
  }

  public void setCpuCoreUsageConnectionRequestTimeoutMillisecond(
      int cpuCoreUsageConnectionRequestTimeoutMillisecond) {
    this.cpuCoreUsageConnectionRequestTimeoutMillisecond = cpuCoreUsageConnectionRequestTimeoutMillisecond;
  }

  public int getCpuCoreUsageRateSecond() {
    return cpuCoreUsageRateSecond;
  }

  public void setCpuCoreUsageRateSecond(int cpuCoreUsageRateSecond) {
    this.cpuCoreUsageRateSecond = cpuCoreUsageRateSecond;
  }

  public int getCpuCoreUsageSocketTimeoutMillisecond() {
    return cpuCoreUsageSocketTimeoutMillisecond;
  }

  public void setCpuCoreUsageSocketTimeoutMillisecond(int cpuCoreUsageSocketTimeoutMillisecond) {
    this.cpuCoreUsageSocketTimeoutMillisecond = cpuCoreUsageSocketTimeoutMillisecond;
  }

  public String getCpuCoreUsageUrl() {
    return cpuCoreUsageUrl;
  }

  public void setCpuCoreUsageUrl(String cpuCoreUsageUrl) {
    this.cpuCoreUsageUrl = cpuCoreUsageUrl;
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

  public int getDiskConnectTimeoutMillisecond() {
    return diskConnectTimeoutMillisecond;
  }

  public void setDiskConnectTimeoutMillisecond(int diskConnectTimeoutMillisecond) {
    this.diskConnectTimeoutMillisecond = diskConnectTimeoutMillisecond;
  }

  public int getDiskConnectionRequestTimeoutMillisecond() {
    return diskConnectionRequestTimeoutMillisecond;
  }

  public void setDiskConnectionRequestTimeoutMillisecond(
      int diskConnectionRequestTimeoutMillisecond) {
    this.diskConnectionRequestTimeoutMillisecond = diskConnectionRequestTimeoutMillisecond;
  }

  public int getDiskRateSecond() {
    return diskRateSecond;
  }

  public void setDiskRateSecond(int diskRateSecond) {
    this.diskRateSecond = diskRateSecond;
  }

  public int getDiskSocketTimeoutMillisecond() {
    return diskSocketTimeoutMillisecond;
  }

  public void setDiskSocketTimeoutMillisecond(int diskSocketTimeoutMillisecond) {
    this.diskSocketTimeoutMillisecond = diskSocketTimeoutMillisecond;
  }

  public String getDiskUrl() {
    return diskUrl;
  }

  public void setDiskUrl(String diskUrl) {
    this.diskUrl = diskUrl;
  }

  public int getDiskUsageConnectTimeoutMillisecond() {
    return diskUsageConnectTimeoutMillisecond;
  }

  public void setDiskUsageConnectTimeoutMillisecond(int diskUsageConnectTimeoutMillisecond) {
    this.diskUsageConnectTimeoutMillisecond = diskUsageConnectTimeoutMillisecond;
  }

  public int getDiskUsageConnectionRequestTimeoutMillisecond() {
    return diskUsageConnectionRequestTimeoutMillisecond;
  }

  public void setDiskUsageConnectionRequestTimeoutMillisecond(
      int diskUsageConnectionRequestTimeoutMillisecond) {
    this.diskUsageConnectionRequestTimeoutMillisecond = diskUsageConnectionRequestTimeoutMillisecond;
  }

  public int getDiskUsageRateSecond() {
    return diskUsageRateSecond;
  }

  public void setDiskUsageRateSecond(int diskUsageRateSecond) {
    this.diskUsageRateSecond = diskUsageRateSecond;
  }

  public int getDiskUsageSocketTimeoutMillisecond() {
    return diskUsageSocketTimeoutMillisecond;
  }

  public void setDiskUsageSocketTimeoutMillisecond(int diskUsageSocketTimeoutMillisecond) {
    this.diskUsageSocketTimeoutMillisecond = diskUsageSocketTimeoutMillisecond;
  }

  public String getDiskUsageUrl() {
    return diskUsageUrl;
  }

  public void setDiskUsageUrl(String diskUsageUrl) {
    this.diskUsageUrl = diskUsageUrl;
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

  public void setEthernetStatConnectTimeoutMillisecond(int ethernetStatConnectTimeoutMillisecond) {
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

  public int getOsConnectTimeoutMillisecond() {
    return osConnectTimeoutMillisecond;
  }

  public void setOsConnectTimeoutMillisecond(int osConnectTimeoutMillisecond) {
    this.osConnectTimeoutMillisecond = osConnectTimeoutMillisecond;
  }

  public int getOsConnectionRequestTimeoutMillisecond() {
    return osConnectionRequestTimeoutMillisecond;
  }

  public void setOsConnectionRequestTimeoutMillisecond(int osConnectionRequestTimeoutMillisecond) {
    this.osConnectionRequestTimeoutMillisecond = osConnectionRequestTimeoutMillisecond;
  }

  public int getOsRateSecond() {
    return osRateSecond;
  }

  public void setOsRateSecond(int osRateSecond) {
    this.osRateSecond = osRateSecond;
  }

  public int getOsSocketTimeoutMillisecond() {
    return osSocketTimeoutMillisecond;
  }

  public void setOsSocketTimeoutMillisecond(int osSocketTimeoutMillisecond) {
    this.osSocketTimeoutMillisecond = osSocketTimeoutMillisecond;
  }

  public String getOsUrl() {
    return osUrl;
  }

  public void setOsUrl(String osUrl) {
    this.osUrl = osUrl;
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

  public boolean isCpuCoreEnable() {
    return cpuCoreEnable;
  }

  public void setCpuCoreEnable(boolean cpuCoreEnable) {
    this.cpuCoreEnable = cpuCoreEnable;
  }

  public boolean isCpuCoreUsageEnable() {
    return cpuCoreUsageEnable;
  }

  public void setCpuCoreUsageEnable(boolean cpuCoreUsageEnable) {
    this.cpuCoreUsageEnable = cpuCoreUsageEnable;
  }

  public boolean isCpuEnable() {
    return cpuEnable;
  }

  public void setCpuEnable(boolean cpuEnable) {
    this.cpuEnable = cpuEnable;
  }

  public boolean isDiskEnable() {
    return diskEnable;
  }

  public void setDiskEnable(boolean diskEnable) {
    this.diskEnable = diskEnable;
  }

  public boolean isDiskUsageEnable() {
    return diskUsageEnable;
  }

  public void setDiskUsageEnable(boolean diskUsageEnable) {
    this.diskUsageEnable = diskUsageEnable;
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
    return osEnable;
  }

  public void setOsEnable(boolean osEnable) {
    this.osEnable = osEnable;
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
