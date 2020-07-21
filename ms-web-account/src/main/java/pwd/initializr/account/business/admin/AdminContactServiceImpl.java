package pwd.initializr.account.business.admin;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import pwd.initializr.account.persistence.dao.AdminContactDao;
import pwd.initializr.account.persistence.entity.AdminContactEntity;

/**
 * (AdminContactEntity)表服务实现类
 *
 * @author makejava
 * @since 2020-07-18 22:18:58
 */
@Service("adminContactService")
public class AdminContactServiceImpl implements AdminContactService {

  @Resource
  private AdminContactDao adminContactDao;

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  @Override
  public boolean deleteById(Long id) {
    return this.adminContactDao.deleteById(id) > 0;
  }

  /**
   * 新增数据
   *
   * @param adminContact 实例对象
   * @return 实例对象
   */
  @Override
  public AdminContactEntity insert(AdminContactEntity adminContact) {
    this.adminContactDao.insert(adminContact);
    return adminContact;
  }

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  @Override
  public List<AdminContactEntity> queryAllByLimit(int offset, int limit) {
//    return this.adminContactDao.queryAllByLimit(offset, limit);
    return null;
  }

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  @Override
  public AdminContactEntity queryById(Long id) {
    return this.adminContactDao.queryById(id);
  }

  /**
   * 修改数据
   *
   * @param adminContact 实例对象
   * @return 实例对象
   */
  @Override
  public AdminContactEntity update(AdminContactEntity adminContact) {
    this.adminContactDao.update(adminContact);
    return this.queryById(adminContact.getId());
  }
}