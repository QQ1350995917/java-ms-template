package pwd.initializr.monitor.api.admin;

import io.swagger.annotations.Api;
import java.util.Properties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.admin.AdminController;
import pwd.initializr.monitor.api.admin.vo.JstOutput;

/**
 * pwd.initializr.monitor.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-19 18:44
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "服务器信息",
    value = "服务器信息",
    description = "[JST，JRT，FILE，USER]"
)
@RestController(value = "jvm")
@RequestMapping(value = "/api/admin/jvm")
public class JvmController extends AdminController implements JvmApi {

    @Override
    public void jst() {
        this.outputData();
    }

    @Override
    public void jrt() {
        this.outputData();
    }

    @Override
    public void file() {
        this.outputData();
    }

    @Override
    public void user() {
        this.outputData();
    }

}
