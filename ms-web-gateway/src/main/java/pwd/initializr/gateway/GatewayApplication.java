package pwd.initializr.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.gateway.limiter.IPKeyResolver;

/**
 * pwd.initializr.gateway@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-15 17:52
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Configuration
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class GatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
  }

  @GetMapping(value = "")
  public String index0() {
    return "this is gateway 0 index";
  }

  @GetMapping(value = "/")
  public String index1() {
    return "this is gateway 1 index";
  }

  @GetMapping(value = "/gateway")
  public String index2() {
    return "this is gateway 2 index";
  }




}
