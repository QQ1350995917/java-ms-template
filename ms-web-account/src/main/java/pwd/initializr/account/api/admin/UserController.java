package pwd.initializr.account.api.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.admin.AdminController;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-14 21:15
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "系统用户管理",
    value = "userManageApi",
    description = "系统用户管理API"
)
@RestController(value = "userManageApi")
@RequestMapping(value = "/api/admin/user")
public class UserController extends AdminController implements UserApi {

    @ApiOperation(value = "下载账号模板")
    @GetMapping(value = {"/template"}, produces = "application/json;charset=UTF-8")
    @Override
    public void downloadAccountTemplate() {
        super.outputData();
    }

    @ApiOperation(value = "导入账号")
    @PostMapping(value = {"/import"}, produces = "application/json;charset=UTF-8")
    @Override
    public void importAccount() {
        super.outputData();
    }

    @ApiOperation(value = "创建账号")
    @PostMapping(value = {"/create"}, produces = "application/json;charset=UTF-8")
    @Override
    public void createAccount() {
        super.outputData();
    }

    @ApiOperation(value = "系统账号清单")
    @GetMapping(value = {""}, produces = "application/json;charset=UTF-8")
    @Override
    public void listAccounts() {
        super.outputData();
    }

    @ApiOperation(value = "账号详情")
    @GetMapping(value = {"/detail"}, produces = "application/json;charset=UTF-8")
    @Override
    public void detailAccount() {
        super.outputData();
    }

    @ApiOperation(value = "封禁账号")
    @PutMapping(value = {"/block"}, produces = "application/json;charset=UTF-8")
    @Override
    public void blockAccount() {
        super.outputData();
    }

    @ApiOperation(value = "解禁账号")
    @PutMapping(value = {"/unblock"}, produces = "application/json;charset=UTF-8")
    @Override
    public void unblockAccount() {
        super.outputData();
    }
}
