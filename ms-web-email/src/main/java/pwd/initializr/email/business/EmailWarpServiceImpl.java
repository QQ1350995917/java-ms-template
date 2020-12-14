package pwd.initializr.email.business;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwd.initializr.email.business.bo.EmailAttachmentBO;
import pwd.initializr.email.business.bo.EmailBO;
import pwd.initializr.email.business.bo.EmailBoxBO;
import pwd.initializr.email.business.bo.EmailContentBO;
import pwd.initializr.email.business.bo.EmailExtendBO;

/**
 * pwd.initializr.email.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-14 16:23
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class EmailWarpServiceImpl implements EmailWarpService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailBoxService emailBoxService;

    @Autowired
    private EmailContentService emailContentService;

    @Autowired
    private EmailAttachmentService emailAttachmentService;

    @Autowired
    private EmailExtendService emailExtendService;


    @Transactional
    @Override
    public EmailBO createEmail(EmailBO emailBO, Set<EmailBoxBO> emailBoxBOS,
        EmailContentBO emailContentBO, Set<EmailAttachmentBO> emailAttachmentBOS,
        Set<EmailExtendBO> emailExtendBOS) {
        // 新增邮件
        emailService.insert(emailBO);
        // 新增邮件收件箱信息
        emailBoxService.insert(emailBO.getId(),emailBoxBOS);
        // 新增邮件内容信息
        emailContentBO.setEmailId(emailBO.getId());
        emailContentService.insert(emailContentBO);
        // 新增邮件附件信息
        if (emailAttachmentBOS != null && !emailAttachmentBOS.isEmpty()) {
            emailAttachmentService.insert(emailBO.getId(),emailAttachmentBOS);
        }
        // 新增邮件扩展信息
        if (emailExtendBOS != null && !emailExtendBOS.isEmpty()) {
            emailExtendService.insert(emailBO.getId(),emailExtendBOS);
        }
        return emailBO;
    }
}
