package pwd.initializr.account.api.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-04 16:23
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "RSA-Key信息管理",
    value = "RSA-KeyInfoApi",
    description = "[RSA-Key信息管理]"
)
@RestController(value = "adminRSA-KeyInfoApi")
@RequestMapping(value = "/api/admin/key")
public interface KeyApi {

    @ApiOperation(value = "刷新管理员的KEY")
    @PutMapping(value = {"/refresh/admin"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void refreshAdminKeyPairs();

    @ApiOperation(value = "刷新用户的KEY")
    @PutMapping(value = {"/refresh/user"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void refreshUserKeyPairs();

    @ApiOperation(value = "获取管理员公钥")
    @GetMapping(value = {"/public/admin"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void getAdminPublishKey();

    @ApiOperation(value = "获取用户公钥")
    @GetMapping(value = {"/public/user"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void getUserPublishKey();

}
