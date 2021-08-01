package pwd.initializr.account.business.admin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pwd.initializr.account.FeignConfig;
import pwd.initializr.email.rpc.RPCEmailInput;

/**
 * pwd.initializr.account.business.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-08-01 14:49
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@FeignClient(value = "email", configuration = FeignConfig.class, fallback = EmailServiceFallbackImpl.class)
public interface EmailService {

  @PostMapping(value = "/api/robot/email", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = {
      MediaType.APPLICATION_JSON_UTF8_VALUE})
  String sendEmail(@RequestBody RPCEmailInput input);
}
