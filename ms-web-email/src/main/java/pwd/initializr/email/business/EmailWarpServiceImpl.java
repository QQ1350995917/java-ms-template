package pwd.initializr.email.business;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwd.initializr.email.business.bo.EmailAttachmentBO;
import pwd.initializr.email.business.bo.EmailBO;
import pwd.initializr.email.business.bo.EmailBoxBO;
import pwd.initializr.email.business.bo.EmailContentBO;
import pwd.initializr.email.business.bo.EmailExtendBO;
import pwd.initializr.email.business.bo.EmailWrapBO;
import pwd.initializr.email.persistence.entity.EmailSendType;

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
        EmailBO result = emailService.insert(emailBO);
        // 新增邮件收件箱信息
        emailBoxService.insert(result.getId(), emailBoxBOS);
        // 新增邮件内容信息
        emailContentBO.setEmailId(result.getId());
        emailContentService.insert(emailContentBO);
        // 新增邮件附件信息
        if (emailAttachmentBOS != null && !emailAttachmentBOS.isEmpty()) {
            emailAttachmentService.insert(result.getId(), emailAttachmentBOS);
        }
        // 新增邮件扩展信息
        if (emailExtendBOS != null && !emailExtendBOS.isEmpty()) {
            emailExtendService.insert(result.getId(), emailExtendBOS);
        }
        return emailBO;
    }

    @Transactional
    @Override
    public Set<Long> queryWaitingForSendEmail(Long pageSize) {
        List<EmailBO> emailBOS = emailService.queryBySynchronizationForSender(pageSize);
        if (emailBOS == null || emailBOS.isEmpty()) {
            return null;
        }
        HashSet<Long> ids = new HashSet<>();
        emailBOS.forEach(emailBO -> {
            ids.add(emailBO.getId());
        });
        emailService.updateSentStatusByBatch(ids, EmailSendType.SENDING);
        return ids;
    }

    @Override
    public EmailWrapBO queryById(Long id) {
        EmailWrapBO emailWrapBO = new EmailWrapBO();

        List<EmailBoxBO> emailBoxBOS = emailBoxService.queryByEmailId(id);
        emailWrapBO.setEmailBoxBOS(emailBoxBOS);

        EmailContentBO emailContentBO = emailContentService.queryByEmailId(id);
        emailWrapBO.setEmailContentBO(emailContentBO);

        List<EmailAttachmentBO> emailAttachmentBOS = emailAttachmentService.queryByEmailId(id);
        emailWrapBO.setEmailAttachmentBOS(emailAttachmentBOS);

        return emailWrapBO;
    }
}
