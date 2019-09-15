package pwd.initializr.account.api.user;

import pwd.initializr.account.api.user.vo.ListSignUpByWaysInput;
import pwd.initializr.account.api.user.vo.SignUpByApplyInput;
import pwd.initializr.account.api.user.vo.SignUpByEmailInput;
import pwd.initializr.account.api.user.vo.SignUpByPhoneNumberInput;

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
public interface SignUpApi {

    void listSignUpWays(ListSignUpByWaysInput input);

    void signUpByApply(SignUpByApplyInput input);

    void signUpByPhoneNumber(SignUpByPhoneNumberInput input);

    void signUpByEmail(SignUpByEmailInput input);

    void signUpByWeChat();

    void signUpByAlipay();

    void signUpBySinaBlog();
}
