package pwd.initializr.account.business.user;


import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import pwd.initializr.account.business.user.bo.UserAccountBO;
import pwd.initializr.account.persistence.dao.UserAccountDao;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * (UserAccountEntity)表服务实现类
 *
 * @author makejava
 * @since 2020-07-18 22:35:19
 */
@Service("userAccountService")
public class UserAccountServiceImpl implements UserAccountService {

  @Resource
  private UserAccountDao userAccountDao;

  @Override
  public boolean deleteById(Long id) {
    return false;
  }

  @Override
  public UserAccountBO insert(UserAccountBO userAccountBO) {
    return null;
  }

  @Override
  public UserAccountBO loginByNameAndPwd(String loginName, String loginPwd) {
    return null;
  }

  @Override
  public ObjectList<UserAccountBO> queryAllByCondition(UserAccountBO userAccountBO, Long offset,
      Long limit) {
    return null;
  }

  @Override
  public ObjectList<UserAccountBO> queryAllByUserId(Long userId) {
    return null;
  }

  @Override
  public UserAccountBO queryById(Long id) {
    return null;
  }

  @Override
  public UserAccountBO update(UserAccountBO userAccountBO) {
    return null;
  }
}