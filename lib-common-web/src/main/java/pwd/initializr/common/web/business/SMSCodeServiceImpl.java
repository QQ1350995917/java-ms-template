package pwd.initializr.common.web.business;

import org.springframework.stereotype.Service;
import pwd.initializr.common.utils.VerifyUtil;
import pwd.initializr.common.vcode.CodeMessage;
import pwd.initializr.common.vcode.VCodeHelper;
import pwd.initializr.common.web.business.bo.SMSCode;

/**
 * pwd.initializr.common.web.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-20 22:45
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class SMSCodeServiceImpl implements SMSCodeService {

  @Override
  public SMSCode productSMSCode(String phoneNumber) {
    if (!VerifyUtil.phoneNumber(phoneNumber)){
      return null;
    }
    // 从 redis 中根据手机号码查找
    // 找到就返回
    // 找不到就生成新的
    CodeMessage codeMessage = VCodeHelper.productSMSCode();
    String smsCode = codeMessage.getPresented();
    // 放入redis，并设置过期时间
    // 调用短信网关发送短信验证码
    return new SMSCode(phoneNumber,smsCode);
  }

  @Override
  public Boolean match(SMSCode smsCode) {
    // 从 redis 中根据手机号码查找
    // 找不到就返回false
    // 找到就比对，一样返回true，不一样返回false
    return true;
  }
}
