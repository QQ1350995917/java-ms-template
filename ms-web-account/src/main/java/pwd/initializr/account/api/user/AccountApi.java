package pwd.initializr.account.api.user;

import pwd.initializr.account.api.user.vo.SignUpByPhoneInput;
import pwd.initializr.common.web.api.vo.SMSCodeInput;

/**
 * pwd.initializr.account.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-14 21:17
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface AccountApi {

    void getSMSCode(SMSCodeInput input);

    void createByPhone(SignUpByPhoneInput input);

}
