package pwd.initializr.common.mw.monitor;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import pwd.initializr.common.http.HttpX;
import pwd.initializr.common.http.HttpXByHttpClient;
import pwd.initializr.common.http.HttpXConfig;
import pwd.initializr.common.mw.monitor.client.linux.HostClientOnLinux;
import pwd.initializr.common.mw.monitor.client.linux.HostCpuClientOnLinux;
import pwd.initializr.common.mw.monitor.client.linux.HostCpuStatClientOnLinux;
import pwd.initializr.common.mw.monitor.client.win.HostClientOnWin;
import pwd.initializr.common.mw.monitor.client.win.HostCpuClientOnWin;
import pwd.initializr.common.mw.monitor.client.win.HostCpuStatClientOnWin;
import pwd.initializr.common.utils.OSUtil;

/**
 * pwd.initializr.common.mw.montor@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-20 09:40
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Configuration
@ConditionalOnProperty(value = "monitor.cloud.client.enable", matchIfMissing = true)
public class MonitorClientAutoConfiguration {

  private ConfigurableEnvironment env;

  public MonitorClientAutoConfiguration(ConfigurableEnvironment env) {
    this.env = env;
  }

  @Bean
  @ConditionalOnProperty(value = "monitor.cloud.client.enable", matchIfMissing = true)
  public HttpX client(MonitorClientConfig monitorClientConfig) {
    HttpXConfig httpXConfig = new HttpXConfig();
    httpXConfig.setConnectionRequestTimeout(monitorClientConfig.getConnectionRequestTimeoutMillisecond());
    httpXConfig.setConnectTimeout(monitorClientConfig.getConnectTimeoutMillisecond());
    httpXConfig.setSocketTimeout(monitorClientConfig.getSocketTimeoutMillisecond());
    return new HttpXByHttpClient(httpXConfig);
  }

  @Bean
  @ConditionalOnMissingBean(value = MonitorClientConfig.class, search = SearchStrategy.CURRENT)
  public MonitorClientConfig monitorClientConfig() {

    String enable = getProperty("monitor.cloud.client.enable");
    String rateSecond = getProperty("monitor.cloud.client.rate.second");
    String threadMaxTotal = getProperty("monitor.cloud.client.thread.max.total");
    String threadPerRoute = getProperty("monitor.cloud.client.thread.per.route");
    String retryEnable = getProperty("monitor.cloud.client.retry.enable");
    String retryCount = getProperty("monitor.cloud.client.retry.count");

    String url = getProperty("monitor.cloud.client.url");
    String connectionRequestTimeoutMillisecond = getProperty("monitor.cloud.client.connection.request.timeout.millisecond");
    String connectTimeoutMillisecond = getProperty("monitor.cloud.client.connect.timeout.millisecond");
    String socketTimeoutMillisecond = getProperty("monitor.cloud.client.socket.timeout.millisecond");

    String hostEnable = getProperty("monitor.cloud.client.host.enable");
    String hostRateSecond = getProperty("monitor.cloud.client.host.rate.second");
    String hostUrl = getProperty("monitor.cloud.client.host.url");
    String hostConnectionRequestTimeoutMillisecond = getProperty("monitor.cloud.client.host.connection.request.timeout.millisecond");
    String hostConnectTimeoutMillisecond = getProperty("monitor.cloud.client.host.connect.timeout.millisecond");
    String hostSocketTimeoutMillisecond = getProperty("monitor.cloud.client.host.socket.timeout.millisecond");

    String cpuEnable = getProperty("monitor.cloud.client.host.cpu.enable");
    String cpuRateSecond = getProperty("monitor.cloud.client.host.cpu.rate.second");
    String cpuUrl = getProperty("monitor.cloud.client.host.cpu.url");
    String cpuConnectionRequestTimeoutMillisecond = getProperty("monitor.cloud.client.host.cpu.connection.request.timeout.millisecond");
    String cpuConnectTimeoutMillisecond = getProperty("monitor.cloud.client.host.cpu.connect.timeout.millisecond");
    String cpuSocketTimeoutMillisecond = getProperty("monitor.cloud.client.host.cpu.socket.timeout.millisecond");

    String cpuCoreEnable = getProperty("monitor.cloud.client.host.cpu.stat.enable");
    String cpuCoreRateSecond = getProperty("monitor.cloud.client.host.cpu.stat.rate.second");
    String cpuCoreUrl = getProperty("monitor.cloud.client.host.cpu.stat.url");
    String cpuCoreConnectionRequestTimeoutMillisecond = getProperty("monitor.cloud.client.host.cpu.stat.connection.request.timeout.millisecond");
    String cpuCoreConnectTimeoutMillisecond = getProperty("monitor.cloud.client.host.cpu.stat.connect.timeout.millisecond");
    String cpuCoreSocketTimeoutMillisecond = getProperty("monitor.cloud.client.host.cpu.stat.socket.timeout.millisecond");

    String cpuCoreUsageEnable = getProperty("monitor.cloud.client.cpu.core.usage.enable");
    String cpuCoreUsageRateSecond = getProperty("monitor.cloud.client.cpu.core.usage.rate.second");
    String cpuCoreUsageUrl = getProperty("monitor.cloud.client.cpu.core.usage.url");
    String cpuCoreUsageConnectionRequestTimeoutMillisecond = getProperty("monitor.cloud.client.cpu.core.usage.connection.request.timeout.millisecond");
    String cpuCoreUsageConnectTimeoutMillisecond = getProperty("monitor.cloud.client.cpu.core.usage.connect.timeout.millisecond");
    String cpuCoreUsageSocketTimeoutMillisecond = getProperty("monitor.cloud.client.cpu.core.usage.socket.timeout.millisecond");

    String diskEnable = getProperty("monitor.cloud.client.disk.enable");
    String diskRateSecond = getProperty("monitor.cloud.client.disk.rate.second");
    String diskUrl = getProperty("monitor.cloud.client.disk.url");
    String diskConnectionRequestTimeoutMillisecond = getProperty("monitor.cloud.client.disk.connection.request.timeout.millisecond");
    String diskConnectTimeoutMillisecond = getProperty("monitor.cloud.client.disk.connect.timeout.millisecond");
    String diskSocketTimeoutMillisecond = getProperty("monitor.cloud.client.disk.socket.timeout.millisecond");

    String diskUsageEnable = getProperty("monitor.cloud.client.disk.usage.enable");
    String diskUsageRateSecond = getProperty("monitor.cloud.client.disk.usage.rate.second");
    String diskUsageUrl = getProperty("monitor.cloud.client.disk.usage.url");
    String diskUsageConnectionRequestTimeoutMillisecond = getProperty("monitor.cloud.client.disk.usage.connection.request.timeout.millisecond");
    String diskUsageConnectTimeoutMillisecond = getProperty("monitor.cloud.client.disk.usage.connect.timeout.millisecond");
    String diskUsageSocketTimeoutMillisecond = getProperty("monitor.cloud.client.disk.usage.socket.timeout.millisecond");

    String ethernetEnable = getProperty("monitor.cloud.client.ethernet.enable");
    String ethernetRateSecond = getProperty("monitor.cloud.client.ethernet.rate.second");
    String ethernetUrl = getProperty("monitor.cloud.client.ethernet.url");
    String ethernetConnectionRequestTimeoutMillisecond = getProperty("monitor.cloud.client.ethernet.connection.request.timeout.millisecond");
    String ethernetConnectTimeoutMillisecond = getProperty("monitor.cloud.client.ethernet.connect.timeout.millisecond");
    String ethernetSocketTimeoutMillisecond = getProperty("monitor.cloud.client.ethernet.socket.timeout.millisecond");

    String ethernetStatEnable = getProperty("monitor.cloud.client.ethernet.stat.enable");
    String ethernetStatRateSecond = getProperty("monitor.cloud.client.ethernet.stat.rate.second");
    String ethernetStatUrl = getProperty("monitor.cloud.client.ethernet.stat.url");
    String ethernetStatConnectionRequestTimeoutMillisecond = getProperty("monitor.cloud.client.ethernet.stat.connection.request.timeout.millisecond");
    String ethernetStatConnectTimeoutMillisecond = getProperty("monitor.cloud.client.ethernet.stat.connect.timeout.millisecond");
    String ethernetStatSocketTimeoutMillisecond = getProperty("monitor.cloud.client.ethernet.stat.socket.timeout.millisecond");

    String memoryEnable = getProperty("monitor.cloud.client.memory.enable");
    String memoryRateSecond = getProperty("monitor.cloud.client.memory.rate.second");
    String memoryUrl = getProperty("monitor.cloud.client.memory.url");
    String memoryConnectionRequestTimeoutMillisecond = getProperty("monitor.cloud.client.memory.connection.request.timeout.millisecond");
    String memoryConnectTimeoutMillisecond = getProperty("monitor.cloud.client.memory.connect.timeout.millisecond");
    String memorySocketTimeoutMillisecond = getProperty("monitor.cloud.client.memory.socket.timeout.millisecond");

    String memorySwapEnable = getProperty("monitor.cloud.client.memory.swap.enable");
    String memorySwapRateSecond = getProperty("monitor.cloud.client.memory.swap.rate.second");
    String memorySwapUrl = getProperty("monitor.cloud.client.memory.swap.url");
    String memorySwapConnectionRequestTimeoutMillisecond = getProperty("monitor.cloud.client.memory.swap.connection.request.timeout.millisecond");
    String memorySwapConnectTimeoutMillisecond = getProperty("monitor.cloud.client.memory.swap.connect.timeout.millisecond");
    String memorySwapSocketTimeoutMillisecond = getProperty("monitor.cloud.client.memory.swap.socket.timeout.millisecond");

    MonitorClientConfig instance = new MonitorClientConfig();

    instance.setEnable(Boolean.parseBoolean(StringUtils.isBlank(enable) ? "true" : enable));
    instance.setRateSecond(Integer.parseInt(StringUtils.isBlank(rateSecond) ? "300" : rateSecond));

    instance.setThreadMaxTotal(Integer.parseInt(StringUtils.isBlank(threadMaxTotal) ? "22" : threadMaxTotal));
    instance.setThreadPerRoute(Integer.parseInt(StringUtils.isBlank(threadPerRoute) ? "2" : threadPerRoute));
    instance.setRetryEnable(Boolean.parseBoolean(StringUtils.isBlank(retryEnable) ? "false" : retryEnable));
    instance.setRetryCount(Integer.parseInt(StringUtils.isBlank(retryCount) ? "0" : retryCount));

    instance.setUrl(StringUtils.isBlank(url) ? "http://127.0.0.1:80" : url);
    instance.setConnectionRequestTimeoutMillisecond(Integer.parseInt(StringUtils.isBlank(connectionRequestTimeoutMillisecond) ? "1000" : connectionRequestTimeoutMillisecond));
    instance.setConnectTimeoutMillisecond(Integer.parseInt(StringUtils.isBlank(connectTimeoutMillisecond) ? "3000" : connectTimeoutMillisecond));
    instance.setSocketTimeoutMillisecond(Integer.parseInt(StringUtils.isBlank(socketTimeoutMillisecond) ? "2000" : socketTimeoutMillisecond));

    instance.setHostEnable(StringUtils.isBlank(hostEnable) ? instance.isEnable() : Boolean.parseBoolean(hostEnable));
    instance.setHostRateSecond(StringUtils.isBlank(hostRateSecond) ? instance.getRateSecond() : Integer.parseInt(hostRateSecond));
    instance.setHostUrl(StringUtils.isBlank(hostUrl) ? url : hostUrl);
    instance.setHostConnectionRequestTimeoutMillisecond(StringUtils.isBlank(hostConnectionRequestTimeoutMillisecond) ? instance.getConnectionRequestTimeoutMillisecond() : Integer.parseInt(hostConnectionRequestTimeoutMillisecond));
    instance.setHostConnectTimeoutMillisecond(StringUtils.isBlank(hostConnectTimeoutMillisecond) ? instance.getConnectTimeoutMillisecond() : Integer.parseInt(hostConnectTimeoutMillisecond));
    instance.setHostSocketTimeoutMillisecond(StringUtils.isBlank(hostSocketTimeoutMillisecond) ? instance.getSocketTimeoutMillisecond() : Integer.parseInt(hostSocketTimeoutMillisecond));

    instance.setCpuEnable(StringUtils.isBlank(cpuEnable) ? instance.isEnable() : Boolean.parseBoolean(cpuEnable));
    instance.setCpuRateSecond(StringUtils.isBlank(cpuRateSecond) ? instance.getRateSecond() : Integer.parseInt(cpuRateSecond));
    instance.setCpuUrl(StringUtils.isBlank(cpuUrl) ? url : cpuUrl);
    instance.setCpuConnectionRequestTimeoutMillisecond(StringUtils.isBlank(cpuConnectionRequestTimeoutMillisecond) ? instance.getConnectionRequestTimeoutMillisecond() : Integer.parseInt(cpuConnectionRequestTimeoutMillisecond));
    instance.setCpuConnectTimeoutMillisecond(StringUtils.isBlank(cpuConnectTimeoutMillisecond) ? instance.getConnectTimeoutMillisecond() : Integer.parseInt(cpuConnectTimeoutMillisecond));
    instance.setCpuSocketTimeoutMillisecond(StringUtils.isBlank(cpuSocketTimeoutMillisecond) ? instance.getSocketTimeoutMillisecond() : Integer.parseInt(cpuSocketTimeoutMillisecond));

    instance.setCpuStatEnable(StringUtils.isBlank(cpuCoreEnable) ? instance.isEnable() : Boolean.parseBoolean(cpuCoreEnable));
    instance.setCpuStatRateSecond(StringUtils.isBlank(cpuCoreRateSecond) ? instance.getRateSecond() : Integer.parseInt(cpuCoreRateSecond));
    instance.setCpuStatUrl(StringUtils.isBlank(cpuCoreUrl) ? url : cpuCoreUrl);
    instance.setCpuStatConnectionRequestTimeoutMillisecond(StringUtils.isBlank(cpuCoreConnectionRequestTimeoutMillisecond) ? instance.getConnectionRequestTimeoutMillisecond() : Integer.parseInt(cpuCoreConnectionRequestTimeoutMillisecond));
    instance.setCpuStatConnectTimeoutMillisecond(StringUtils.isBlank(cpuCoreConnectTimeoutMillisecond) ? instance.getConnectTimeoutMillisecond() : Integer.parseInt(cpuCoreConnectTimeoutMillisecond));
    instance.setCpuStatSocketTimeoutMillisecond(StringUtils.isBlank(cpuCoreSocketTimeoutMillisecond) ? instance.getSocketTimeoutMillisecond() : Integer.parseInt(cpuCoreSocketTimeoutMillisecond));

    instance.setDiskEnable(StringUtils.isBlank(diskEnable) ? instance.isEnable() : Boolean.parseBoolean(diskEnable));
    instance.setDiskRateSecond(StringUtils.isBlank(diskRateSecond) ? instance.getRateSecond() : Integer.parseInt(diskRateSecond));
    instance.setDiskUrl(StringUtils.isBlank(diskUrl) ? url : diskUrl);
    instance.setDiskConnectionRequestTimeoutMillisecond(StringUtils.isBlank(diskConnectionRequestTimeoutMillisecond) ? instance.getConnectionRequestTimeoutMillisecond() : Integer.parseInt(diskConnectionRequestTimeoutMillisecond));
    instance.setDiskConnectTimeoutMillisecond(StringUtils.isBlank(diskConnectTimeoutMillisecond) ? instance.getConnectTimeoutMillisecond() : Integer.parseInt(diskConnectTimeoutMillisecond));
    instance.setDiskSocketTimeoutMillisecond(StringUtils.isBlank(diskSocketTimeoutMillisecond) ? instance.getSocketTimeoutMillisecond() : Integer.parseInt(diskSocketTimeoutMillisecond));

    instance.setDiskUsageEnable(StringUtils.isBlank(diskUsageEnable) ? instance.isEnable() : Boolean.parseBoolean(diskUsageEnable));
    instance.setDiskUsageRateSecond(StringUtils.isBlank(diskUsageRateSecond) ? instance.getRateSecond() : Integer.parseInt(diskUsageRateSecond));
    instance.setDiskUsageUrl(StringUtils.isBlank(diskUsageUrl) ? url : diskUsageUrl);
    instance.setDiskUsageConnectionRequestTimeoutMillisecond(StringUtils.isBlank(diskUsageConnectionRequestTimeoutMillisecond) ? instance.getConnectionRequestTimeoutMillisecond() : Integer.parseInt(diskUsageConnectionRequestTimeoutMillisecond));
    instance.setDiskUsageConnectTimeoutMillisecond(StringUtils.isBlank(diskUsageConnectTimeoutMillisecond) ? instance.getConnectTimeoutMillisecond() : Integer.parseInt(diskUsageConnectTimeoutMillisecond));
    instance.setDiskUsageSocketTimeoutMillisecond(StringUtils.isBlank(diskUsageSocketTimeoutMillisecond) ? instance.getSocketTimeoutMillisecond() : Integer.parseInt(diskUsageSocketTimeoutMillisecond));

    instance.setEthernetEnable(StringUtils.isBlank(ethernetEnable) ? instance.isEnable() : Boolean.parseBoolean(ethernetEnable));
    instance.setEthernetRateSecond(StringUtils.isBlank(ethernetRateSecond) ? instance.getRateSecond() : Integer.parseInt(ethernetRateSecond));
    instance.setEthernetUrl(StringUtils.isBlank(ethernetUrl) ? url : ethernetUrl);
    instance.setEthernetConnectionRequestTimeoutMillisecond(StringUtils.isBlank(ethernetConnectionRequestTimeoutMillisecond) ? instance.getConnectionRequestTimeoutMillisecond() : Integer.parseInt(ethernetConnectionRequestTimeoutMillisecond));
    instance.setEthernetConnectTimeoutMillisecond(StringUtils.isBlank(ethernetConnectTimeoutMillisecond) ? instance.getConnectTimeoutMillisecond() : Integer.parseInt(ethernetConnectTimeoutMillisecond));
    instance.setEthernetSocketTimeoutMillisecond(StringUtils.isBlank(ethernetSocketTimeoutMillisecond) ? instance.getSocketTimeoutMillisecond() : Integer.parseInt(ethernetSocketTimeoutMillisecond));

    instance.setEthernetStatEnable(StringUtils.isBlank(ethernetStatEnable) ? instance.isEnable() : Boolean.parseBoolean(ethernetStatEnable));
    instance.setEthernetStatRateSecond(StringUtils.isBlank(ethernetStatRateSecond) ? instance.getRateSecond() : Integer.parseInt(ethernetStatRateSecond));
    instance.setEthernetStatUrl(StringUtils.isBlank(ethernetStatUrl) ? url : ethernetStatUrl);
    instance.setEthernetStatConnectionRequestTimeoutMillisecond(StringUtils.isBlank(ethernetStatConnectionRequestTimeoutMillisecond) ? instance.getConnectionRequestTimeoutMillisecond() : Integer.parseInt(ethernetStatConnectionRequestTimeoutMillisecond));
    instance.setEthernetStatConnectTimeoutMillisecond(StringUtils.isBlank(ethernetStatConnectTimeoutMillisecond) ? instance.getConnectTimeoutMillisecond() : Integer.parseInt(ethernetStatConnectTimeoutMillisecond));
    instance.setEthernetStatSocketTimeoutMillisecond(StringUtils.isBlank(ethernetStatSocketTimeoutMillisecond) ? instance.getSocketTimeoutMillisecond() : Integer.parseInt(ethernetStatSocketTimeoutMillisecond));

    instance.setMemoryEnable(StringUtils.isBlank(memoryEnable) ? instance.isEnable() : Boolean.parseBoolean(memoryEnable));
    instance.setMemoryRateSecond(StringUtils.isBlank(memoryRateSecond) ? instance.getRateSecond() : Integer.parseInt(memoryRateSecond));
    instance.setMemoryUrl(StringUtils.isBlank(memoryUrl) ? url : memoryUrl);
    instance.setMemoryConnectionRequestTimeoutMillisecond(StringUtils.isBlank(memoryConnectionRequestTimeoutMillisecond) ? instance.getConnectionRequestTimeoutMillisecond() : Integer.parseInt(memoryConnectionRequestTimeoutMillisecond));
    instance.setMemoryConnectTimeoutMillisecond(StringUtils.isBlank(memoryConnectTimeoutMillisecond) ? instance.getConnectTimeoutMillisecond() : Integer.parseInt(memoryConnectTimeoutMillisecond));
    instance.setMemorySocketTimeoutMillisecond(StringUtils.isBlank(memorySocketTimeoutMillisecond) ? instance.getSocketTimeoutMillisecond() : Integer.parseInt(memorySocketTimeoutMillisecond));

    instance.setMemorySwapEnable(StringUtils.isBlank(memorySwapEnable) ? instance.isEnable() : Boolean.parseBoolean(memorySwapEnable));
    instance.setMemorySwapRateSecond(StringUtils.isBlank(memorySwapRateSecond) ? instance.getRateSecond() : Integer.parseInt(memorySwapRateSecond));
    instance.setMemorySwapUrl(StringUtils.isBlank(memorySwapUrl) ? url : memorySwapUrl);
    instance.setMemorySwapConnectionRequestTimeoutMillisecond(StringUtils.isBlank(memorySwapConnectionRequestTimeoutMillisecond) ? instance.getConnectionRequestTimeoutMillisecond() : Integer.parseInt(memorySwapConnectionRequestTimeoutMillisecond));
    instance.setMemorySwapConnectTimeoutMillisecond(StringUtils.isBlank(memorySwapConnectTimeoutMillisecond) ? instance.getConnectTimeoutMillisecond() : Integer.parseInt(memorySwapConnectTimeoutMillisecond));
    instance.setMemorySwapSocketTimeoutMillisecond(StringUtils.isBlank(memorySwapSocketTimeoutMillisecond) ? instance.getSocketTimeoutMillisecond() : Integer.parseInt(memorySwapSocketTimeoutMillisecond));

    return instance;
  }

  private String getProperty(String property) {
    return this.env.containsProperty(property) ? this.env.getProperty(property) : "";
  }

  @Bean
  @ConditionalOnProperty(value = "monitor.cloud.client.host.enable", matchIfMissing = true)
  public MonitorClient hostClient(MonitorClientConfig monitorClientConfig) {
    if (OSUtil.isLinux()) {
      return new HostClientOnLinux(monitorClientConfig);
    } else if (OSUtil.isWindows()) {
      return new HostClientOnWin(monitorClientConfig);
    }
    throw new RuntimeException("incompatible OS " + OSUtil.getOSName());
  }

  @Bean
  @ConditionalOnProperty(value = "monitor.cloud.client.host.cpu.enable", matchIfMissing = true)
  public MonitorClient cpuClient(MonitorClientConfig monitorClientConfig) {
    if (OSUtil.isLinux()) {
      return new HostCpuClientOnLinux(monitorClientConfig);
    } else if (OSUtil.isWindows()) {
      return new HostCpuClientOnWin(monitorClientConfig);
    }
    throw new RuntimeException("incompatible OS " + OSUtil.getOSName());
  }

  @Bean
  @ConditionalOnProperty(value = "monitor.cloud.client.host.cpu.stat.enable", matchIfMissing = true)
  public MonitorClient cpuStatClient(MonitorClientConfig monitorClientConfig) {
    if (OSUtil.isLinux()) {
      return new HostCpuStatClientOnLinux(monitorClientConfig);
    } else if (OSUtil.isWindows()) {
      return new HostCpuStatClientOnWin(monitorClientConfig);
    }
    throw new RuntimeException("incompatible OS " + OSUtil.getOSName());
  }
//
//  @Bean
//  @ConditionalOnProperty(value = "monitor.cloud.client.who.enable", matchIfMissing = true)
//  public MonitorClient whoClient(MonitorClientConfig monitorClientConfig) {
//    return new WhoClient(monitorClientConfig);
//  }
//

//
//  @Bean
//  @ConditionalOnProperty(value = "monitor.cloud.client.cpu.core.enable", matchIfMissing = true)
//  public MonitorClient cpuCoreClient(MonitorClientConfig monitorClientConfig) {
//    return new CpuCoreClient(monitorClientConfig);
//  }
//
//  @Bean
//  @ConditionalOnProperty(value = "monitor.cloud.client.cpu.core.usage.enable", matchIfMissing = true)
//  public MonitorClient cpuCoreUsageClient(MonitorClientConfig monitorClientConfig) {
//    return new CpuCoreUsageClient(monitorClientConfig);
//  }
//
//  @Bean
//  @ConditionalOnProperty(value = "monitor.cloud.client.disk.enable", matchIfMissing = true)
//  public MonitorClient diskClient(MonitorClientConfig monitorClientConfig) {
//    return new DiskClient(monitorClientConfig);
//  }
//
//  @Bean
//  @ConditionalOnProperty(value = "monitor.cloud.client.disk.usage.enable", matchIfMissing = true)
//  public MonitorClient diskUsageClient(MonitorClientConfig monitorClientConfig) {
//    return new DiskUsageClient(monitorClientConfig);
//  }
//
//  @Bean
//  @ConditionalOnProperty(value = "monitor.cloud.client.ethernet.enable", matchIfMissing = true)
//  public MonitorClient ethernetClient(MonitorClientConfig monitorClientConfig) {
//    return new EthernetClient(monitorClientConfig);
//  }
//
//  @Bean
//  @ConditionalOnProperty(value = "monitor.cloud.client.ethernet.stat.enable", matchIfMissing = true)
//  public MonitorClient ethernetStatClient(MonitorClientConfig monitorClientConfig) {
//    return new EthernetStatClient(monitorClientConfig);
//  }
//
//  @Bean
//  @ConditionalOnProperty(value = "monitor.cloud.client.memory.enable", matchIfMissing = true)
//  public MonitorClient memoryClient(MonitorClientConfig monitorClientConfig) {
//    return new MemoryClient(monitorClientConfig);
//  }
//
//  @Bean
//  @ConditionalOnProperty(value = "monitor.cloud.client.memory.swap.enable", matchIfMissing = true)
//  public MonitorClient memorySwapClient(MonitorClientConfig monitorClientConfig) {
//    return new MemorySwapClient(monitorClientConfig);
//  }

}
