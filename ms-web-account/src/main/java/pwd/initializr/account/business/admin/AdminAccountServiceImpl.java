package pwd.initializr.account.business.admin;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import pwd.initializr.account.persistence.dao.AdminAccountDao;
import pwd.initializr.account.persistence.entity.AdminAccountEntity;

/**
 * (AdminAccountEntity)表服务实现类
 *
 * @author makejava
 * @since 2020-07-18 22:19:55
 */
@Service("adminAccountService")
public class AdminAccountServiceImpl implements AdminAccountService {

  @Resource
  private AdminAccountDao adminAccountDao;

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  @Override
  public boolean deleteById(Long id) {
    return this.adminAccountDao.deleteById(id) > 0;
  }

  /**
   * 新增数据
   *
   * @param adminAccount 实例对象
   * @return 实例对象
   */
  @Override
  public AdminAccountEntity insert(AdminAccountEntity adminAccount) {
    this.adminAccountDao.insert(adminAccount);
    return adminAccount;
  }

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  @Override
  public List<AdminAccountEntity> queryAllByLimit(int offset, int limit) {
    return this.adminAccountDao.queryAllByLimit(offset, limit);
  }

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  @Override
  public AdminAccountEntity queryById(Long id) {
    return this.adminAccountDao.queryById(id);
  }

  /**
   * 修改数据
   *
   * @param adminAccount 实例对象
   * @return 实例对象
   */
  @Override
  public AdminAccountEntity update(AdminAccountEntity adminAccount) {
    this.adminAccountDao.update(adminAccount);
    return this.queryById(adminAccount.getId());
  }
}