package pwd.initializr.account.business.user;


import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.account.business.user.bo.UserAccountBO;
import pwd.initializr.account.persistence.dao.UserAccountDao;
import pwd.initializr.account.persistence.entity.UserAccountEntity;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.common.web.persistence.entity.EntityDel;

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
  public Integer ableById(List<Long> ids, EntityAble able) {
    return userAccountDao.ableByIds(ids, able.getNumber());
  }

  @Override
  public Integer ableByUserId(List<Long> userIds, EntityAble able) {
    return userAccountDao.ableByUserIds(userIds, able.getNumber());
  }

  @Override
  public Integer ableByUserId(Long userId, EntityAble able) {
    return userAccountDao.ableById(userId, able.getNumber());
  }

  @Override
  public Integer deleteById(Long id) {
    return userAccountDao.deleteById(id);
  }

  @Override
  public Integer deleteById(List<Long> ids) {
    return userAccountDao.deleteByIds(ids);
  }

  @Override
  public Boolean existLoginName(String loginName) {
    return userAccountDao.existName(loginName) == null;
  }

  @Override
  public UserAccountBO insert(UserAccountBO userAccountBO) {
    UserAccountEntity userAccountEntity = new UserAccountEntity();
    BeanUtils.copyProperties(userAccountBO, userAccountEntity);
    userAccountEntity.setPwdTime(new Date());
    userAccountEntity.setAble(EntityAble.ENABLE.getNumber());
    userAccountEntity.setDel(EntityDel.YES.getNumber());
    userAccountEntity.setCreateTime(new Date());
    userAccountEntity.setUpdateTime(new Date());
    userAccountDao.insert(userAccountEntity);

    UserAccountBO result = new UserAccountBO();
    BeanUtils.copyProperties(userAccountEntity, result);
    return result;
  }

  @Override
  public PageableQueryResult<UserAccountBO> queryAllByCondition(UserAccountBO userAccountBO,
      Long pageIndex,
      Long pageSize) {
    PageableQueryResult<UserAccountBO> resultData = new PageableQueryResult<>();
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
  public PageableQueryResult<UserAccountBO> queryAllByUserId(Long userId) {
    PageableQueryResult<UserAccountBO> resultData = new PageableQueryResult<>();
    List<UserAccountEntity> userAccountEntities = userAccountDao.queryAllByUid(userId);
    resultData.setTotal(Long.valueOf(userAccountEntities.size()));
    resultData.setIndex(0L);
    resultData.setSize(Long.valueOf(userAccountEntities.size()));
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
