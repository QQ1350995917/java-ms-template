package pwd.initializr.account.business.user;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import pwd.initializr.account.persistence.dao.UserUserDao;
import pwd.initializr.account.persistence.entity.UserUserEntity;

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

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  @Override
  public boolean deleteById(Long id) {
    return this.userUserDao.deleteById(id) > 0;
  }

  /**
   * 新增数据
   *
   * @param userUser 实例对象
   * @return 实例对象
   */
  @Override
  public UserUserEntity insert(UserUserEntity userUser) {
    this.userUserDao.insert(userUser);
    return userUser;
  }

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  @Override
  public List<UserUserEntity> queryAllByLimit(int offset, int limit) {
    return this.userUserDao.queryAllByLimit(offset, limit);
  }

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  @Override
  public UserUserEntity queryById(Long id) {
    return this.userUserDao.queryById(id);
  }

  /**
   * 修改数据
   *
   * @param userUser 实例对象
   * @return 实例对象
   */
  @Override
  public UserUserEntity update(UserUserEntity userUser) {
    this.userUserDao.update(userUser);
    return this.queryById(userUser.getId());
  }
}