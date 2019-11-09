package pwd.initializr.gateway;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pwd.initializr.gateway.filter.LoggerFilter;
import pwd.initializr.gateway.filter.SessionFilter;

/**
 * pwd.initializr.gateway@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-09 08:50
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Configuration
public class FilterBeanDefiner {
  @Bean
  public GlobalFilter loggerFilter() {
    return new LoggerFilter();
  }

  @Bean
  public GlobalFilter sessionFilter() {
    return new SessionFilter();
  }
}
