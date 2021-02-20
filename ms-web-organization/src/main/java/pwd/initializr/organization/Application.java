package pwd.initializr.organization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * pwd-initializr-organization-20210220185831978@ms-web-initializr
 *
 * <h1>pwd-initializr-organization-20210220185831978启动</h1>
 *
 * date 2021-02-20 18:58
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
@SpringBootApplication
@ComponentScan(basePackages = {"pwd.initializr.common.web", "pwd.initializr.organization"})
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
