package pwd.initializr.search.api.user;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.search.api.robot.vo.DocumentIntoSearchInputVO;
import pwd.initializr.search.api.robot.vo.SearchInputVo;

/**
 * pwd.initializr.search.api.robot@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-13 11:33
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "信息写入与搜索",
    value = "searchApi",
    description = "信息搜索API"
)
@RestController(value = "searchApi")
@RequestMapping(value = "/api/search")
public interface DocumentApi {

    void search(SearchInputVo input);

}
