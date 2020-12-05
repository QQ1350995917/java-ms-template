package pwd.initializr.email.business;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import pwd.initializr.common.utils.Snowflake;
import pwd.initializr.email.business.bo.Email;
import pwd.initializr.email.business.bo.EmailBO;
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

    @Override
    public Long sendEmail(EmailBO bo){
        Email email = new Email();
        // TODO 部分属性识别
        BeanUtils.copyProperties(bo,email);
        try {
            getEmailClient().send(email);
        } catch (Exception e) {
            log.error(e.getMessage());
          return null;
        }

        EmailEntity emailEntity = new EmailEntity();
        BeanUtils.copyProperties(bo,emailEntity);
        emailEntity.setId(new Snowflake().nextId());
        EmailEntity save = mongoTemplate.save(emailEntity);
        return save.getId();
    }


    private EmailClientDefault getEmailClient(){
        return EmailClientDefault.getInstance(this.emailDebug,this.emailServerHost,this.emailServerPort,this.emailServerProtocol,this.emailServerAuth,this.emailServerUser,this.emailServerPassword);
    }
}
