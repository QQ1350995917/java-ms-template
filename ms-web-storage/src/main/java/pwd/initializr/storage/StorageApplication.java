package pwd.initializr.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.FullPathNameGenerator;

/**
 * pwd.initializr.storage@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-25 16:39
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Configuration
@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableDiscoveryClient
@ComponentScan(nameGenerator = FullPathNameGenerator.class)
public class StorageApplication {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(StorageApplication.class, args);
  }


  @GetMapping(value = "")
  public String index0() {
    return "this is storage 0 index";
  }

  @GetMapping(value = "/")
  public String index1() {
    return "this is storage 1 index";
  }

  @GetMapping(value = "/account")
  public String index2() {
    return "this is storage 2 index";
  }

}
