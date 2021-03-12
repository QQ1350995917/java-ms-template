package pwd.initializr.email.business;

import feign.Response;
import java.io.InputStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pwd.initializr.common.email.Email;
import pwd.initializr.common.email.EmailAttachment;
import pwd.initializr.common.email.EmailClient;
import pwd.initializr.email.business.bo.EmailAttachmentBO;
import pwd.initializr.email.business.bo.EmailBO;
import pwd.initializr.email.business.bo.EmailBoxBO;
import pwd.initializr.email.business.bo.EmailContentBO;
import pwd.initializr.email.business.bo.EmailWrapBO;
import pwd.initializr.email.persistence.entity.EmailBoxType;
import pwd.initializr.email.persistence.entity.EmailSendType;

/**
 * pwd.initializr.email.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-14 16:17
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Slf4j
@Service("EmailSenderService")
public class EmailSenderServiceImpl implements EmailSenderService{

    /**
     * 队列空闲容量超出总总量的1/3进行再次查询
     */
    private final int BOUNDARY_DIVIDER_FOR_QUERY = 3;
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
    @Value("${email.client.auto.schedule.enable}")
    private Boolean autoEnable;
    @Value("${email.client.executor.queue.max.capacity}")
    private Integer queueMaxCapacity;

    @Autowired
    private EmailService emailService;
    @Autowired
    private EmailWarpService emailWarpService;

    @Autowired
    private StorageService storageService;

    @Autowired
    @Qualifier(value = "emailPostManThreadPoolQueue")
    private ArrayBlockingQueue waitingForSendEmailQueue;
    @Autowired
    @Qualifier(value = "emailPostManThreadPool")
    private ThreadPoolExecutor emailPostManThreadPool;

    @Override
    public boolean isQueueRemainingCapacityAble() {
        int remainingCapacity = this.waitingForSendEmailQueue.remainingCapacity();
        if (remainingCapacity >= this.queueMaxCapacity / this.BOUNDARY_DIVIDER_FOR_QUERY) {
            return true;
        }
        return false;
    }

    @Override
    public Set<Long> send() {
        Integer remainingCapacity = this.waitingForSendEmailQueue.remainingCapacity();
        Set<Long> ids = emailWarpService.queryWaitingForSendEmail(remainingCapacity.longValue());
        if (ids != null && !ids.isEmpty()) {
            ids.forEach(id -> {
                this.emailPostManThreadPool.execute(new EmailPostManRunnable(id));
            });
        }
        return ids;
    }

    private class EmailPostManRunnable implements Runnable {

        private Long id;

        private EmailPostManRunnable(Long id) {
            this.id = id;
        }

        @Override
        public void run() {
            EmailWrapBO emailWrapBO = emailWarpService.queryById(this.id);
            Email email = new Email();
            email.setFrom(emailServerUser);

            HashSet<String> tos = new HashSet<>();
            HashSet<String> ccs = new HashSet<>();
            HashSet<String> bccs = new HashSet<>();
            List<EmailBoxBO> emailBoxBOS = emailWrapBO.getEmailBoxBOS();
            Optional.ofNullable(emailBoxBOS).get().forEach(emailBoxBO -> {
                if (EmailBoxType.TO.getType() == emailBoxBO.getType()) {
                    tos.add(emailBoxBO.getMailbox());
                } else if (EmailBoxType.CC.getType() == emailBoxBO.getType()) {
                    ccs.add(emailBoxBO.getMailbox());
                } else if (EmailBoxType.BCC.getType() == emailBoxBO.getType()) {
                    bccs.add(emailBoxBO.getMailbox());
                } else {
                    // 发件箱不处理
                }
            });

            email.setTos(tos);
            if (!ccs.isEmpty()) {
                email.setCcs(ccs);
            }
            if (!bccs.isEmpty()) {
                email.setBccs(bccs);
            }

            EmailContentBO emailContentBO = emailWrapBO.getEmailContentBO();
            email.setSubject(emailContentBO.getSubject());
            email.setContent(emailContentBO.getContent());

            List<EmailAttachmentBO> emailAttachmentBOS = emailWrapBO.getEmailAttachmentBOS();
            if (emailAttachmentBOS != null && !emailAttachmentBOS.isEmpty()) {
                LinkedList<EmailAttachment> attachments = new LinkedList<>();
                EmailAttachment emailAttachment = new EmailAttachment();
                emailAttachmentBOS.forEach(emailAttachmentBO -> {
                    BeanUtils.copyProperties(emailAttachmentBO, emailAttachment);
                    Response response = storageService.download(emailAttachmentBO.getUrl());
                    if (response == null) {
                        log.info("邮件附件下载异常，Response为空");
                        return;
                    }
                    try (InputStream inputStream = response.body().asInputStream()){
                        byte[] bytes = new byte[inputStream.available()];
                        inputStream.read(bytes);
                        emailAttachment.setBytes(bytes);
                    } catch (Exception e){
                        log.error("邮件附件读取异常，{}", e.getMessage());
                    }
                    attachments.add(emailAttachment);
                });
                email.setAttachments(attachments);
            }

            EmailClient emailClient = new EmailClient(emailDebug, emailServerHost, emailServerPort,
                emailServerProtocol, autoEnable, emailServerUser, emailServerPassword);
            EmailBO emailBO = new EmailBO();
            emailBO.setId(this.id);
            try {
                emailClient.send(email);
                emailBO.setSent(EmailSendType.SENT.getType());
                emailService.updateById(emailBO);
            } catch (Exception e) {
                emailBO.setSent(EmailSendType.FAILURE.getType());
                emailService.updateById(emailBO);
            } finally {
                emailClient.destroyTransport();
            }
        }
    }

}
