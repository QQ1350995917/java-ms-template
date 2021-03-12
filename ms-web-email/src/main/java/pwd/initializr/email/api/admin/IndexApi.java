package pwd.initializr.email.api.admin;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
public interface IndexApi {

}
