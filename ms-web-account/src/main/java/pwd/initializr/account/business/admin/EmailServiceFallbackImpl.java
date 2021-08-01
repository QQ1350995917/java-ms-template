package pwd.initializr.account.business.admin;

import org.springframework.stereotype.Service;
import pwd.initializr.email.rpc.RPCEmailInput;

/**
 * pwd.initializr.account.business.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-08-01 14:50
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class EmailServiceFallbackImpl implements EmailService {

  @Override
  public String sendEmail(RPCEmailInput input) {
    return null;
  }
}
