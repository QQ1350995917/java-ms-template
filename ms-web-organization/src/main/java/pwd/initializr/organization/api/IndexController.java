package pwd.initializr.organization.api;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * pwd-initializr-organization@ms-web-initializr
 *
 * <h1>界面</h1>
 *
 * date 2021-02-22 21:33
 *
 * @author Automatic[dingpengwei@foxmail.com]
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
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
