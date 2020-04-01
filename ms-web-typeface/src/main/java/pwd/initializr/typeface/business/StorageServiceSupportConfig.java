package pwd.initializr.typeface.business;

import feign.Logger;
import feign.Logger.Level;
import feign.codec.Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * pwd.initializr.typeface.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-30 23:35
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Configuration
public class StorageServiceSupportConfig {

  @Bean
  public Encoder feignFormEncoder() {
    return new StorageServiceFormEncoder();
  }

  @Bean
  Logger.Level feignLoggerLevel() {
    return Level.FULL;
  }
}
