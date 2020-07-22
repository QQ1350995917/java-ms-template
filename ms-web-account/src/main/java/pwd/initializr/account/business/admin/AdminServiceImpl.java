package pwd.initializr.account.business.admin;

import java.util.LinkedHashSet;
import org.springframework.stereotype.Service;
import pwd.initializr.account.business.admin.bo.AdminUserBO;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * (AdminUserBO)表服务实现类
 *
 * @author makejava
 * @since 2020-04-25 20:16:11
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {

//  @Resource
//  private AdminDao adminDao;

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  @Override
  public Integer deleteById(Integer id) {
//    return this.adminDao.deleteById(id);
    return null;
  }

  /**
   * 新增数据
   *
   * @param adminUserBO 实例对象
   * @return 实例对象
   */
  @Override
  public AdminUserBO insert(AdminUserBO adminUserBO) {
//    AdminEntity adminEntity = new AdminEntity();
//    BeanUtils.copyProperties(adminUserBO, adminEntity);
//    Date initDate = new Date();
//    adminEntity.setAbleStatus(ConstantAbleStatus.DISABLE.value());
//    adminEntity.setDelStatus(ConstantDeleteStatus.EXISTING.value());
//    adminEntity.setCreateTime(initDate);
//    adminEntity.setUpdateTime(initDate);
//    this.adminDao.insert(adminEntity);
//    AdminUserBO result = new AdminUserBO();
//    BeanUtils.copyProperties(adminEntity, result);
//    return result;
    return null;
  }

  /**
   * 查询多条数据
   *
   * @return 对象列表
   */
  @Override
  public ObjectList<AdminUserBO> queryByCondition(AdminUserBO adminUserBO,
      LinkedHashSet<String> orderBys,
      Integer pageIndex, Integer pageSize) {
//    AdminEntity adminEntity = new AdminEntity();
//    BeanUtils.copyProperties(adminUserBO, adminEntity);
//    Integer countAll = this.adminDao.countAll(adminEntity);
//    List<AdminEntity> adminEntities = this.adminDao
//        .queryByCondition(adminEntity, orderBys, pageIndex * pageSize, pageSize);
//    List<AdminUserBO> adminUserBOS = new LinkedList<>();
//    adminEntities.forEach(adminEntity1 -> {
//      AdminUserBO adminUserBO1 = new AdminUserBO();
//      BeanUtils.copyProperties(adminEntity1, adminUserBO1);
//      adminUserBOS.add(adminUserBO1);
//    });
//
//    ObjectList<AdminUserBO> result = new ObjectList<>();
//    result.setElements(adminUserBOS);
//    result.setTotal(countAll.longValue());
//    result.setIndex(pageIndex.longValue());
//    result.setSize(pageSize.longValue());
//    return result;

    return null;
  }

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  @Override
  public AdminUserBO queryById(Integer id) {
//    AdminEntity adminEntity = this.adminDao.queryById(id);
    AdminUserBO adminUserBO = new AdminUserBO();
//    BeanUtils.copyProperties(adminEntity, adminUserBO);
    return adminUserBO;
  }

//  @Override
//  public AdminUserBO queryByLoginNameAndLoginPassword(String loginName, String loginPwd) {
//    AdminEntity adminEntity = this.adminDao
//        .queryByLoginNameAndLoginPassword(loginName, loginPwd);
//    if (adminEntity == null) {
//      return null;
//    }
//    AdminUserBO adminUserBO = new AdminUserBO();
//    BeanUtils.copyProperties(adminEntity, adminUserBO);
//    return adminUserBO;
//  }

  /**
   * 修改数据
   *
   * @param admin 实例对象
   * @return 实例对象
   */
  @Override
  public Integer update(AdminUserBO admin) {
//    return this.adminDao.update(admin);
    return null;
  }
}
