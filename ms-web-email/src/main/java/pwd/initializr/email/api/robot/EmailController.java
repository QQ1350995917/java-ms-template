package pwd.initializr.email.api.robot;

import io.swagger.annotations.Api;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.email.api.robot.vo.SendEmailInput;
import pwd.initializr.email.business.EmailService;
import pwd.initializr.email.business.bo.EmailBO;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>控制层接口：管理员信息</h1>
 *
 * date 2019-10-26 8:14
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "管理员管理",
    value = "adminManageApi",
    description = "[创建管理员/账号，人员/账号列表，人员/账号启/禁用，人员/账号删除]"
)
@RestController(value = "robotEmail")
@RequestMapping(value = "/api/robot/email")
@Slf4j
public class EmailController extends pwd.initializr.common.web.api.admin.AdminController implements
    EmailApi {

  @Autowired
  private EmailService emailService;

  @Override
  public void create(@Valid @NotNull(message = "参数不能为空") SendEmailInput input) {
    EmailBO emailBO = new EmailBO();
    BeanUtils.copyProperties(input,emailBO);
    Long id = emailService.sendEmail(emailBO);
    if (id == null) {
      this.outputException(500,"请检查邮箱地址并重试");
    } else {
      this.outputData(200,id);
    }
  }

}
