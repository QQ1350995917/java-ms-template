package pwd.initializr.account.business.admin;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import pwd.initializr.account.persistence.dao.AdminConfigDao;
import pwd.initializr.account.persistence.entity.AdminConfigEntity;

/**
 * (AdminConfigEntity)表服务实现类
 *
 * @author makejava
 * @since 2020-07-18 22:19:33
 */
@Service("adminConfigService")
public class AdminConfigServiceImpl implements AdminConfigService {

  @Resource
  private AdminConfigDao adminConfigDao;

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  @Override
  public boolean deleteById(Long id) {
    return this.adminConfigDao.deleteById(id) > 0;
  }

  /**
   * 新增数据
   *
   * @param adminConfig 实例对象
   * @return 实例对象
   */
  @Override
  public AdminConfigEntity insert(AdminConfigEntity adminConfig) {
    this.adminConfigDao.insert(adminConfig);
    return adminConfig;
  }

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  @Override
  public List<AdminConfigEntity> queryAllByLimit(int offset, int limit) {
    return this.adminConfigDao.queryAllByLimit(offset, limit);
  }

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  @Override
  public AdminConfigEntity queryById(Long id) {
    return this.adminConfigDao.queryById(id);
  }

  /**
   * 修改数据
   *
   * @param adminConfig 实例对象
   * @return 实例对象
   */
  @Override
  public AdminConfigEntity update(AdminConfigEntity adminConfig) {
    this.adminConfigDao.update(adminConfig);
    return this.queryById(adminConfig.getId());
  }
}