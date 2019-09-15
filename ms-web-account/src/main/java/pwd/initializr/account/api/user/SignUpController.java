package pwd.initializr.account.api.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.account.api.user.vo.ListSignUpByWaysInput;
import pwd.initializr.account.api.user.vo.SignUpByApplyInput;
import pwd.initializr.account.api.user.vo.SignUpByEmailInput;
import pwd.initializr.account.api.user.vo.SignUpByPhoneNumberInput;
import pwd.initializr.common.web.api.user.UserController;

/**
 * pwd.initializr.account.api.user@ms-web-initializr
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
    tags = "注册系统账号",
    value = "accountCreateApi",
    description = "用户注册系统账号的API"
)
@RestController(value = "accountCreateApi")
@RequestMapping(value = "/api/signup")
public class SignUpController extends UserController implements SignUpApi {

    @ApiOperation(value = "注册方式清单")
    @GetMapping(value = {"/"}, produces = "application/json;charset=UTF-8")
    @Override
    public void listSignUpWays(ListSignUpByWaysInput input) {

        super.outputData();
    }

    @ApiOperation(value = "申请账号")
    @PostMapping(value = {"/apply"}, produces = "application/json;charset=UTF-8")
    @Override
    public void signUpByApply(SignUpByApplyInput input) {

        super.outputData();
    }

    @ApiOperation(value = "手机号注册账号")
    @PostMapping(value = {"/phone"}, produces = "application/json;charset=UTF-8")
    @Override
    public void signUpByPhoneNumber(SignUpByPhoneNumberInput input) {

        super.outputData();
    }

    @ApiOperation(value = "邮箱注册账号")
    @PostMapping(value = {"/email"}, produces = "application/json;charset=UTF-8")
    @Override
    public void signUpByEmail(SignUpByEmailInput input) {

        super.outputData();
    }

    @Override
    public void signUpByWeChat() {

        super.outputData();
    }

    @Override
    public void signUpByAlipay() {

        super.outputData();
    }

    @Override
    public void signUpBySinaBlog() {

        super.outputData();
    }
}
