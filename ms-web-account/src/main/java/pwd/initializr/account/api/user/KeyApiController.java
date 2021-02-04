package pwd.initializr.account.api.user;

import io.swagger.annotations.Api;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.business.user.UserKeyService;
import pwd.initializr.common.web.api.user.UserController;

/**
 * pwd.initializr.account.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-04 16:31
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
@Slf4j
public class KeyApiController extends UserController implements KeyApi {

    @Resource
    private UserKeyService userKeyService;

    @Override
    public void getPublishKey() {
        String publicKey = userKeyService.getPublicKey();
        outputData(publicKey);
    }
}
