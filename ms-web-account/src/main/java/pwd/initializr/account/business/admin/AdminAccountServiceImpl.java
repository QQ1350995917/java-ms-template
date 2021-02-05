package pwd.initializr.account.business.admin;

import java.util.Date;
import java.util.LinkedHashSet;
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
import pwd.initializr.common.utils.CryptographerAes;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.common.web.persistence.entity.EntityDel;

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
  public Integer ableById(Long id, Long uid, EntityAble able) {
    return this.adminAccountDao.ableById(id,uid,able.getNumber(), new Date());
  }

  @Override
  public Integer ableByIds(List<Long> ids, EntityAble able) {
    return adminAccountDao.ableByIds(ids, able.getNumber(), new Date());
  }

  @Override
  public Integer ableByUserIds(List<Long> userIds, EntityAble able) {
    return adminAccountDao.ableByUserIds(userIds, able.getNumber(), new Date());
  }

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  @Override
  public Integer deleteById(Long id,Long uid) {
    return this.adminAccountDao.deleteById(id,uid, new Date());
  }

  @Override
  public Integer deleteByUserId(Long userId) {
    return this.adminAccountDao.deleteByUserId(userId, new Date());
  }

  @Override
  public Integer deleteByUserId(List<Long> userIds) {
    return this.adminAccountDao.deleteByUserIds(userIds, new Date());
  }

  @Override
  public Integer enabledAccountNum(Long userId) {
    return this.adminAccountDao.enabledAccountNum(userId);
  }

  @Override
  public Integer existedAccountNum(Long userId) {
    return this.adminAccountDao.existedAccountNum(userId);
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
    adminAccountEntity.setAble(EntityAble.DISABLE.getNumber());
    adminAccountEntity.setDel(EntityDel.NO.getNumber());
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
  public PageableQueryResult<AdminAccountBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
      LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize) {
    PageableQueryResult<AdminAccountBO> result = new PageableQueryResult<>();
    Long total = this.adminAccountDao.countAllByCondition(scopes);
    if (total == null || total < 1) {
      return result;
    }
    List<AdminAccountEntity> adminAccountEntities = this.adminAccountDao
        .queryAllByCondition(scopes,sorts, pageIndex * pageSize, pageSize);
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
  public AdminAccountBO queryById(Long id,Long uid) {
    AdminAccountEntity adminAccountEntity = this.adminAccountDao.queryById(id,uid);
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
    Assert.notNull(loginName, "Login pwd should not be empty");
    String encrypt = null;
    try {
      encrypt = CryptographerAes.encrypt(loginPwd, loginName);
    } catch (Exception e) {
      e.printStackTrace();
    }
    AdminAccountEntity adminAccountEntity = adminAccountDao
        .queryByLoginNameAndPwd(loginName);
    // FIXME: 密码问题
    if (adminAccountEntity == null) {
      return null;
    }
    AdminAccountBO adminAccountBO = new AdminAccountBO();
    BeanUtils.copyProperties(adminAccountEntity, adminAccountBO);
    return adminAccountBO;
  }

  @Override
  public AdminAccountBO queryByPhoneNumberAndSmsCode(String phoneNumber, String smsCode) {
    // TODO
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
   * @param id 账户ID
   * @return 实例对象
   */
  @Override
  public Integer updateLoginPwd(Long id, Long uid, String previousPwd, String currentPwd) {
    return this.adminAccountDao.updateLoginPwd(id,uid,previousPwd,currentPwd, new Date());
  }
}
