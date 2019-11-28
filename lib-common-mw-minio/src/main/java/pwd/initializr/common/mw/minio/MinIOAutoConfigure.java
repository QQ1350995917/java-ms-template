package pwd.initializr.common.mw.minio;

import io.minio.MinioClient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * pwd.initializr.common.mw.minio@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-25 20:27
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Configuration
@ConditionalOnWebApplication
@Import({MinIOClient.class})
//@EnableConfigurationProperties(MinIOClient.class)
public class MinIOAutoConfigure {

  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnProperty(prefix = "spring.minio", value = "enable", havingValue = "true")
  public MinIOClient initRedisClient() throws Exception {
    return new MinIOClient();
  }
}
