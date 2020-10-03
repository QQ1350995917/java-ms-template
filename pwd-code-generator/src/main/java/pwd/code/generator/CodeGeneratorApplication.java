package pwd.code.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * pwd-code-generator@ms-web-initializr
 *
 * <h1>pwd-code-generator启动</h1>
 *
 * date 2020-10-01 15:41
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
@Controller
@SpringBootApplication
@ComponentScan(basePackages = {"pwd.initializr.common.web", "pwd.code.generator"})
public class CodeGeneratorApplication {

  public static void main(String[] args) {
    SpringApplication.run(CodeGeneratorApplication.class, args);
  }

  @GetMapping("")
  public ModelAndView index(){
  ModelAndView modelAndView = new ModelAndView("redirect:/swagger-ui.html");
    return modelAndView;
  }
}
