package pwd.initializr.account.business.user;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import pwd.initializr.account.business.user.bo.UserUserBO;
import pwd.initializr.account.persistence.dao.UserUserDao;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * (UserUserEntity)表服务实现类
 *
 * @author makejava
 * @since 2020-07-18 22:35:25
 */
@Service("userUserService")
public class UserUserServiceImpl implements UserUserService {

  @Resource
  private UserUserDao userUserDao;

  @Override
  public boolean deleteById(Long id) {
    return false;
  }

  @Override
  public UserUserBO insert(UserUserBO userUserBO) {
    return null;
  }

  @Override
  public ObjectList<UserUserBO> queryAllByCondition(UserUserBO userUserBO, Long offset,
      Long limit) {
    return null;
  }

  @Override
  public UserUserBO queryById(Long id) {
    return null;
  }

  @Override
  public UserUserBO update(UserUserBO userUserBO) {
    return null;
  }
}