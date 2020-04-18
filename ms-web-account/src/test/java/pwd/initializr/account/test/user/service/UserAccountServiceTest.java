package pwd.initializr.account.test.user.service;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.account.AccountApplication;
import pwd.initializr.account.business.user.UserAccountService;
import pwd.initializr.account.business.user.bo.User;
import pwd.initializr.account.business.user.bo.UserAccount;
import pwd.initializr.account.persistence.entity.UserAccountType;

/**
 * pwd.initializr.account.test.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-21 16:25
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AccountApplication.class})
public class UserAccountServiceTest {

  @Autowired
  private UserAccountService userAccountService;

  static String[] names = {"吕布","颜良","关羽","马超","张飞","赵云","典韦","文丑","文鸯","许褚","黄忠","孙策","太史慈","夏侯敦","夏侯渊","张郃","张辽","徐晃","庞德","甘宁","周泰","魏延","邓艾","姜维"};
  @Test
  public void testCreateUserAccount() {
    Stream.of(names).forEach(name -> {
      User user = new User(name, "18511694468");
      UserAccount account = new UserAccount(user.getId(), name, "123456",
          UserAccountType.ByPhoneNumber);
      user.setAccounts(Arrays.asList(new UserAccount[]{account}));
      User userAndAccount = userAccountService.createUserAndAccount(user);
      Assert.assertNotNull(userAndAccount.getId());
      Assert.assertNotNull(userAndAccount.getAccounts());
      Assert.assertNotNull(userAndAccount.getAccounts().get(0).getId());
    });
  }

  @Test
  public void testFindAccountByLoginNameAndPassword() {
    UserAccount 吕布 = userAccountService.findAccountByLoginNameAndPassword("吕布", "123456");
    Assert.assertNotNull(吕布);
  }


  @Test
  public void testLargeData(){

    for (int i = 1694471; i < 6694468; i++) {
      String pre = "1851" + i;
      User user = new User(pre, pre);
      UserAccount account = new UserAccount(user.getId(), pre, "123456",
          UserAccountType.ByPhoneNumber);
      user.setAccounts(Arrays.asList(new UserAccount[]{account}));
      userAccountService.createUserAndAccount(user);

      if (i % 10000 == 0) {
        System.out.println(i);
      }
    }
  }
}
