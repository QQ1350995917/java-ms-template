package pwd.initializr.search.api.robot;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.search.api.robot.vo.DocumentVO;
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
    value = "documentApi",
    description = "信息写入与搜索API"
)
@RestController(value = "documentApi")
@RequestMapping(value = "/api/robot/document")
public interface DocumentApi {

    @ApiOperation(value = "向ES中指定的index写入数据")
    @PostMapping(value = {"/{indexName}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void replace(@PathVariable("indexName") @Valid @NotNull(message = "参数不能为空") String indexName,
        @RequestBody @Valid @NotNull(message = "参数不能为空") List<DocumentVO> input);

    @ApiOperation(value = "在ES中指定的index中根据关键字搜索")
    @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void search(@Valid @NotNull(message = "参数不能为空") SearchInputVo input);

}
