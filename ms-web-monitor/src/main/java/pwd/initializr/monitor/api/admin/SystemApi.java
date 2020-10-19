package pwd.initializr.monitor.api.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * pwd.initializr.monitor.api@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-19 16:34
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "服务器信息",
    value = "服务器信息",
    description = "[OS，CPU，MEMORY，SWAP，WHO，DISK，NETWORK，ETHERNET]"
)
@RestController(value = "admin")
@RequestMapping(value = "/api/admin/server")
public interface SystemApi {
    @ApiOperation(value = "os信息")
    @PostMapping(value = {
        "/os"}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void os();

    @ApiOperation(value = "cpu信息")
    @PostMapping(value = {
        "/cpu"}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void cpu();

    @ApiOperation(value = "memory信息")
    @PostMapping(value = {
        "/memory"}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void memory();

    @ApiOperation(value = "swap信息")
    @PostMapping(value = {
        "/swap"}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void swap();

    @ApiOperation(value = "who信息")
    @PostMapping(value = {
        "/who"}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void who();

    @ApiOperation(value = "disk信息")
    @PostMapping(value = {
        "/disk"}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void disk();

    @ApiOperation(value = "network信息")
    @PostMapping(value = {
        "/network"}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void network();

    @ApiOperation(value = "ethernet信息")
    @PostMapping(value = {
        "/ethernet"}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void ethernet();
}
