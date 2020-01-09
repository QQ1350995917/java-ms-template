package pwd.initializr.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class GatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
  }

//  @Autowired
//  DynamicGatewayService dynamicGatewayService;

  @GetMapping(value = "")
  public String index0() {
    return "this is gateway 0 index";
  }

  @GetMapping(value = "/")
  public String index1() {
//    dynamicGatewayService.save();
    return "this is gateway 1 index";
  }

  @GetMapping(value = "/gateway")
  public String index2() {
    return "this is gateway 2 index";
  }
}
