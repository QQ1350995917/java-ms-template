package pwd.initializr.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * project-generator-test-20210202180117487@ms-web-initializr
 *
 * <h1>project-generator-test-20210202180117487启动</h1>
 *
 * date 2021-02-02 18:01
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
@Controller
@SpringBootApplication
@ComponentScan(basePackages = {"pwd.initializr.common.web", "pwd.initializr.edu"})
public class EducationApplication {

  public static void main(String[] args) {
    SpringApplication.run(EducationApplication.class, args);
  }

  @GetMapping("")
  public ModelAndView index(){
  ModelAndView modelAndView = new ModelAndView("redirect:/swagger-ui.html");
    return modelAndView;
  }
}
