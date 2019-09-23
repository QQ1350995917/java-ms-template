package pwd.initializr.account.test.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.account.business.user.AccountService;
import pwd.initializr.account.business.user.UserAccountService;
import pwd.initializr.account.business.user.UserService;
import pwd.initializr.account.business.user.bo.Account;
import pwd.initializr.account.business.user.bo.User;
import pwd.initializr.account.business.user.bo.UserAccount;
import pwd.initializr.account.persistence.dao.AccountEntity.Type;
import pwd.initializr.account.persistence.dao.ConstantStatus;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * pwd.initializr.account.test.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-21 16:25
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class UserAccountServiceTest {
//
//  @Autowired
//  private UserAccountService userAccountService;
//
//  @Autowired
//  private UserService userService;
//
//  @Autowired
//  private AccountService accountService;
//
//  @Test
//  public void testCreateUserAccount() {
//    User user = new User(null, "pwd-test-0", ConstantStatus.ENABLE.value(),
//        System.currentTimeMillis(),
//        System.currentTimeMillis());
//    Account account = new Account(null, user.getId(), "000000", "000000", Type.GRANT.value(),
//        ConstantStatus.ENABLE.value(), System.currentTimeMillis(), System.currentTimeMillis());
//    UserAccount userAccount = userAccountService.createUserAccount(user, account);
//    System.out.println(userAccount);
//  }
//
//
//  @Test
//  public void testCountUser() {
//    Long result = userService.countUser();
//    System.out.println(result);
//  }
//
//  @Test
//  public void testListUser() {
//    ObjectList<User> userObjectList = userService.listUser();
//    System.out.println(userObjectList);
//  }
//
//  @Test
//  public void testFindUserById() {
//    User byUserId = userService.findByUserId(5L);
//    System.out.println(byUserId);
//  }
//
//  @Test
//  public void testListAccountByUserId() {
//    ObjectList<Account> accountObjectList = accountService.findByUserId(5L);
//    System.out.println(accountObjectList);
//  }


}
