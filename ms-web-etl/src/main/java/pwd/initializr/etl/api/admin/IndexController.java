package pwd.initializr.etl.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pwd.initializr.etl.business.admin.ReportTask;
import pwd.initializr.etl.business.admin.bo.InputReportBO;

/**
 * pwd.initializr.etl.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-01-14 22:13
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Controller
public class IndexController {
  @Value("${server.port}")
  private Integer port;

  @RequestMapping(value = "/",method = RequestMethod.GET)
  public ModelAndView index() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("index");
    modelAndView.addObject("port", port);
    return modelAndView;
  }
}
