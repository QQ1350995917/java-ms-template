package pwd.initializr.common.web.business;

import org.springframework.stereotype.Service;
import pwd.initializr.common.web.business.bo.SMSCode;

/**
 * pwd.initializr.common.web.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-20 22:35
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface SMSCodeService {

  SMSCode productSMSCode(String phoneNumber);

  Boolean match(SMSCode smsCode);

  Boolean matchOnce(SMSCode smsCode);

}
