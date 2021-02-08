package pwd.initializr.edu.api.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * pwd.initializr.edu.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-08 16:45
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "管理员页面",
    value = "管理员页面",
    description = "管理员页面"
)
@Controller(value = "管理员页面")
@RequestMapping(value = "")
@Slf4j
public class ViewController {

    @ApiOperation(value = "index")
    @GetMapping(value = {""})
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @ApiOperation(value = "同步课文")
    @GetMapping(value = {"/article"})
    public ModelAndView article() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("article");
        return modelAndView;
    }

}
