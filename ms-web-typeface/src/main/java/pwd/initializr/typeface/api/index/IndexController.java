package pwd.initializr.typeface.api.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * pwd.initializr.typeface.api.index@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-01-19 22:08
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Controller
@RequestMapping(value = "/index")
public class IndexController {

  @RequestMapping(value = "", method = RequestMethod.GET)
  public ModelAndView index() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("index");
    modelAndView.addObject("port", "");
    return modelAndView;
  }
}
