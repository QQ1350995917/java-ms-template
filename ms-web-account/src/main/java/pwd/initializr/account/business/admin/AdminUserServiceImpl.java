package pwd.initializr.account.business.admin;


import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.account.business.admin.bo.AdminUserBO;
import pwd.initializr.account.persistence.dao.AdminUserDao;
import pwd.initializr.account.persistence.entity.AdminUserEntity;

/**
 * (AdminUserEntity)表服务实现类
 *
 * @author makejava
 * @since 2020-07-18 22:12:59
 */
@Service("adminUserService")
public class AdminUserServiceImpl implements AdminUserService {

  @Resource
  private AdminUserDao adminUserDao;

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  @Override
  public boolean deleteById(Long id) {
    return this.adminUserDao.deleteById(id) > 0;
  }

  /**
   * 新增数据
   *
   * @param adminUserBO 实例对象
   * @return 实例对象
   */
  @Override
  public AdminUserBO insert(AdminUserBO adminUserBO) {
    AdminUserEntity adminUserEntity = new AdminUserEntity();
    BeanUtils.copyProperties(adminUserBO,adminUserEntity);
    adminUserEntity.setCreateTime(new Date());
    adminUserEntity.setUpdateTime(new Date());
    this.adminUserDao.insert(adminUserEntity);
    AdminUserBO adminUserBOResult = new AdminUserBO();
    BeanUtils.copyProperties(adminUserEntity,adminUserBOResult);
    return adminUserBOResult;
  }

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  @Override
  public List<AdminUserEntity> queryAllByLimit(int offset, int limit) {
//    return this.adminUserDao.queryAllByLimit(offset, limit);
    return null;
  }

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  @Override
  public AdminUserEntity queryById(Long id) {
    return this.adminUserDao.queryById(id);
  }

  /**
   * 修改数据
   *
   * @param adminUser 实例对象
   * @return 实例对象
   */
  @Override
  public AdminUserEntity update(AdminUserEntity adminUser) {
    this.adminUserDao.update(adminUser);
    return this.queryById(adminUser.getId());
  }
}
