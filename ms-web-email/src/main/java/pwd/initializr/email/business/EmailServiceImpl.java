package pwd.initializr.email.business;

import feign.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import pwd.initializr.email.business.bo.Email;
import pwd.initializr.email.business.bo.EmailAttachment;
import pwd.initializr.email.business.bo.EmailBO;
import pwd.initializr.email.persistence.entity.EmailAttachmentEntity;
import pwd.initializr.email.persistence.entity.EmailEntity;

/**
 * pwd.initializr.email.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-05 16:33
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Value("${email.debug}")
    private Boolean emailDebug;
    @Value("${email.server.host}")
    private String emailServerHost;
    @Value("${email.server.port}")
    private String emailServerPort;
    @Value("${email.server.protocol}")
    private String emailServerProtocol;
    @Value("${email.server.auth}")
    private Boolean emailServerAuth;
    @Value("${email.server.user}")
    private String emailServerUser;
    @Value("${email.server.password}")
    private String emailServerPassword;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private StorageService storageService;


    @Override
    public Long sendEmail(EmailBO bo) {
        // 初始化邮件发送对象
        Email email = new Email();
        BeanUtils.copyProperties(bo, email);

        // 构建邮件发送附件对象
        LinkedList<EmailAttachment> emailAttachments = new LinkedList<>();
        email.setAttachments(emailAttachments);
        List<EmailAttachmentEntity> attachments = bo.getAttachments();
        if (attachments != null && !attachments.isEmpty()) {
            attachments.forEach(attachment -> {
                EmailAttachment emailAttachment = new EmailAttachment();
                BeanUtils.copyProperties(attachment,emailAttachment);
                // Fixme: 重新设计，记录每个附件的读取状态，以便于追踪
                Response response = storageService.download(attachment.getUrl());
                if (response != null && response.status() == 200) {
                    Response.Body body = response.body();
                    try (InputStream inputStream = body.asInputStream()) {
                        byte[] b = new byte[inputStream.available()];
                        inputStream.read(b);
                        emailAttachment.setBytes(b);
                    } catch (IOException e) {
                        log.error(e.getMessage());
                    }
                }
                emailAttachments.add(emailAttachment);
            });
        }

        // 构建邮件发送数据存储对象
        EmailEntity emailEntity = new EmailEntity();
        BeanUtils.copyProperties(bo, emailEntity);

        // 发送邮件并记录发送状态
        try {
            getEmailClient().send(email);
            emailEntity.setSent(1);
        } catch (Exception e) {
            emailEntity.setSent(1);
            log.error(e.getMessage());
        }

        // 数据存储
        EmailEntity save = mongoTemplate.save(emailEntity);
        return save.getId();
    }


    private EmailClientDefault getEmailClient() {
        return EmailClientDefault
            .getInstance(this.emailDebug, this.emailServerHost, this.emailServerPort,
                this.emailServerProtocol, this.emailServerAuth, this.emailServerUser,
                this.emailServerPassword);
    }
}
