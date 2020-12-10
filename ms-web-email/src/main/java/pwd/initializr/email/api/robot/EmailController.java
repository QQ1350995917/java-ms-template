package pwd.initializr.email.api.robot;

import io.swagger.annotations.Api;
import java.util.LinkedList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.email.api.robot.vo.SendEmailInput;
import pwd.initializr.email.api.robot.vo.SendEmailOutput;
import pwd.initializr.email.business.EmailService;
import pwd.initializr.email.business.bo.EmailAttachmentBO;
import pwd.initializr.email.business.bo.EmailBO;
import pwd.initializr.email.rpc.RPCEmailAttachmentVO;
import pwd.initializr.email.rpc.RPCEmailContentVO;
import pwd.initializr.email.rpc.RPCEmailHeaderVO;

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
        BeanUtils.copyProperties(input, emailBO);
        RPCEmailHeaderVO header = input.getHeader();
        BeanUtils.copyProperties(header, emailBO);
        RPCEmailContentVO content = input.getContent();
        BeanUtils.copyProperties(content, emailBO);

        List<RPCEmailAttachmentVO> attachments = input.getAttachments();
        if (attachments != null && !attachments.isEmpty()) {
            LinkedList<EmailAttachmentBO> emailAttachmentBOS = new LinkedList<>();
            attachments.forEach(attachment -> {
                EmailAttachmentBO emailAttachmentBO = new EmailAttachmentBO();
                BeanUtils.copyProperties(attachment, emailAttachmentBO);
                emailAttachmentBOS.add(emailAttachmentBO);
            });
            emailBO.setAttachments(emailAttachmentBOS);
        }

        // fixme: 附件有bug
        emailBO.setAttachments(null);

        EmailBO result = emailService.sendEmail(emailBO);

        if (result == null) {
            this.outputException(500, "请联系客服人员");
        } else {
            SendEmailOutput sendEmailOutput = new SendEmailOutput();
            BeanUtils.copyProperties(result, sendEmailOutput);
            this.outputData(200, sendEmailOutput);
        }
    }

}
