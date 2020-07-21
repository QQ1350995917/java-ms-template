//package pwd.initializr.account.test.user.service;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import pwd.initializr.account.AccountApplication;
//import pwd.initializr.account.persistence.dao.UserAccountDao;
//import pwd.initializr.account.persistence.dao.UserDao;
//import pwd.initializr.account.persistence.entity.AccountType;
//import pwd.initializr.account.persistence.entity.UserAccountEntity;
//import pwd.initializr.account.persistence.entity.UserEntity;
//
///**
// * pwd.initializr.account.test.user@ms-web-initializr
// *
// * <h1>TODO what you want to do?</h1>
// *
// * date 2019-09-21 16:25
// *
// * @author DingPengwei[dingpengwei@foxmail.com]
// * @version 1.0.0
// * @since DistributionVersion
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {AccountApplication.class})
//public class UserAccountEntityDaoTest {
//
//  @Autowired
//  private UserDao userDao;
//  @Autowired
//  private UserAccountDao userAccountDao;
//
//
//  @Test
//  public void testCreateUserAccount() {
//
//    UserEntity user = new UserEntity("dingpegnwei", "18511694468");
//    int insertUser = userDao.insert(user);
//    Assert.assertEquals(1, insertUser);
//
//    UserAccountEntity account = new UserAccountEntity(user.getId(), "dingpengwei", "123456",
//        AccountType.ByPhoneNumber);
//    int insertAccount = userAccountDao.insert(account);
//    Assert.assertEquals(1, insertAccount);
//
//
//  }
//}
