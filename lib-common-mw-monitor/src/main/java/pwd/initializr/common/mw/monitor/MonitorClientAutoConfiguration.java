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
import pwd.initializr.common.mw.monitor.client.linux.HostDiskStatClientOnLinux;
import pwd.initializr.common.mw.monitor.client.linux.HostEthernetStatClientOnLinux;
import pwd.initializr.common.mw.monitor.client.linux.HostLoadStatClientOnLinux;
import pwd.initializr.common.mw.monitor.client.linux.HostLoggedStatClientOnLinux;
import pwd.initializr.common.mw.monitor.client.linux.HostMemoryStatClientOnLinux;
import pwd.initializr.common.mw.monitor.client.win.HostClientOnWin;
import pwd.initializr.common.mw.monitor.client.win.HostCpuClientOnWin;
import pwd.initializr.common.mw.monitor.client.win.HostCpuStatClientOnWin;
import pwd.initializr.common.mw.monitor.client.win.HostDiskStatClientOnWin;
import pwd.initializr.common.mw.monitor.client.win.HostEthernetStatClientOnWin;
import pwd.initializr.common.mw.monitor.client.win.HostLoadStatClientOnWin;
import pwd.initializr.common.mw.monitor.client.win.HostLoggedStatClientOnWin;
import pwd.initializr.common.mw.monitor.client.win.HostMemoryStatClientOnWin;
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

    String cpuStatEnable = getProperty("monitor.cloud.client.host.cpu.stat.enable");
    String cpuStatRateSecond = getProperty("monitor.cloud.client.host.cpu.stat.rate.second");
    String cpuStatUrl = getProperty("monitor.cloud.client.host.cpu.stat.url");
    String cpuStatConnectionRequestTimeoutMillisecond = getProperty("monitor.cloud.client.host.cpu.stat.connection.request.timeout.millisecond");
    String cpuStatConnectTimeoutMillisecond = getProperty("monitor.cloud.client.host.cpu.stat.connect.timeout.millisecond");
    String cpuStatSocketTimeoutMillisecond = getProperty("monitor.cloud.client.host.cpu.stat.socket.timeout.millisecond");

    String diskStatEnable = getProperty("monitor.cloud.client.host.disk.stat.enable");
    String diskStatRateSecond = getProperty("monitor.cloud.client.host.disk.stat.rate.second");
    String diskStatUrl = getProperty("monitor.cloud.client.host.disk.stat.url");
    String diskStatConnectionRequestTimeoutMillisecond = getProperty("monitor.cloud.client.host.disk.stat.connection.request.timeout.millisecond");
    String diskStatConnectTimeoutMillisecond = getProperty("monitor.cloud.client.host.disk.stat.connect.timeout.millisecond");
    String diskStatSocketTimeoutMillisecond = getProperty("monitor.cloud.client.host.disk.stat.socket.timeout.millisecond");

    String ethernetStatEnable = getProperty("monitor.cloud.client.host.ethernet.stat.enable");
    String ethernetStatRateSecond = getProperty("monitor.cloud.client.host.ethernet.stat.rate.second");
    String ethernetStatUrl = getProperty("monitor.cloud.client.host.ethernet.stat.url");
    String ethernetStatConnectionRequestTimeoutMillisecond = getProperty("monitor.cloud.client.host.ethernet.stat.connection.request.timeout.millisecond");
    String ethernetStatConnectTimeoutMillisecond = getProperty("monitor.cloud.client.host.ethernet.stat.connect.timeout.millisecond");
    String ethernetStatSocketTimeoutMillisecond = getProperty("monitor.cloud.client.host.ethernet.stat.socket.timeout.millisecond");

    String loadStatEnable = getProperty("monitor.cloud.client.host.load.stat.enable");
    String loadStatRateSecond = getProperty("monitor.cloud.client.host.load.stat.rate.second");
    String loadStatUrl = getProperty("monitor.cloud.client.host.load.stat.url");
    String loadStatConnectionRequestTimeoutMillisecond = getProperty("monitor.cloud.client.host.load.stat.connection.request.timeout.millisecond");
    String loadStatConnectTimeoutMillisecond = getProperty("monitor.cloud.client.host.load.stat.connect.timeout.millisecond");
    String loadStatSocketTimeoutMillisecond = getProperty("monitor.cloud.client.host.load.stat.socket.timeout.millisecond");

    String loggedStatEnable = getProperty("monitor.cloud.client.host.logged.stat.enable");
    String loggedStatRateSecond = getProperty("monitor.cloud.client.host.logged.stat.rate.second");
    String loggedStatUrl = getProperty("monitor.cloud.client.host.logged.stat.url");
    String loggedStatConnectionRequestTimeoutMillisecond = getProperty("monitor.cloud.client.host.logged.stat.connection.request.timeout.millisecond");
    String loggedStatConnectTimeoutMillisecond = getProperty("monitor.cloud.client.host.logged.stat.connect.timeout.millisecond");
    String loggedStatSocketTimeoutMillisecond = getProperty("monitor.cloud.client.host.logged.stat.socket.timeout.millisecond");

    String memoryStatEnable = getProperty("monitor.cloud.client.host.memory.stat.enable");
    String memoryStatRateSecond = getProperty("monitor.cloud.client.host.memory.stat.rate.second");
    String memoryStatUrl = getProperty("monitor.cloud.client.host.memory.stat.url");
    String memoryStatConnectionRequestTimeoutMillisecond = getProperty("monitor.cloud.client.host.memory.stat.connection.request.timeout.millisecond");
    String memoryStatConnectTimeoutMillisecond = getProperty("monitor.cloud.client.host.memory.stat.connect.timeout.millisecond");
    String memoryStatSocketTimeoutMillisecond = getProperty("monitor.cloud.client.host.memory.stat.socket.timeout.millisecond");

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

    instance.setCpuStatEnable(StringUtils.isBlank(cpuStatEnable) ? instance.isEnable() : Boolean.parseBoolean(cpuStatEnable));
    instance.setCpuStatRateSecond(StringUtils.isBlank(cpuStatRateSecond) ? instance.getRateSecond() : Integer.parseInt(cpuStatRateSecond));
    instance.setCpuStatUrl(StringUtils.isBlank(cpuStatUrl) ? url : cpuStatUrl);
    instance.setCpuStatConnectionRequestTimeoutMillisecond(StringUtils.isBlank(cpuStatConnectionRequestTimeoutMillisecond) ? instance.getConnectionRequestTimeoutMillisecond() : Integer.parseInt(cpuStatConnectionRequestTimeoutMillisecond));
    instance.setCpuStatConnectTimeoutMillisecond(StringUtils.isBlank(cpuStatConnectTimeoutMillisecond) ? instance.getConnectTimeoutMillisecond() : Integer.parseInt(cpuStatConnectTimeoutMillisecond));
    instance.setCpuStatSocketTimeoutMillisecond(StringUtils.isBlank(cpuStatSocketTimeoutMillisecond) ? instance.getSocketTimeoutMillisecond() : Integer.parseInt(cpuStatSocketTimeoutMillisecond));

    instance.setDiskStatEnable(StringUtils.isBlank(diskStatEnable) ? instance.isEnable() : Boolean.parseBoolean(diskStatEnable));
    instance.setDiskStatRateSecond(StringUtils.isBlank(diskStatRateSecond) ? instance.getRateSecond() : Integer.parseInt(diskStatRateSecond));
    instance.setDiskStatUrl(StringUtils.isBlank(diskStatUrl) ? url : diskStatUrl);
    instance.setDiskStatConnectionRequestTimeoutMillisecond(StringUtils.isBlank(diskStatConnectionRequestTimeoutMillisecond) ? instance.getConnectionRequestTimeoutMillisecond() : Integer.parseInt(diskStatConnectionRequestTimeoutMillisecond));
    instance.setDiskStatConnectTimeoutMillisecond(StringUtils.isBlank(diskStatConnectTimeoutMillisecond) ? instance.getConnectTimeoutMillisecond() : Integer.parseInt(diskStatConnectTimeoutMillisecond));
    instance.setDiskStatSocketTimeoutMillisecond(StringUtils.isBlank(diskStatSocketTimeoutMillisecond) ? instance.getSocketTimeoutMillisecond() : Integer.parseInt(diskStatSocketTimeoutMillisecond));

    instance.setEthernetStatEnable(StringUtils.isBlank(ethernetStatEnable) ? instance.isEnable() : Boolean.parseBoolean(ethernetStatEnable));
    instance.setEthernetStatRateSecond(StringUtils.isBlank(ethernetStatRateSecond) ? instance.getRateSecond() : Integer.parseInt(ethernetStatRateSecond));
    instance.setEthernetStatUrl(StringUtils.isBlank(ethernetStatUrl) ? url : ethernetStatUrl);
    instance.setEthernetStatConnectionRequestTimeoutMillisecond(StringUtils.isBlank(ethernetStatConnectionRequestTimeoutMillisecond) ? instance.getConnectionRequestTimeoutMillisecond() : Integer.parseInt(ethernetStatConnectionRequestTimeoutMillisecond));
    instance.setEthernetStatConnectTimeoutMillisecond(StringUtils.isBlank(ethernetStatConnectTimeoutMillisecond) ? instance.getConnectTimeoutMillisecond() : Integer.parseInt(ethernetStatConnectTimeoutMillisecond));
    instance.setEthernetStatSocketTimeoutMillisecond(StringUtils.isBlank(ethernetStatSocketTimeoutMillisecond) ? instance.getSocketTimeoutMillisecond() : Integer.parseInt(ethernetStatSocketTimeoutMillisecond));

    instance.setLoadStatEnable(StringUtils.isBlank(loadStatEnable) ? instance.isEnable() : Boolean.parseBoolean(loadStatEnable));
    instance.setLoadStatRateSecond(StringUtils.isBlank(loadStatRateSecond) ? instance.getRateSecond() : Integer.parseInt(loadStatRateSecond));
    instance.setLoadStatUrl(StringUtils.isBlank(loadStatUrl) ? url : loadStatUrl);
    instance.setLoadStatConnectionRequestTimeoutMillisecond(StringUtils.isBlank(loadStatConnectionRequestTimeoutMillisecond) ? instance.getConnectionRequestTimeoutMillisecond() : Integer.parseInt(loadStatConnectionRequestTimeoutMillisecond));
    instance.setLoadStatConnectTimeoutMillisecond(StringUtils.isBlank(loadStatConnectTimeoutMillisecond) ? instance.getConnectTimeoutMillisecond() : Integer.parseInt(loadStatConnectTimeoutMillisecond));
    instance.setLoadStatSocketTimeoutMillisecond(StringUtils.isBlank(loadStatSocketTimeoutMillisecond) ? instance.getSocketTimeoutMillisecond() : Integer.parseInt(loadStatSocketTimeoutMillisecond));

    instance.setLoggedStatEnable(StringUtils.isBlank(loggedStatEnable) ? instance.isEnable() : Boolean.parseBoolean(loggedStatEnable));
    instance.setLoggedStatRateSecond(StringUtils.isBlank(loggedStatRateSecond) ? instance.getRateSecond() : Integer.parseInt(loggedStatRateSecond));
    instance.setLoggedStatUrl(StringUtils.isBlank(loggedStatUrl) ? url : loggedStatUrl);
    instance.setLoggedStatConnectionRequestTimeoutMillisecond(StringUtils.isBlank(loggedStatConnectionRequestTimeoutMillisecond) ? instance.getConnectionRequestTimeoutMillisecond() : Integer.parseInt(loggedStatConnectionRequestTimeoutMillisecond));
    instance.setLoggedStatConnectTimeoutMillisecond(StringUtils.isBlank(loggedStatConnectTimeoutMillisecond) ? instance.getConnectTimeoutMillisecond() : Integer.parseInt(loggedStatConnectTimeoutMillisecond));
    instance.setLoggedStatSocketTimeoutMillisecond(StringUtils.isBlank(loggedStatSocketTimeoutMillisecond) ? instance.getSocketTimeoutMillisecond() : Integer.parseInt(loggedStatSocketTimeoutMillisecond));

    instance.setMemoryStatEnable(StringUtils.isBlank(memoryStatEnable) ? instance.isEnable() : Boolean.parseBoolean(memoryStatEnable));
    instance.setMemoryStatRateSecond(StringUtils.isBlank(memoryStatRateSecond) ? instance.getRateSecond() : Integer.parseInt(memoryStatRateSecond));
    instance.setMemoryStatUrl(StringUtils.isBlank(memoryStatUrl) ? url : memoryStatUrl);
    instance.setMemoryStatConnectionRequestTimeoutMillisecond(StringUtils.isBlank(memoryStatConnectionRequestTimeoutMillisecond) ? instance.getConnectionRequestTimeoutMillisecond() : Integer.parseInt(memoryStatConnectionRequestTimeoutMillisecond));
    instance.setMemoryStatConnectTimeoutMillisecond(StringUtils.isBlank(memoryStatConnectTimeoutMillisecond) ? instance.getConnectTimeoutMillisecond() : Integer.parseInt(memoryStatConnectTimeoutMillisecond));
    instance.setMemoryStatSocketTimeoutMillisecond(StringUtils.isBlank(memoryStatSocketTimeoutMillisecond) ? instance.getSocketTimeoutMillisecond() : Integer.parseInt(memoryStatSocketTimeoutMillisecond));

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

  @Bean
  @ConditionalOnProperty(value = "monitor.cloud.client.host.disk.stat.enable", matchIfMissing = true)
  public MonitorClient diskStatClient(MonitorClientConfig monitorClientConfig) {
    if (OSUtil.isLinux()) {
      return new HostDiskStatClientOnLinux(monitorClientConfig);
    } else if (OSUtil.isWindows()) {
      return new HostDiskStatClientOnWin(monitorClientConfig);
    }
    throw new RuntimeException("incompatible OS " + OSUtil.getOSName());
  }

  @Bean
  @ConditionalOnProperty(value = "monitor.cloud.client.host.ethernet.stat.enable", matchIfMissing = true)
  public MonitorClient ethernetStatClient(MonitorClientConfig monitorClientConfig) {
    if (OSUtil.isLinux()) {
      return new HostEthernetStatClientOnLinux(monitorClientConfig);
    } else if (OSUtil.isWindows()) {
      return new HostEthernetStatClientOnWin(monitorClientConfig);
    }
    throw new RuntimeException("incompatible OS " + OSUtil.getOSName());
  }

  @Bean
  @ConditionalOnProperty(value = "monitor.cloud.client.host.load.stat.enable", matchIfMissing = true)
  public MonitorClient loadStatClient(MonitorClientConfig monitorClientConfig) {
    if (OSUtil.isLinux()) {
      return new HostLoadStatClientOnLinux(monitorClientConfig);
    } else if (OSUtil.isWindows()) {
      return new HostLoadStatClientOnWin(monitorClientConfig);
    }
    throw new RuntimeException("incompatible OS " + OSUtil.getOSName());
  }

  @Bean
  @ConditionalOnProperty(value = "monitor.cloud.client.host.logged.stat.enable", matchIfMissing = true)
  public MonitorClient loggedStatClient(MonitorClientConfig monitorClientConfig) {
    if (OSUtil.isLinux()) {
      return new HostLoggedStatClientOnLinux(monitorClientConfig);
    } else if (OSUtil.isWindows()) {
      return new HostLoggedStatClientOnWin(monitorClientConfig);
    }
    throw new RuntimeException("incompatible OS " + OSUtil.getOSName());
  }

  @Bean
  @ConditionalOnProperty(value = "monitor.cloud.client.host.memory.stat.enable", matchIfMissing = true)
  public MonitorClient memoryStatClient(MonitorClientConfig monitorClientConfig) {
    if (OSUtil.isLinux()) {
      return new HostMemoryStatClientOnLinux(monitorClientConfig);
    } else if (OSUtil.isWindows()) {
      return new HostMemoryStatClientOnWin(monitorClientConfig);
    }
    throw new RuntimeException("incompatible OS " + OSUtil.getOSName());
  }

}
