package pwd.initializr.common.mw.monitor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

  @Bean
  @ConditionalOnProperty(value = "monitor.cloud.client.enable", matchIfMissing = true)
  public HttpX client() {
    return new HttpXByHttpClient(new HttpXConfig());
  }


  @Bean
  @ConditionalOnProperty(value = "monitor.cloud.client.os.enable", matchIfMissing = true)
  public MonitorClient osClient() {
    return new OSClient();
  }

//  @Bean
//  @ConditionalOnProperty(value = "monitor.cloud.client.os.enable", matchIfMissing = true)
//  public MonitorClient cpuClient() {
//    return new CpuClient();
//  }

}
