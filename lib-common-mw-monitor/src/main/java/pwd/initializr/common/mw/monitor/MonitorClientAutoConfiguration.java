package pwd.initializr.common.mw.monitor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import pwd.initializr.common.http.HttpX;
import pwd.initializr.common.http.HttpXByHttpClient;
import pwd.initializr.common.http.HttpXConfig;
import pwd.initializr.common.mw.monitor.client.CpuClient;
import pwd.initializr.common.mw.monitor.client.OSClient;

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
    String connectionRequestTimeoutMillisecond = getProperty("monitor.cloud.client.connection.request.timeout.millisecond");
    String connectTimeoutMillisecond = getProperty("monitor.cloud.client.connect.timeout.millisecond");
    String socketTimeoutMillisecond = getProperty("monitor.cloud.client.socket.timeout.millisecond");

    String osEnable = getProperty("monitor.cloud.client.os.enable");
    String osRateSecond = getProperty("monitor.cloud.client.os.rate.second");
    String osUrl = getProperty("monitor.cloud.client.os.url");
    String osConnectionRequestTimeoutMillisecond = getProperty("monitor.cloud.client.os.connection.request.timeout.millisecond");
    String osConnectTimeoutMillisecond = getProperty("monitor.cloud.client.os.connect.timeout.millisecond");
    String osSocketTimeoutMillisecond = getProperty("monitor.cloud.client.os.socket.timeout.millisecond");

    String whoEnable = getProperty("monitor.cloud.client.who.enable");
    String whoRateSecond = getProperty("monitor.cloud.client.who.rate.second");
    String whoUrl = getProperty("monitor.cloud.client.who.url");
    String whoConnectionRequestTimeoutMillisecond = getProperty("monitor.cloud.client.who.connection.request.timeout.millisecond");
    String whoConnectTimeoutMillisecond = getProperty("monitor.cloud.client.who.connect.timeout.millisecond");
    String whoSocketTimeoutMillisecond = getProperty("monitor.cloud.client.who.socket.timeout.millisecond");

    String cpuEnable = getProperty("monitor.cloud.client.cpu.enable");
    String cpuRateSecond = getProperty("monitor.cloud.client.cpu.rate.second");
    String cpuUrl = getProperty("monitor.cloud.client.cpu.url");
    String cpuConnectionRequestTimeoutMillisecond = getProperty("monitor.cloud.client.cpu.connection.request.timeout.millisecond");
    String cpuConnectTimeoutMillisecond = getProperty("monitor.cloud.client.cpu.connect.timeout.millisecond");
    String cpuSocketTimeoutMillisecond = getProperty("monitor.cloud.client.cpu.socket.timeout.millisecond");

    MonitorClientConfig instance = new MonitorClientConfig();

    instance.setEnable(Boolean.parseBoolean(enable));
    instance.setConnectTimeoutMillisecond(Integer.parseInt(connectTimeoutMillisecond));
    instance.setConnectionRequestTimeoutMillisecond(Integer.parseInt(connectionRequestTimeoutMillisecond));
    instance.setSocketTimeoutMillisecond(Integer.parseInt(socketTimeoutMillisecond));

    instance.setOsEnable(Boolean.parseBoolean(osEnable));
    instance.setOsRateSecond(Integer.parseInt(osRateSecond));
    instance.setOsUrl(osUrl);
    instance.setOsConnectionRequestTimeoutMillisecond(Integer.parseInt(osConnectionRequestTimeoutMillisecond));
    instance.setOsConnectTimeoutMillisecond(Integer.parseInt(osConnectTimeoutMillisecond));
    instance.setOsSocketTimeoutMillisecond(Integer.parseInt(osSocketTimeoutMillisecond));

    instance.setWhoEnable(Boolean.parseBoolean(whoEnable));
    instance.setWhoRateSecond(Integer.parseInt(whoRateSecond));
    instance.setWhoUrl(whoUrl);
    instance.setWhoConnectionRequestTimeoutMillisecond(Integer.parseInt(whoConnectionRequestTimeoutMillisecond));
    instance.setWhoConnectTimeoutMillisecond(Integer.parseInt(whoConnectTimeoutMillisecond));
    instance.setWhoSocketTimeoutMillisecond(Integer.parseInt(whoSocketTimeoutMillisecond));

    instance.setCpuEnable(Boolean.parseBoolean(cpuEnable));
    instance.setCpuRateSecond(Integer.parseInt(cpuRateSecond));
    instance.setCpuUrl(cpuUrl);
    instance.setCpuConnectionRequestTimeoutMillisecond(Integer.parseInt(cpuConnectionRequestTimeoutMillisecond));
    instance.setCpuConnectTimeoutMillisecond(Integer.parseInt(cpuConnectTimeoutMillisecond));
    instance.setCpuSocketTimeoutMillisecond(Integer.parseInt(cpuSocketTimeoutMillisecond));

    return instance;
  }

  private String getProperty(String property) {
    return this.env.containsProperty(property) ? this.env.getProperty(property) : "";
  }


  @Bean
  @ConditionalOnProperty(value = "monitor.cloud.client.os.enable", matchIfMissing = true)
  public MonitorClient osClient(MonitorClientConfig monitorClientConfig) {
    return new OSClient(monitorClientConfig);
  }

  @Bean
  @ConditionalOnProperty(value = "monitor.cloud.client.os.enable", matchIfMissing = true)
  public MonitorClient cpuClient(MonitorClientConfig monitorClientConfig) {
    return new CpuClient(monitorClientConfig);
  }

}
