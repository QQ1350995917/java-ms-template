package pwd.initializr.organization;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * pwd.initializr.organization@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-11-02 22:03
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@RestController
@EnableTransactionManagement
@ComponentScan(nameGenerator = FullPathNameGenerator.class)
@MapperScan("pwd.initializr.organization.persistence.mapper")
@EnableFeignClients
public class OrganizationApplication {
  public static void main(String[] args) {
    SpringApplication.run(OrganizationApplication.class, args);
  }


  @GetMapping(value = "")
  public String index0() {
    return "this is organization 0 index";
  }

  @GetMapping(value = "/")
  public String index1() {
    return "this is organization 1 index";
  }

  @GetMapping(value = "/account")
  public String index2() {
    return "this is organization 2 index";
  }
}
