package pwd.initializr.account.api.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * pwd.initializr.account.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-04 16:24
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
@RestController(value = "RSA-KeyInfoApi")
@RequestMapping(value = "/api/key")
public interface KeyApi {

    @ApiOperation(value = "获取公钥")
    @PutMapping(value = {"/publish"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void getPublishKey();

}
