package pwd.initializr.account.business.admin;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.account.business.admin.bo.AdminContactBO;
import pwd.initializr.account.persistence.dao.AdminContactDao;
import pwd.initializr.account.persistence.entity.AdminContactEntity;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.common.web.persistence.entity.EntityDel;

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
  public Integer deleteById(Long id) {
    return this.adminContactDao.deleteById(id);
  }

  /**
   * 新增数据
   *
   * @param adminContact 实例对象
   * @return 实例对象
   */
  @Override
  public AdminContactBO insert(AdminContactBO adminContact) {
    this.adminContactDao.insert(adminContact);
    return adminContact;
  }

  /**
   * 新增数据
   *
   * @param adminContacts 实例对象
   * @return 实例对象
   */
  @Override
  public Integer insertByBatch(List<AdminContactBO> adminContacts) {
    adminContacts.forEach(adminContactBO -> {
      adminContactBO.setAble(EntityAble.DISABLE.getNumber());
      adminContactBO.setDel(EntityDel.NO.getNumber());
      adminContactBO.setCreateTime(new Date());
      adminContactBO.setUpdateTime(new Date());
      adminContactBO.setVersion(1L);
    });
    int insertByBatch = this.adminContactDao.insertByBatch(
        adminContacts.stream().map(this::convertAdminContactBOToAdminContactEntity)
            .collect(Collectors.toList()));
    return insertByBatch;
  }

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  @Override
  public List<AdminContactBO> queryAllByLimit(int offset, int limit) {
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
  public AdminContactBO queryById(Long id) {
    return this.convertAdminContactEntityToAdminContactBO(this.adminContactDao.queryById(id));
  }

  @Override
  public List<AdminContactBO> queryByUid(Long uid) {
    List<AdminContactEntity> adminContactEntities = this.adminContactDao.queryAllByUid(uid);
    return adminContactEntities.stream().map(this::convertAdminContactEntityToAdminContactBO).collect(Collectors
        .toList());
  }

  /**
   * 修改数据
   *
   * @param adminContact 实例对象
   * @return 实例对象
   */
  @Override
  public AdminContactBO update(AdminContactBO adminContact) {
    this.adminContactDao.update(adminContact);
    return this.queryById(adminContact.getId());
  }

  protected AdminContactBO convertAdminContactEntityToAdminContactBO (AdminContactEntity adminContactEntity) {
    AdminContactBO adminContactBO = new AdminContactBO();
    BeanUtils.copyProperties(adminContactEntity, adminContactBO);
    return adminContactBO;
  }

  protected AdminContactEntity convertAdminContactBOToAdminContactEntity (AdminContactBO adminContactBO) {
    AdminContactEntity adminContactEntity = new AdminContactEntity();
    BeanUtils.copyProperties(adminContactBO, adminContactEntity);
    return adminContactEntity;
  }
}