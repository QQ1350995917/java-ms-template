package pwd.initializr.account.test.business.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.account.business.admin.EmailService;
import pwd.initializr.common.mw.redis.RedisClient;
import pwd.initializr.email.rpc.RPCEmailContentVO;
import pwd.initializr.email.rpc.RPCEmailInput;

/**
 * pwd.initializr.account.test.business.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-08-01 15:29
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceTest {

  @Autowired
  private EmailService emailService;

  @Test
  public void sendEmail() {
    RPCEmailInput rpcEmailInput = new RPCEmailInput();
    rpcEmailInput.setApp("account");
    // TODO 埋了一个联系人集合的坑
    rpcEmailInput.getBox().getTo().add("www.dingpengwei@foxmail.com");
    rpcEmailInput.setContent(new RPCEmailContentVO("密码" ,"123456"));
    emailService.sendEmail(rpcEmailInput);
  }

}
