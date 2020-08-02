package pwd.initializr.account.business.admin;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pwd.initializr.account.business.admin.bo.AdminAccountBO;
import pwd.initializr.account.persistence.dao.AdminAccountDao;
import pwd.initializr.account.persistence.entity.AdminAccountEntity;
import pwd.initializr.account.persistence.entity.AdminAccountType;
import pwd.initializr.common.utils.Cryptographer;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.persistence.entity.EntityAble;

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

  @Override
  public Boolean ableByIds(List<Long> ids, EntityAble able) {
    adminAccountDao.ableByIds(ids, able.getNumber());
    return true;
  }

  @Override
  public Boolean ableByUserIds(List<Long> userIds, EntityAble able) {
    adminAccountDao.ableByUserIds(userIds, able.getNumber());
    return true;
  }

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  @Override
  public Boolean deleteById(Long id) {
    return this.adminAccountDao.deleteById(id) > 0;
  }

  @Override
  public Boolean deleteByUserId(Long userId) {
    return this.adminAccountDao.deleteByUserId(userId) > 0;
  }

  @Override
  public Boolean deleteByUserId(List<Long> userIds) {
    return this.adminAccountDao.deleteByUserIds(userIds) > 0;
  }

  /**
   * 新增数据
   *
   * @param adminAccountBO 实例对象
   * @return 实例对象
   */
  @Override
  public AdminAccountBO insert(AdminAccountBO adminAccountBO) {
    AdminAccountEntity adminAccountEntity = new AdminAccountEntity();
    BeanUtils.copyProperties(adminAccountBO, adminAccountEntity);
    adminAccountEntity.setPwdTime(new Date());
    adminAccountEntity.setType(AdminAccountType.GRANT.getNumber());
    adminAccountEntity.setCreateTime(new Date());
    adminAccountEntity.setUpdateTime(new Date());
    this.adminAccountDao.insert(adminAccountEntity);
    AdminAccountBO adminAccountBOResult = new AdminAccountBO();
    BeanUtils.copyProperties(adminAccountEntity, adminAccountBOResult);
    return adminAccountBOResult;
  }

  @Override
  public PageableQueryResult<AdminAccountBO> queryAllByCondition(AdminAccountBO adminAccountBO,
      Long pageIndex, Long pageSize) {
    PageableQueryResult<AdminAccountBO> result = new PageableQueryResult<>();
    AdminAccountEntity queryCondition = new AdminAccountEntity();
    BeanUtils.copyProperties(adminAccountBO, queryCondition);
    Long total = this.adminAccountDao.countAllByCondition(queryCondition);
    if (total == null || total < 1) {
      return result;
    }
    List<AdminAccountEntity> adminAccountEntities = this.adminAccountDao
        .queryAllByCondition(queryCondition, pageIndex * pageSize, pageSize);
    if (adminAccountEntities == null) {
      return null;
    }
    adminAccountEntities.forEach(adminAccountEntity -> {
      AdminAccountBO resultItem = new AdminAccountBO();
      BeanUtils.copyProperties(adminAccountEntity, resultItem);
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
  public AdminAccountBO queryById(Long id) {
    AdminAccountEntity adminAccountEntity = this.adminAccountDao.queryById(id);
    if (adminAccountEntity == null) {
      return null;
    }
    AdminAccountBO adminAccountBO = new AdminAccountBO();
    BeanUtils.copyProperties(adminAccountEntity, adminAccountBO);
    return adminAccountBO;
  }

  @Override
  public AdminAccountBO queryByNameAndPwd(String loginName, String loginPwd) {
    Assert.notNull(loginName, "Login name should not be empty");
    Assert.notNull(loginName, "Login password should not be empty");
    String encrypt = null;
    try {
      encrypt = Cryptographer.encrypt(loginPwd, loginName);
    } catch (Exception e) {
      e.printStackTrace();
    }
    AdminAccountEntity adminAccountEntity = adminAccountDao
        .queryByLoginNameAndPwd(loginName, encrypt);
    if (adminAccountEntity == null) {
      return null;
    }
    AdminAccountBO adminAccountBO = new AdminAccountBO();
    BeanUtils.copyProperties(adminAccountEntity, adminAccountBO);
    return adminAccountBO;
  }

  @Override
  public AdminAccountBO queryByPhoneNumberAndSmsCode(String phoneNumber, String smsCode) {
    return null;
  }

  @Override
  public List<AdminAccountBO> queryByUserId(Long userId) {
    List<AdminAccountEntity> adminAccountEntities = this.adminAccountDao.queryAllByUid(userId);
    if (adminAccountEntities == null) {
      return null;
    }
    LinkedList<AdminAccountBO> adminAccountBOS = new LinkedList<>();
    adminAccountEntities.forEach(adminAccountEntity -> {
      AdminAccountBO adminAccountBO = new AdminAccountBO();
      BeanUtils.copyProperties(adminAccountEntity, adminAccountBO);
      adminAccountBOS.add(adminAccountBO);
    });
    return adminAccountBOS;
  }

  /**
   * 修改数据
   *
   * @param adminAccountBO 实例对象
   * @return 实例对象
   */
  @Override
  public Integer update(AdminAccountBO adminAccountBO) {
    return this.adminAccountDao.update(adminAccountBO);
  }
}
