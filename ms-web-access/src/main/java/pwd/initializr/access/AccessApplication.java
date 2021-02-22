package pwd.initializr.access;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * pwd-initializr-access@ms-web-initializr
 *
 * <h1>pwd-initializr-access启动</h1>
 *
 * date 2021-02-22 22:48
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
@SpringBootApplication
@ComponentScan(basePackages = {"pwd.initializr.common.web", "pwd.initializr.access"})
public class AccessApplication {

  public static void main(String[] args) {
    SpringApplication.run(AccessApplication.class, args);
  }

}
