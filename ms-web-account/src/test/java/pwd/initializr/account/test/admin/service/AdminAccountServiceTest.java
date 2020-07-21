package pwd.initializr.account.test.admin.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.account.AccountApplication;
import pwd.initializr.account.business.admin.AdminAccountService;

/**
 * pwd.initializr.account.test.admin.service@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-07-21 22:56
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AccountApplication.class})
public class AdminAccountServiceTest {

  @Autowired
  private AdminAccountService adminAccountService;

  @Test
  public void loginByNameAndPwd() {
    try {
      adminAccountService.loginByNameAndPwd("","pwd");
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
  }
}
