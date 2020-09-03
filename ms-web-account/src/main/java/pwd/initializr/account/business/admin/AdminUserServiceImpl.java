package pwd.initializr.account.business.admin;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwd.initializr.account.business.admin.bo.AdminUserBO;
import pwd.initializr.account.persistence.dao.AdminUserDao;
import pwd.initializr.account.persistence.entity.AdminUserEntity;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.common.web.persistence.entity.EntityDel;

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
  public Integer ableById(List<Long> ids, EntityAble able) {
    return adminUserDao.ableByIds(ids, able.getNumber());
  }

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  @Override
  public Integer deleteById(Long id) {
    return this.adminUserDao.deleteById(id);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Integer deleteById(List<Long> ids) {
    this.ableById(ids,EntityAble.DISABLE);
    return this.adminUserDao.deleteByIds(ids);
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
    adminUserEntity.setAble(EntityAble.DISABLE.getNumber());
    adminUserEntity.setDel(EntityDel.NO.getNumber());
    adminUserEntity.setCreateTime(new Date());
    adminUserEntity.setUpdateTime(new Date());
    this.adminUserDao.insert(adminUserEntity);
    AdminUserBO adminUserBOResult = new AdminUserBO();
    BeanUtils.copyProperties(adminUserEntity, adminUserBOResult);
    return adminUserBOResult;
  }

  @Override
  public PageableQueryResult<AdminUserBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopeBOS,
      LinkedHashSet<SortBO> sortBOS, Long pageIndex, Long pageSize) {
    PageableQueryResult<AdminUserBO> result = new PageableQueryResult<>();
    Long total = this.adminUserDao.countAllByCondition(scopeBOS);
    if (total == null || total < 1) {
      return result;
    }
    List<AdminUserEntity> adminUserEntities = this.adminUserDao
        .queryAllByCondition(scopeBOS, sortBOS, pageIndex * pageSize, pageSize);
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
