package pwd.initializr.organization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * pwd-initializr-organization@ms-web-initializr
 *
 * <h1>pwd-initializr-organization启动</h1>
 *
 * date 2021-02-22 21:33
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
@SpringBootApplication
@ComponentScan(basePackages = {"pwd.initializr.common.web", "pwd.initializr.organization"})
public class OrganizationApplication {

  public static void main(String[] args) {
    SpringApplication.run(OrganizationApplication.class, args);
  }

}
