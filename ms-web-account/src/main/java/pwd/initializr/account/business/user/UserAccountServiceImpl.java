package pwd.initializr.account.business.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwd.initializr.account.business.user.bo.UserBO;
import pwd.initializr.account.business.user.bo.UserAccountBO;
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
  public UserBO createAccountForUser(UserBO userBO) {
    List<UserAccountBO> accounts = userBO.getAccounts();
    UserAccountBO userAccountBO = accounts.get(0);
    UserAccountEntity userAccountEntity = new UserAccountEntity();
    BeanUtils.copyProperties(userAccountBO, userAccountEntity);
    userAccountEntity.setUserId(userBO.getId());
    userAccountDao.insert(userAccountEntity);
    BeanUtils.copyProperties(userAccountEntity, userAccountBO);
    return userBO;
  }

  @Transactional
  @Override
  public UserBO createUserAndAccount(UserBO userBO) {
    UserEntity userEntity = new UserEntity();
    BeanUtils.copyProperties(userBO, userEntity);
    Date date = new Date();
    userEntity.setCreateTime(date);
    userEntity.setUpdateTime(date);
    userDao.insert(userEntity);

    UserAccountBO userAccountBO = userBO.getAccounts().get(0);
    UserAccountEntity userAccountEntity = new UserAccountEntity();
    BeanUtils.copyProperties(userAccountBO, userAccountEntity);
    userAccountEntity.setUserId(userEntity.getId());
    userAccountEntity.setCreateTime(date);
    userAccountEntity.setUpdateTime(date);
    userAccountDao.insert(userAccountEntity);

    BeanUtils.copyProperties(userAccountEntity, userAccountBO);
    BeanUtils.copyProperties(userEntity, userBO);

    return userBO;
  }

  @Override
  public UserAccountBO findAccountByLoginNameAndPassword(String loginName, String password) {
    QueryWrapper<UserAccountEntity> userAccountEntityQueryWrapper = new QueryWrapper<>();
    userAccountEntityQueryWrapper.lambda().eq(UserAccountEntity::getLoginName, loginName)
        .eq(UserAccountEntity::getPassword, password);
    UserAccountEntity userAccountEntity = userAccountDao.selectOne(userAccountEntityQueryWrapper);
    UserAccountBO userAccountBO = new UserAccountBO();
    BeanUtils.copyProperties(userAccountEntity, userAccountBO);
    return userAccountBO;
  }

  @Override
  public UserBO findUserByUserId(Long id) {
    UserEntity userEntity = userDao.selectById(id);
    UserBO userBO = new UserBO();
    BeanUtils.copyProperties(userEntity, userBO);
    return userBO;
  }

  @Override
  public ObjectList<UserBO> listAccount(UserAccountBO userAccountBO, Integer offset, Integer size) {
    return null;
  }

  @Override
  public ObjectList<UserBO> listAccountByUser(UserBO userBO, Integer offset, Integer size) {
    return null;
  }

  @Override
  public ObjectList<UserBO> listUser(UserBO userBO, Integer offset, Integer size) {
    QueryWrapper<UserEntity> userEntityQueryWrapper = new QueryWrapper<>();

    IPage<UserEntity> userEntityIPage = userDao
        .selectPage(new Page<>(offset, 12), userEntityQueryWrapper);

    List<UserEntity> userEntities = userEntityIPage.getRecords();

    List<UserBO> collect = userEntities.stream().map(userEntity -> {
      UserBO userBOItem = new UserBO();
      BeanUtils.copyProperties(userEntity, userBOItem);
      return userBOItem;
    }).collect(Collectors
        .toList());

    ObjectList<UserBO> userObjectList = new ObjectList<>();
    userObjectList.setTotal(userEntityIPage.getTotal());
    userObjectList.setPages(userEntityIPage.getPages());
    userObjectList.setIndex(userEntityIPage.getCurrent());
    userObjectList.setSize(userEntityIPage.getSize());
    userObjectList.setElements(collect);
    return userObjectList;
  }
}
