package pwd.initializr.account.business.user;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import pwd.initializr.account.persistence.dao.UserConfigDao;
import pwd.initializr.account.persistence.entity.UserConfigEntity;

/**
 * (UserConfigEntity)表服务实现类
 *
 * @author makejava
 * @since 2020-07-18 22:35:24
 */
@Service("configService")
public class UserConfigServiceImpl implements UserConfigService {

  @Resource
  private UserConfigDao userConfigDao;

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  @Override
  public Integer deleteById(Long id) {
    return this.userConfigDao.deleteById(id);
  }

  /**
   * 新增数据
   *
   * @param userConfig 实例对象
   * @return 实例对象
   */
  @Override
  public UserConfigEntity insert(UserConfigEntity userConfig) {
    this.userConfigDao.insert(userConfig);
    return userConfig;
  }

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  @Override
  public List<UserConfigEntity> queryAllByLimit(int offset, int limit) {
    return this.userConfigDao.queryAllByLimit(offset, limit);
  }

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  @Override
  public UserConfigEntity queryById(Long id) {
    return this.userConfigDao.queryById(id);
  }

  /**
   * 修改数据
   *
   * @param userConfig 实例对象
   * @return 实例对象
   */
  @Override
  public UserConfigEntity update(UserConfigEntity userConfig) {
    this.userConfigDao.update(userConfig);
    return this.queryById(userConfig.getId());
  }
}