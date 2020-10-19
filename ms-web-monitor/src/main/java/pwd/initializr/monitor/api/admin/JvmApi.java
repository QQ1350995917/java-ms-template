package pwd.initializr.monitor.api.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * pwd.initializr.monitor.api@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-19 16:35
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
@RestController(value = "admin")
@RequestMapping(value = "/api/admin/jvm")
public interface JvmApi {

    @ApiOperation(value = "jst信息")
    @PostMapping(value = {
        "/jst"}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void jst();

    @ApiOperation(value = "jrt信息")
    @PostMapping(value = {
        "/jrt"}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void jrt();

    @ApiOperation(value = "file信息")
    @PostMapping(value = {
        "/file"}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void file();

    @ApiOperation(value = "user信息")
    @PostMapping(value = {
        "/user"}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void user();


}
