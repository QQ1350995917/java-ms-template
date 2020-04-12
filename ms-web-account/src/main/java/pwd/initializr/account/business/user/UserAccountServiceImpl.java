package pwd.initializr.account.business.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwd.initializr.account.business.user.bo.User;
import pwd.initializr.account.business.user.bo.UserAccount;
import pwd.initializr.account.persistence.dao.UserAccountDao;
import pwd.initializr.account.persistence.dao.UserDao;
import pwd.initializr.account.persistence.entity.UserAccountEntity;
import pwd.initializr.account.persistence.entity.UserEntity;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-21 16:19
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {

  @Autowired
  private UserAccountDao userAccountDao;
  @Autowired
  private UserDao userDao;


  @Transactional
  @Override
  public User createAccountForUser(User user) {
    List<UserAccount> accounts = user.getAccounts();
    UserAccount userAccount = accounts.get(0);
    UserAccountEntity userAccountEntity = new UserAccountEntity();
    BeanUtils.copyProperties(userAccount, userAccountEntity);
    userAccountEntity.setUserId(user.getId());
    userAccountDao.insert(userAccountEntity);
    BeanUtils.copyProperties(userAccountEntity, userAccount);
    return user;
  }

  @Transactional
  @Override
  public User createUserAndAccount(User user) {
    UserEntity userEntity = new UserEntity();
    BeanUtils.copyProperties(user, userEntity);
    userDao.insert(userEntity);

    UserAccount userAccount = user.getAccounts().get(0);
    UserAccountEntity userAccountEntity = new UserAccountEntity();
    BeanUtils.copyProperties(userAccount, userAccountEntity);
    userAccountEntity.setUserId(userEntity.getId());
    userAccountDao.insert(userAccountEntity);

    BeanUtils.copyProperties(userAccountEntity, userAccount);
    BeanUtils.copyProperties(userEntity, user);

    return user;
  }

  @Override
  public UserAccount findAccountByLoginNameAndPassword(String loginName, String password) {
    QueryWrapper<UserAccountEntity> userAccountEntityQueryWrapper = new QueryWrapper<>();
    userAccountEntityQueryWrapper.lambda().eq(UserAccountEntity::getLoginName, loginName)
        .eq(UserAccountEntity::getPassword, password);
    UserAccountEntity userAccountEntity = userAccountDao.selectOne(userAccountEntityQueryWrapper);
    UserAccount userAccount = new UserAccount();
    BeanUtils.copyProperties(userAccountEntity, userAccount);
    return userAccount;
  }

  @Override
  public User findUserByUserId(Long id) {
    UserEntity userEntity = userDao.selectById(id);
    User user = new User();
    BeanUtils.copyProperties(userEntity, user);
    return user;
  }

  @Override
  public ObjectList<User> listAccount(UserAccount userAccount, Integer offset, Integer size) {
    return null;
  }

  @Override
  public ObjectList<User> listAccountByUser(User user, Integer offset, Integer size) {
    return null;
  }

  @Override
  public ObjectList<User> listUser(User user, Integer offset, Integer size) {
    QueryWrapper<UserEntity> userEntityQueryWrapper = new QueryWrapper<>();

    IPage<UserEntity> userEntityIPage = userDao
        .selectPage(new Page<>(offset, 12), userEntityQueryWrapper);

    List<UserEntity> userEntities = userEntityIPage.getRecords();

    List<User> collect = userEntities.stream().map(userEntity -> {
      User userItem = new User();
      BeanUtils.copyProperties(userEntity, userItem);
      return userItem;
    }).collect(Collectors
        .toList());

    ObjectList<User> userObjectList = new ObjectList<>();
    userObjectList.setTotal(userEntityIPage.getTotal());
    userObjectList.setPages(userEntityIPage.getPages());
    userObjectList.setIndex(userEntityIPage.getCurrent());
    userObjectList.setSize(userEntityIPage.getSize());
    userObjectList.setElements(collect);
    return userObjectList;
  }
}
