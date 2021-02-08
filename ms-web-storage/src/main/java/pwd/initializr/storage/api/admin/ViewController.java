package pwd.initializr.storage.api.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.InputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import pwd.initializr.common.web.api.admin.AdminController;
import pwd.initializr.storage.business.StorageService;
import pwd.initializr.storage.business.bo.StorageBO;

/**
 * pwd.initializr.storage.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-08 11:26
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "文件管理页面",
    value = "文件管理页面",
    description = "文件管理页面"
)
@Controller(value = "adminView")
@RequestMapping(value = "")
public class ViewController extends AdminController {

    @ApiOperation(value = "文件列表页面")
    @GetMapping(value = {""})
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @ApiOperation(value = "文件上传页面")
    @GetMapping(value = {"/upload"})
    public ModelAndView upload() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("upload");
        return modelAndView;
    }




}
