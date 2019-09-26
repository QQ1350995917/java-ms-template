package pwd.initializr.common.mw.redis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * pwd.initializr.common.web.middleware.redis@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-21 17:46
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Component
@Configuration
//@PropertySource("classpath:redis.properties")
@Slf4j
@ConfigurationProperties("spring.redis")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class RedisConfig {

  private String host;
  private int port;
  private int timeout;
  private int maxIdle;
  private long maxWaitMillis;
  private String password;
  private boolean blockWhenExhausted;

  @Bean
  public JedisPool redisPoolFactory() throws Exception {
    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    jedisPoolConfig.setMaxIdle(maxIdle);
    jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
    // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
    jedisPoolConfig.setBlockWhenExhausted(blockWhenExhausted);
    // 是否启用pool的jmx管理功能, 默认true
    jedisPoolConfig.setJmxEnabled(true);
//    JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
    JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
    return jedisPool;
  }

}
