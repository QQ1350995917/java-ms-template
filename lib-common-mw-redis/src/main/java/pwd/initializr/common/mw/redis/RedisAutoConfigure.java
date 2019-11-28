package pwd.initializr.common.mw.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * pwd.initializr.common.web.middleware.redis@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-23 14:08
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Configuration
@ConditionalOnWebApplication
@Import({RedisClient.class})
@EnableConfigurationProperties(RedisConfig.class)
public class RedisAutoConfigure {

  @Autowired
  private RedisConfig redisConfig;

  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnProperty(prefix = "spring.redis", value = "enable", havingValue = "true")
  public RedisClient initRedisClient() {
    return new RedisClient();
  }
}
