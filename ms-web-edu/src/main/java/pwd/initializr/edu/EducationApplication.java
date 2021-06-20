package pwd.initializr.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * project-generator-test-20210204160711060@ms-web-initializr
 *
 * <h1>project-generator-test-20210204160711060启动</h1>
 *
 * date 2021-02-04 16:07
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients("pwd.initializr.edu.business")
@SpringBootApplication
@ComponentScan(basePackages = {"pwd.initializr.common.web", "pwd.initializr.edu"})
public class EducationApplication {

  public static void main(String[] args) {
    SpringApplication.run(EducationApplication.class, args);
  }
}
