package pwd.initializr.email.api.robot;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.email.api.robot.vo.SendEmailInput;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>控制层接口：管理员信息</h1>
 *
 * date 2019-10-26 8:13
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "邮件管理API",
    value = "emailPosterApi",
    description = "[邮件创建[发送]]"
)
@RestController(value = "robotEmailApi")
@RequestMapping(value = "/api/robot/email")
public interface EmailApi {

    @ApiOperation(value = "发送邮件")
    @PostMapping(value = {
        ""}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void create(@RequestBody @Valid @NotNull(message = "参数不能为空") SendEmailInput input);
}
