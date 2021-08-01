package pwd.initializr.account;

import feign.Feign;
import feign.Logger;
import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionPool;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * pwd.initializr.account@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-08-01 14:49
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Configuration
@ConditionalOnClass(Feign.class)
@AutoConfigureBefore(FeignAutoConfiguration.class)
public class FeignConfig {

  @Bean
  Logger.Level feignLevel() {
    return Logger.Level.FULL;
  }

  /*
   * 配置okhttp与连接池
   * ConnectionPool默认创建5个线程，保持5分钟长连接
   */
  @Bean
  public okhttp3.OkHttpClient okHttpClient() {
    return new okhttp3.OkHttpClient.Builder()
        //设置连接超时
        .connectTimeout(10, TimeUnit.SECONDS)
        //设置读超时
        .readTimeout(10, TimeUnit.SECONDS)
        //设置写超时
        .writeTimeout(10, TimeUnit.SECONDS)
        //是否自动重连
        .retryOnConnectionFailure(true)
        .connectionPool(new ConnectionPool(10, 5L, TimeUnit.MINUTES))
        .build();
  }
}
