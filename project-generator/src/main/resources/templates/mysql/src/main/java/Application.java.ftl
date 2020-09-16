package ${projectPackage};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * ${projectName}@ms-web-initializr
 *
 * <h1>${projectName}启动</h1>
 *
 * date ${projectCreateDate}
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @version ${projectVersion}
 * @since ${projectVersion}
 */
@Controller
@SpringBootApplication
@ComponentScan(basePackages = {"pwd.initializr.common.web", "${projectPackage}"})
public class ${applicationName} {

  public static void main(String[] args) {
    SpringApplication.run(${applicationName}.class, args);
  }

  @GetMapping("")
  public ModelAndView index(){
  ModelAndView modelAndView = new ModelAndView("redirect:/swagger-ui.html");
    return modelAndView;
  }
}