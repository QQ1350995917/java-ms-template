package pwd.initializr.email.api.robot;

import io.swagger.annotations.Api;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.vo.PageInput;
import pwd.initializr.common.web.api.vo.PageOutput;
import pwd.initializr.common.web.api.vo.ScopeInput;
import pwd.initializr.common.web.api.vo.SortInput;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.email.api.robot.vo.EmailOutput;
import pwd.initializr.email.api.robot.vo.SendEmailInput;
import pwd.initializr.email.api.robot.vo.SendEmailOutput;
import pwd.initializr.email.business.EmailService;
import pwd.initializr.email.business.EmailWarpService;
import pwd.initializr.email.business.bo.EmailAttachmentBO;
import pwd.initializr.email.business.bo.EmailBO;
import pwd.initializr.email.business.bo.EmailBoxBO;
import pwd.initializr.email.business.bo.EmailContentBO;
import pwd.initializr.email.business.bo.EmailExtendBO;
import pwd.initializr.email.persistence.entity.EmailBoxType;
import pwd.initializr.email.rpc.RPCEmailAttachmentVO;
import pwd.initializr.email.rpc.RPCEmailBoxVO;
import pwd.initializr.email.rpc.RPCEmailContentVO;

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
    tags = "邮件管理API",
    value = "emailPosterApi",
    description = "[邮件发送]"
)
@RestController(value = "robotEmail")
@RequestMapping(value = "/api/robot/email")
@Slf4j
public class EmailController extends pwd.initializr.common.web.api.robot.RobotController implements
    EmailApi {

    @Autowired
    private EmailService service;

    @Autowired
    private EmailWarpService emailWarpService;

    @Override
    public void list(String scopes, String sorts, String page) {
        PageInput pageInput = PageInput.parse(page);
        LinkedHashSet<ScopeBO> scopeBOS = ScopeInput.parse(scopes);
        LinkedHashSet<SortBO> sortBOS = SortInput.parse(sorts);
        PageableQueryResult<EmailBO> pageableQueryResult = service
            .queryAllByCondition(scopeBOS, sortBOS, pageInput.getIndex(), pageInput.getSize());
        PageOutput<EmailOutput> result = new PageOutput<>();
        pageableQueryResult.getElements().forEach(bo -> {
            EmailOutput output = new EmailOutput();
            BeanUtils.copyProperties(bo, output);
            result.getElements().add(output);
        });
        result.setTotal(pageableQueryResult.getTotal());
        result.setIndex(pageableQueryResult.getIndex());
        result.setSize(pageableQueryResult.getSize());
        outputData(result);
    }

    @Override
    public void detail(@Valid @NotNull(message = "参数不能为空") Long id) {
        EmailBO bo = service.queryById(id);
        EmailOutput output = new EmailOutput();
        BeanUtils.copyProperties(bo, output);
        outputData(output);
    }

    @Override
    public void delete(@Valid @NotNull(message = "参数不能为空") Long id) {
        Integer result = service.deleteById(id);
        outputData(200, result);
    }

    @Override
    public void delete(@Valid @NotNull(message = "参数不能为空") Set<Long> ids) {
        Integer result = service.deleteById(ids);
        outputData(200, result);
    }


    @Override
    public void create(@Valid @NotNull(message = "参数不能为空") SendEmailInput input) {
        // 解析收件箱参数
        RPCEmailBoxVO boxVO = input.getBox();
        Set<String> tos = boxVO.getTo();
        HashSet<EmailBoxBO> emailBoxBOS = new HashSet<>();
        // 解析收件箱
        for (String to : tos) {
            EmailBoxBO emailBoxBO = new EmailBoxBO();
            emailBoxBO.setType(EmailBoxType.TO.getType());
            emailBoxBO.setMailbox(to);
            emailBoxBOS.add(emailBoxBO);
        }
        // 解析抄送邮箱
        Set<String> ccs = boxVO.getCc();
        if (ccs != null && !ccs.isEmpty()) {
            for (String cc : ccs) {
                EmailBoxBO emailBoxBO = new EmailBoxBO();
                emailBoxBO.setType(EmailBoxType.CC.getType());
                emailBoxBO.setMailbox(cc);
                emailBoxBOS.add(emailBoxBO);
            }
        }
        // 解析密送邮箱
        Set<String> bccs = boxVO.getBcc();
        if (bccs != null && !bccs.isEmpty()) {
            for (String bcc : bccs) {
                EmailBoxBO emailBoxBO = new EmailBoxBO();
                emailBoxBO.setType(EmailBoxType.BCC.getType());
                emailBoxBO.setMailbox(bcc);
                emailBoxBOS.add(emailBoxBO);
            }
        }

        // 解析内容
        RPCEmailContentVO contentVO = input.getContent();
        EmailContentBO emailContentBO = new EmailContentBO();
        BeanUtils.copyProperties(contentVO, emailContentBO);

        // 解析附件
        Set<RPCEmailAttachmentVO> attachmentsVOS = input.getAttachments();
        HashSet<EmailAttachmentBO> emailAttachmentBOS = null;
        if (attachmentsVOS != null && !attachmentsVOS.isEmpty()) {
            emailAttachmentBOS = new HashSet<>();
            for(RPCEmailAttachmentVO attachmentsVO :attachmentsVOS){
                EmailAttachmentBO emailAttachmentBO = new EmailAttachmentBO();
                BeanUtils.copyProperties(attachmentsVO,emailAttachmentBO);
                emailAttachmentBOS.add(emailAttachmentBO);
            };
        }

        // 解析附加数据
        HashMap<String, String> extendVO = input.getExtend();
        HashSet<EmailExtendBO> emailExtendBOS = null;
        if (extendVO != null && !extendVO.isEmpty()) {
            emailExtendBOS = new HashSet<>();
            Iterator<Entry<String, String>> iterator = extendVO.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> next = iterator.next();
                emailExtendBOS.add(new EmailExtendBO(next.getKey(),next.getValue()));
            }
        }

        EmailBO emailBO = new EmailBO();
        emailBO.setApp(input.getApp());

        EmailBO email = emailWarpService
            .createEmail(emailBO, emailBoxBOS, emailContentBO, emailAttachmentBOS, emailExtendBOS);

        if (email == null) {
            this.outputException(500, "创建邮件失败");
            return;
        }

        SendEmailOutput sendEmailOutput = new SendEmailOutput();
        BeanUtils.copyProperties(email, sendEmailOutput);
        this.outputData(200, sendEmailOutput);
    }

}
