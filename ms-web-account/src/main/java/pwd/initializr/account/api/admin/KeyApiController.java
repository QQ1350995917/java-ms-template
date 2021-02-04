package pwd.initializr.account.api.admin;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.admin.AdminController;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-04 16:30
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
@Slf4j
public class KeyApiController extends AdminController implements KeyApi {

    @Override
    public void refreshKeyPairs() {

    }

    @Override
    public void getPublishKey() {

    }
}
