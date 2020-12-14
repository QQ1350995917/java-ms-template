package pwd.initializr.email.business;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
 * date 2020-12-14 16:22
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public interface EmailWarpService {

    EmailBO createEmail(EmailBO emailBO, Set<EmailBoxBO> emailBoxBOS, EmailContentBO emailContentBO,
        Set<EmailAttachmentBO> emailAttachmentBOS, Set<EmailExtendBO> emailExtendBOS);
}
