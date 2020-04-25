package pwd.initializr.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.FullPathNameGenerator;

/**
 * pwd.initializr.account@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-13 22:48
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@RestController
@ComponentScan(nameGenerator = FullPathNameGenerator.class)
public class ArticleApplication {

  public static void main(String[] args) {
    SpringApplication.run(ArticleApplication.class, args);
  }

  @GetMapping(value = "")
  public String index0() {
    return "this is book 0 index";
  }

  @GetMapping(value = "/")
  public String index1() {
    return "this is book 1 index";
  }

  @GetMapping(value = "/logger")
  public String index2() {
    return "this is book 2 index";
  }

}
