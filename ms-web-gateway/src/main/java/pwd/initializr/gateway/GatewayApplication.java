package pwd.initializr.gateway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * pwd.initializr.gateway@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 *
 * https://www.cnblogs.com/qianwei/p/10127700.html
 * https://www.cnblogs.com/crazymakercircle/p/11704077.html
 * https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.1.0.RC2/single/spring-cloud-gateway.html#gateway-how-it-works
 * https://github.com/giafei/gateway-request-recorder-starter
 *
 * date 2019-09-15 17:52
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RestController
@SpringBootApplication
@EnableDiscoveryClient
@EnableOpenApi
@SpringCloudApplication
@MapperScan("pwd.initializr.gateway.persistence")
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
