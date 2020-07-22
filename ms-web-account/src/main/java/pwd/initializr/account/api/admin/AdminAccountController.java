package pwd.initializr.account.api.admin;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-07-22 11:11
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "管理员账号管理",
    value = "adminAccountManageApi",
    description = "[创建管理员，管理员列表，管理员启用，管理员禁用]"
)
@RestController(value = "adminAccount")
@RequestMapping(value = "/api/admin/account")
public class AdminAccountController implements AdminAccountApi {


}
