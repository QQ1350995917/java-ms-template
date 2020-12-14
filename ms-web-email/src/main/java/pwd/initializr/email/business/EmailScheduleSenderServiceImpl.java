package pwd.initializr.email.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import pwd.initializr.common.email.EmailClient;
import pwd.initializr.email.business.bo.EmailBO;

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
public class EmailScheduleSenderServiceImpl {

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
    private StorageService storageService;


    public EmailBO sendEmail(EmailBO bo) {
        // TODO
        EmailClient emailClient = new EmailClient();
        try {
            // TODO
            emailClient.send(null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            emailClient.destroyTransport();
        }
        return null;
    }


}
