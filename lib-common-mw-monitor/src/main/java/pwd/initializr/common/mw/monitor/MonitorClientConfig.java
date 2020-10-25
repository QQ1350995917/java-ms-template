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

  public int getSocketTimeoutMillisecond() {
    return socketTimeoutMillisecond;
  }

  public void setSocketTimeoutMillisecond(int socketTimeoutMillisecond) {
    this.socketTimeoutMillisecond = socketTimeoutMillisecond;
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

  public boolean isCpuEnable() {
    return cpuEnable;
  }

  public void setCpuEnable(boolean cpuEnable) {
    this.cpuEnable = cpuEnable;
  }

  public boolean isEnable() {
    return enable;
  }

  public void setEnable(boolean enable) {
    this.enable = enable;
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
