package pwd.initializr.account.business.user;


import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import pwd.initializr.account.persistence.dao.UserAccountDao;
import pwd.initializr.account.persistence.entity.UserAccountEntity;

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

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  @Override
  public boolean deleteById(Long id) {
    return this.userAccountDao.deleteById(id) > 0;
  }

  /**
   * 新增数据
   *
   * @param userAccount 实例对象
   * @return 实例对象
   */
  @Override
  public UserAccountEntity insert(UserAccountEntity userAccount) {
    this.userAccountDao.insert(userAccount);
    return userAccount;
  }

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  @Override
  public List<UserAccountEntity> queryAllByLimit(int offset, int limit) {
    return this.userAccountDao.queryAllByLimit(offset, limit);
  }

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  @Override
  public UserAccountEntity queryById(Long id) {
    return this.userAccountDao.queryById(id);
  }

  /**
   * 修改数据
   *
   * @param userAccount 实例对象
   * @return 实例对象
   */
  @Override
  public UserAccountEntity update(UserAccountEntity userAccount) {
    this.userAccountDao.update(userAccount);
    return this.queryById(userAccount.getId());
  }
}