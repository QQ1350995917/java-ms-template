package ${projectPackage}.api;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * ${projectName}@ms-web-initializr
 *
 * <h1>界面</h1>
 *
 * date ${projectCreateDate}
 *
 * @author Automatic[dingpengwei@foxmail.com]
 * @version ${projectVersion}
 * @since ${projectVersion}
 */
@Api(
    tags = "Index",
    value = "IndexPage",
    description = "[界面]"
)

@Controller(value = "Index")
@RequestMapping(value = "")
public class IndexController {

    @GetMapping("")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    @GetMapping("/swagger")
    public ModelAndView swagger(){
        return new ModelAndView("redirect:/swagger-ui.html");
    }
}
