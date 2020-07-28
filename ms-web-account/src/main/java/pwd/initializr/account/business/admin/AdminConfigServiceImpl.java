package pwd.initializr.account.business.admin;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pwd.initializr.account.business.admin.bo.AdminConfigBO;
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
   * @param adminConfigBO 实例对象
   * @return 实例对象
   */
  @Override
  public AdminConfigBO insert(AdminConfigBO adminConfigBO) {
    AdminConfigEntity adminConfigEntity = new AdminConfigEntity();
    BeanUtils.copyProperties(adminConfigBO, adminConfigEntity);
    adminConfigEntity.setCreateTime(new Date());
    adminConfigEntity.setUpdateTime(new Date());
    this.adminConfigDao.insert(adminConfigEntity);
    AdminConfigBO adminConfigBOResult = new AdminConfigBO();
    BeanUtils.copyProperties(adminConfigEntity, adminConfigBOResult);
    return adminConfigBOResult;
  }

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  @Override
  public List<AdminConfigBO> queryAllByLimit(int offset, int limit) {

    List<AdminConfigEntity> adminConfigEntities = this.adminConfigDao
        .queryAllByLimit(offset, limit);

    for (AdminConfigEntity adminConfigEntity : adminConfigEntities) {

    }

    return null;
  }

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  @Override
  public AdminConfigBO queryById(Long id) {
    return null;
  }

  @Override
  public AdminConfigBO queryByKey(String key) {
    AdminConfigEntity adminConfigEntity = this.adminConfigDao.queryByKey(key);
    if (adminConfigEntity == null) {
      return null;
    }
    AdminConfigBO adminConfigBO = new AdminConfigBO();
    BeanUtils.copyProperties(adminConfigEntity, adminConfigBO);
    return adminConfigBO;
  }

  /**
   * 修改数据
   *
   * @param adminConfigBO 实例对象
   * @return 实例对象
   */
  @Override
  public AdminConfigBO update(AdminConfigBO adminConfigBO) {
    this.adminConfigDao.update(adminConfigBO);
    return this.queryById(adminConfigBO.getId());
  }
}
