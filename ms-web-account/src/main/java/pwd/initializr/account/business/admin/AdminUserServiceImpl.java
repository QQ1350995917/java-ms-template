package pwd.initializr.account.business.admin;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.account.business.admin.bo.AdminUserBO;
import pwd.initializr.account.persistence.dao.AdminUserDao;
import pwd.initializr.account.persistence.entity.AdminUserEntity;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.common.web.persistence.entity.EntityAble;

/**
 * (AdminUserEntity)表服务实现类
 *
 * @author makejava
 * @since 2020-07-18 22:12:59
 */
@Service("adminUserServiceImpl")
public class AdminUserServiceImpl implements AdminUserService {

  @Resource
  private AdminUserDao adminUserDao;

  @Override
  public Boolean ableById(List<Long> ids, EntityAble able) {
    adminUserDao.ableByIds(ids,able.getNumber());
    return true;
  }

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

  @Override
  public boolean deleteById(List<Long> ids) {
    return this.adminUserDao.deleteByIds(ids) > 0;
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
    BeanUtils.copyProperties(adminUserBO, adminUserEntity);
    adminUserEntity.setCreateTime(new Date());
    adminUserEntity.setUpdateTime(new Date());
    this.adminUserDao.insert(adminUserEntity);
    AdminUserBO adminUserBOResult = new AdminUserBO();
    BeanUtils.copyProperties(adminUserEntity, adminUserBOResult);
    return adminUserBOResult;
  }

  @Override
  public ObjectList<AdminUserBO> queryAllByCondition(AdminUserBO adminUserBO, Long pageIndex,
      Long pageSize) {
    ObjectList<AdminUserBO> result = new ObjectList<>();
    AdminUserEntity queryCondition = new AdminUserEntity();
    BeanUtils.copyProperties(adminUserBO, queryCondition);
    Long total = this.adminUserDao.countAllByCondition(queryCondition);
    if (total == null || total < 1) {
      return result;
    }
    List<AdminUserEntity> adminUserEntities = this.adminUserDao
        .queryAllByCondition(queryCondition, pageIndex * pageSize, pageSize);
    if (adminUserEntities == null) {
      return null;
    }
    adminUserEntities.forEach(adminUserEntity -> {
      AdminUserBO resultItem = new AdminUserBO();
      BeanUtils.copyProperties(adminUserEntity, resultItem);
      result.getElements().add(resultItem);
    });
    result.setIndex(pageIndex);
    result.setSize(pageSize);
    result.setTotal(total);
    return result;
  }

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  @Override
  public AdminUserBO queryById(Long id) {
    AdminUserEntity adminUserEntity = this.adminUserDao.queryById(id);
    if (adminUserEntity == null) {
      return null;
    }
    AdminUserBO adminUserBO = new AdminUserBO();
    BeanUtils.copyProperties(adminUserEntity, adminUserBO);
    return adminUserBO;
  }

  /**
   * 修改数据
   *
   * @param adminUserBO 实例对象
   * @return 实例对象
   */
  @Override
  public Integer update(AdminUserBO adminUserBO) {
    return this.adminUserDao.update(adminUserBO);
  }
}
