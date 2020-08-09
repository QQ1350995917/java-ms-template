package pwd.initializr.account.test.business.admin;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.account.AccountApplication;
import pwd.initializr.account.business.admin.SessionService;

/**
 * pwd.initializr.account.test.admin.service@ms-web-initializr
 *
 * <h1>单元测试：SessionServiceTest</h1>
 *
 * date 2020-07-21 22:56
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AccountApplication.class})
public class SessionServiceTest {

  @Autowired
  private SessionService sessionService;

}
