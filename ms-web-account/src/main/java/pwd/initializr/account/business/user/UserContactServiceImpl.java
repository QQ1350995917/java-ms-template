package pwd.initializr.account.business.user;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import pwd.initializr.account.persistence.dao.UserContactDao;
import pwd.initializr.account.persistence.entity.UserContactEntity;

/**
 * (UserContactEntity)表服务实现类
 *
 * @author makejava
 * @since 2020-07-18 22:35:25
 */
@Service("userContactService")
public class UserContactServiceImpl implements UserContactService {

  @Resource
  private UserContactDao userContactDao;

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  @Override
  public boolean deleteById(Long id) {
    return this.userContactDao.deleteById(id) > 0;
  }

  /**
   * 新增数据
   *
   * @param userContact 实例对象
   * @return 实例对象
   */
  @Override
  public UserContactEntity insert(UserContactEntity userContact) {
    this.userContactDao.insert(userContact);
    return userContact;
  }

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  @Override
  public List<UserContactEntity> queryAllByLimit(int offset, int limit) {
    return this.userContactDao.queryAllByLimit(offset, limit);
  }

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  @Override
  public UserContactEntity queryById(Long id) {
    return this.userContactDao.queryById(id);
  }

  /**
   * 修改数据
   *
   * @param userContact 实例对象
   * @return 实例对象
   */
  @Override
  public UserContactEntity update(UserContactEntity userContact) {
    this.userContactDao.update(userContact);
    return this.queryById(userContact.getId());
  }
}