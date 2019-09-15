package pwd.initializr.account.api.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.admin.AdminController;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-15 09:46
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "注册方式管理",
    value = "signupManageApi",
    description = "注册方式管理API"
)
@RestController(value = "signup")
@RequestMapping(value = "/api/admin/singup")
public class SignUpWayController extends AdminController implements SignUpWayApi {

    @ApiOperation(value = "注册方式清单")
    @GetMapping(value = {"/"}, produces = "application/json;charset=UTF-8")
    @Override
    public void listSignUpWays() {
        super.outputData();
    }

    @ApiOperation(value = "封禁账号")
    @PutMapping(value = {"/block"}, produces = "application/json;charset=UTF-8")
    @Override
    public void blockSignUpWay() {
        super.outputData();
    }

    @ApiOperation(value = "解禁账号")
    @PutMapping(value = {"/unblock"}, produces = "application/json;charset=UTF-8")
    @Override
    public void unblockSignUpWay() {
        super.outputData();
    }
}
