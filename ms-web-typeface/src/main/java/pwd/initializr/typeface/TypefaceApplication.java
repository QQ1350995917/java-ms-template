package pwd.initializr.typeface;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * pwd.initializr.typeface@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-29 11:40
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RestController
@Configuration
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients("pwd.initializr.typeface.business")
@MapperScan("pwd.initializr.typeface.persistence")
public class TypefaceApplication {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(TypefaceApplication.class, args);
  }


  @GetMapping(value = "")
  public String index0() {
    return "this is typeface 0 index";
  }

  @GetMapping(value = "/")
  public String index1() {
    return "this is typeface 1 index";
  }

  @GetMapping(value = "/typeface")
  public String index2() {
    return "this is typeface 2 index";
  }

}
