package pwd.initializr.account.business.admin;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.account.business.admin.bo.AdminBO;
import pwd.initializr.account.persistence.dao.AdminDao;
import pwd.initializr.account.persistence.entity.AdminEntity;
import pwd.initializr.common.utils.ConstantAbleStatus;
import pwd.initializr.common.utils.ConstantDeleteStatus;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * (AdminBO)表服务实现类
 *
 * @author makejava
 * @since 2020-04-25 20:16:11
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {

  @Resource
  private AdminDao adminDao;

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  @Override
  public Integer deleteById(Integer id) {
    return this.adminDao.deleteById(id);
  }

  /**
   * 新增数据
   *
   * @param adminBO 实例对象
   * @return 实例对象
   */
  @Override
  public AdminBO insert(AdminBO adminBO) {
    AdminEntity adminEntity = new AdminEntity();
    BeanUtils.copyProperties(adminBO, adminEntity);
    Date initDate = new Date();
    adminEntity.setAbleStatus(ConstantAbleStatus.DISABLE.value());
    adminEntity.setDelStatus(ConstantDeleteStatus.EXISTING.value());
    adminEntity.setCreateTime(initDate);
    adminEntity.setUpdateTime(initDate);
    this.adminDao.insert(adminEntity);
    AdminBO result = new AdminBO();
    BeanUtils.copyProperties(adminEntity, result);
    return result;
  }

  /**
   * 查询多条数据
   *
   * @return 对象列表
   */
  @Override
  public ObjectList<AdminBO> queryByCondition(AdminBO adminBO, LinkedHashSet<String> orderBys, Integer pageIndex, Integer pageSize) {
    AdminEntity adminEntity = new AdminEntity();
    BeanUtils.copyProperties(adminBO,adminEntity);
    Integer countAll = this.adminDao.countAll(adminEntity);
    List<AdminEntity> adminEntities = this.adminDao.queryByCondition(adminEntity,orderBys,pageIndex * pageSize, pageSize);
    List<AdminBO> adminBOS = new LinkedList<>();
    adminEntities.forEach(adminEntity1 -> {
      AdminBO adminBO1 = new AdminBO();
      BeanUtils.copyProperties(adminEntity1, adminBO1);
      adminBOS.add(adminBO1);
    });

    ObjectList<AdminBO> result = new ObjectList<>();
    result.setElements(adminBOS);
    result.setTotal(countAll.longValue());
    result.setIndex(pageIndex.longValue());
    result.setSize(pageSize.longValue());
    return result;
  }

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  @Override
  public AdminBO queryById(Integer id) {
    AdminEntity adminEntity = this.adminDao.queryById(id);
    AdminBO adminBO = new AdminBO();
    BeanUtils.copyProperties(adminEntity, adminBO);
    return adminBO;
  }

  /**
   * 修改数据
   *
   * @param admin 实例对象
   * @return 实例对象
   */
  @Override
  public Integer update(AdminBO admin) {
    return this.adminDao.update(admin);
  }
}
