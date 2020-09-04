package pwd.initializr.account.business.user;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.account.business.user.bo.UserUserBO;
import pwd.initializr.account.persistence.dao.UserUserDao;
import pwd.initializr.account.persistence.entity.UserUserEntity;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.common.web.persistence.entity.EntityDel;

/**
 * (UserUserEntity)表服务实现类
 *
 * @author makejava
 * @since 2020-07-18 22:35:25
 */
@Service("userService")
public class UserUserServiceImpl implements UserUserService {

  @Resource
  private UserUserDao userUserDao;

  @Override
  public Integer ableById(List<Long> ids, EntityAble able) {
    return userUserDao.ableByIds(ids,able.getNumber());
  }

  @Override
  public Integer ableById(Long id, EntityAble able) {
    return null;
  }

  @Override
  public Integer deleteById(Long id) {
    return userUserDao.deleteById(id);
  }

  @Override
  public Integer deleteById(List<Long> ids) {
    return userUserDao.deleteByIds(ids);
  }

  @Override
  public UserUserBO insert(UserUserBO userUserBO) {
    UserUserEntity userUserEntity = new UserUserEntity();
    BeanUtils.copyProperties(userUserBO, userUserEntity);
    userUserEntity.setAble(EntityAble.ENABLE.getNumber());
    userUserEntity.setDel(EntityDel.NO.getNumber());
    userUserEntity.setCreateTime(new Date());
    userUserEntity.setUpdateTime(new Date());
    this.userUserDao.insert(userUserEntity);
    UserUserBO userUserBOResult = new UserUserBO();
    BeanUtils.copyProperties(userUserEntity, userUserBOResult);
    return userUserBOResult;
  }

  @Override
  public PageableQueryResult<UserUserBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes, LinkedHashSet<SortBO> sorts,
      Long pageIndex, Long pageSize) {
    PageableQueryResult<UserUserBO> resultData = new PageableQueryResult<>();
    Long queryCount = userUserDao.countAllByCondition(scopes);
    if (queryCount < 1) {
      return resultData;
    }
    List<UserUserEntity> queryResult = userUserDao
        .queryAllByCondition(scopes,sorts, pageIndex * pageSize, pageSize);
    queryResult.forEach(userUserEntity -> {
      UserUserBO dataItem = new UserUserBO();
      BeanUtils.copyProperties(userUserEntity, dataItem);
      resultData.getElements().add(dataItem);
    });
    resultData.setSize(pageSize);
    resultData.setIndex(pageIndex);
    resultData.setTotal(queryCount);
    return resultData;
  }

  @Override
  public UserUserBO queryById(Long id) {
    UserUserBO userUserBO = new UserUserBO();
    UserUserEntity userUserEntity = userUserDao.queryById(id);
    if (userUserEntity == null) {
      return null;
    }
    BeanUtils.copyProperties(userUserEntity, userUserBO);
    return userUserBO;
  }

  @Override
  public Integer update(UserUserBO userUserBO) {
    UserUserEntity userUserEntity = new UserUserEntity();
    BeanUtils.copyProperties(userUserBO, userUserEntity);
    return userUserDao.update(userUserEntity);
  }
}
