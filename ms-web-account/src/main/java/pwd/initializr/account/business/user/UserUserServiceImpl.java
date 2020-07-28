package pwd.initializr.account.business.user;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.account.business.user.bo.UserUserBO;
import pwd.initializr.account.persistence.dao.UserUserDao;
import pwd.initializr.account.persistence.entity.UserUserEntity;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.common.web.persistence.entity.EntityAble;

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
  public Boolean ableById(List<Long> ids, EntityAble able) {
    return null;
  }

  @Override
  public Boolean ableById(Long id, EntityAble able) {
    return null;
  }

  @Override
  public boolean deleteById(Long id) {
    userUserDao.deleteById(id);
    return true;
  }

  @Override
  public boolean deleteById(List<Long> ids) {
    return false;
  }

  @Override
  public UserUserBO insert(UserUserBO userUserBO) {
    return null;
  }

  @Override
  public ObjectList<UserUserBO> queryAllByCondition(UserUserBO userUserBO, Long pageIndex,
      Long pageSize) {
    ObjectList<UserUserBO> resultData = new ObjectList<>();
    UserUserEntity queryCondition = new UserUserEntity();
    BeanUtils.copyProperties(userUserBO, queryCondition);
    Long queryCount = userUserDao.countAllByCondition(queryCondition);
    if (queryCount < 1) {
      return resultData;
    }
    List<UserUserEntity> queryResult = userUserDao
        .queryAllByCondition(queryCondition, pageIndex * pageSize, pageSize);
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
