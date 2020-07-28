package pwd.initializr.account.business.user;


import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.account.business.user.bo.UserAccountBO;
import pwd.initializr.account.persistence.dao.UserAccountDao;
import pwd.initializr.account.persistence.entity.AdminAccountType;
import pwd.initializr.account.persistence.entity.UserAccountEntity;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.common.web.persistence.entity.EntityAble;

/**
 * (UserAccountEntity)表服务实现类
 *
 * @author makejava
 * @since 2020-07-18 22:35:19
 */
@Service("accountService")
public class UserAccountServiceImpl implements UserAccountService {

  @Resource
  private UserAccountDao userAccountDao;


  @Override
  public Boolean ableById(List<Long> ids, EntityAble able) {
    userAccountDao.ableByIds(ids, able.getNumber());
    return true;
  }

  @Override
  public Boolean ableByUserId(List<Long> userIds, EntityAble able) {
    userAccountDao.ableByUserIds(userIds, able.getNumber());
    return true;
  }

  @Override
  public Boolean ableByUserId(Long userId, EntityAble able) {
    userAccountDao.ableById(userId, able.getNumber());
    return true;
  }

  @Override
  public Boolean deleteById(Long id) {
    userAccountDao.deleteById(id);
    return true;
  }

  @Override
  public Boolean deleteById(List<Long> ids) {
    userAccountDao.deleteByIds(ids);
    return true;
  }

  @Override
  public UserAccountBO insert(UserAccountBO userAccountBO) {
    UserAccountEntity userAccountEntity = new UserAccountEntity();
    BeanUtils.copyProperties(userAccountBO, userAccountEntity);
    userAccountEntity.setPwdTime(new Date());
    userAccountEntity.setType(AdminAccountType.PHONE_PWD.getNumber());
    userAccountEntity.setCreateTime(new Date());
    userAccountEntity.setUpdateTime(new Date());
    userAccountDao.insert(userAccountEntity);

    UserAccountBO result = new UserAccountBO();
    BeanUtils.copyProperties(userAccountEntity, result);
    return result;
  }

  @Override
  public ObjectList<UserAccountBO> queryAllByCondition(UserAccountBO userAccountBO, Long pageIndex,
      Long pageSize) {
    ObjectList<UserAccountBO> resultData = new ObjectList<>();
    UserAccountEntity userAccountEntity = new UserAccountEntity();
    Long total = userAccountDao.countAllByCondition(userAccountEntity);
    if (total < 1) {
      return resultData;
    }
    List<UserAccountEntity> queryResult = userAccountDao
        .queryAllByCondition(userAccountEntity, pageIndex * pageSize, pageSize);
    for (UserAccountEntity accountEntity : queryResult) {
      UserAccountBO resultItem = new UserAccountBO();
      BeanUtils.copyProperties(accountEntity, resultData);
      resultData.getElements().add(resultItem);
    }
    return resultData;
  }

  @Override
  public ObjectList<UserAccountBO> queryAllByUserId(Long userId) {
    ObjectList<UserAccountBO> resultData = new ObjectList<>();
    List<UserAccountEntity> userAccountEntities = userAccountDao.queryAllByUid(userId);
    for (UserAccountEntity userAccountEntity : userAccountEntities) {
      UserAccountBO userAccountBO = new UserAccountBO();
      BeanUtils.copyProperties(userAccountEntity, userAccountBO);
      resultData.getElements().add(userAccountBO);
    }
    return resultData;
  }

  @Override
  public UserAccountBO queryById(Long id) {
    UserAccountEntity userAccountEntity = userAccountDao.queryById(id);
    UserAccountBO userAccountBO = new UserAccountBO();
    BeanUtils.copyProperties(userAccountEntity, userAccountBO);
    return userAccountBO;
  }

  @Override
  public UserAccountBO queryByNameAndPwd(String loginName, String loginPwd) {
    UserAccountEntity userAccountEntity = userAccountDao
        .queryByLoginNameAndPwd(loginName, loginPwd);
    UserAccountBO userAccountBO = new UserAccountBO();
    BeanUtils.copyProperties(userAccountEntity, userAccountBO);
    return userAccountBO;
  }

  @Override
  public Integer update(UserAccountBO userAccountBO) {
    UserAccountEntity userAccountEntity = new UserAccountEntity();
    BeanUtils.copyProperties(userAccountBO, userAccountEntity);
    return userAccountDao.update(userAccountEntity);
  }
}
