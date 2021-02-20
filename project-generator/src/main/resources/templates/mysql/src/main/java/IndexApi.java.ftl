package ${projectPackage}.api;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
public interface IndexApi {

}
