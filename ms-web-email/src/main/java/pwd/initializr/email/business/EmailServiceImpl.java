package pwd.initializr.email.business;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pwd.initializr.email.business.bo.EmailBO;
import pwd.initializr.email.persistence.dao.EmailDao;
import pwd.initializr.email.persistence.entity.EmailEntity;
import pwd.initializr.common.utils.Cryptographer;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.common.web.business.bo.ScopeBO;
import pwd.initializr.common.web.business.bo.SortBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;
import pwd.initializr.common.web.persistence.entity.EntityDel;

/**
 * (EmailEntity)表服务实现类
 *
 * @author makejava
 * @since 2020-07-18 22:19:55
 */
@Service("adminAccountService")
public class EmailServiceImpl implements EmailService {

  @Resource
  private EmailDao emailDao;

  @Override
  public Integer ableById(Long id, Long uid, EntityAble able) {
    return this.emailDao.ableById(id,uid,able.getNumber());
  }

  @Override
  public Integer ableByIds(List<Long> ids, EntityAble able) {
    return emailDao.ableByIds(ids, able.getNumber());
  }

  @Override
  public Integer ableByUserIds(List<Long> userIds, EntityAble able) {
    return emailDao.ableByUserIds(userIds, able.getNumber());
  }

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  @Override
  public Integer deleteById(Long id,Long uid) {
    return this.emailDao.deleteById(id,uid);
  }

  @Override
  public Integer deleteByUserId(Long userId) {
    return this.emailDao.deleteByUserId(userId);
  }

  @Override
  public Integer deleteByUserId(List<Long> userIds) {
    return this.emailDao.deleteByUserIds(userIds);
  }

  @Override
  public Integer enabledAccountNum(Long userId) {
    return this.emailDao.enabledAccountNum(userId);
  }

  @Override
  public Integer existedAccountNum(Long userId) {
    return this.emailDao.existedAccountNum(userId);
  }

  /**
   * 新增数据
   *
   * @param adminAccountBO 实例对象
   * @return 实例对象
   */
  @Override
  public EmailBO insert(EmailBO adminAccountBO) {
    EmailEntity emailEntity = new EmailEntity();
    BeanUtils.copyProperties(adminAccountBO, emailEntity);
    emailEntity.setAble(EntityAble.DISABLE.getNumber());
    emailEntity.setDel(EntityDel.NO.getNumber());
    emailEntity.setCreateTime(new Date());
    emailEntity.setUpdateTime(new Date());
    this.emailDao.insert(emailEntity);
    EmailBO adminAccountBOResult = new EmailBO();
    BeanUtils.copyProperties(emailEntity, adminAccountBOResult);
    return adminAccountBOResult;
  }

  @Override
  public PageableQueryResult<EmailBO> queryAllByCondition(LinkedHashSet<ScopeBO> scopes,
      LinkedHashSet<SortBO> sorts, Long pageIndex, Long pageSize) {
    PageableQueryResult<EmailBO> result = new PageableQueryResult<>();
    Long total = this.emailDao.countAllByCondition(scopes);
    if (total == null || total < 1) {
      return result;
    }
    List<EmailEntity> adminAccountEntities = this.emailDao
        .queryAllByCondition(scopes,sorts, pageIndex * pageSize, pageSize);
    if (adminAccountEntities == null) {
      return null;
    }
    adminAccountEntities.forEach(emailEntity -> {
      EmailBO resultItem = new EmailBO();
      BeanUtils.copyProperties(emailEntity, resultItem);
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
  public EmailBO queryById(Long id,Long uid) {
    EmailEntity emailEntity = this.emailDao.queryById(id,uid);
    if (emailEntity == null) {
      return null;
    }
    EmailBO adminAccountBO = new EmailBO();
    BeanUtils.copyProperties(emailEntity, adminAccountBO);
    return adminAccountBO;
  }

  @Override
  public EmailBO queryByNameAndPwd(String loginName, String loginPwd) {
    Assert.notNull(loginName, "Login name should not be empty");
    Assert.notNull(loginName, "Login pwd should not be empty");
    String encrypt = null;
    try {
      encrypt = Cryptographer.encrypt(loginPwd, loginName);
    } catch (Exception e) {
      e.printStackTrace();
    }
    EmailEntity emailEntity = emailDao
        .queryByLoginNameAndPwd(loginName, encrypt);
    if (emailEntity == null) {
      return null;
    }
    EmailBO adminAccountBO = new EmailBO();
    BeanUtils.copyProperties(emailEntity, adminAccountBO);
    return adminAccountBO;
  }

  @Override
  public EmailBO queryByPhoneNumberAndSmsCode(String phoneNumber, String smsCode) {
    // TODO
    return null;
  }

  @Override
  public List<EmailBO> queryByUserId(Long userId) {
    List<EmailEntity> adminAccountEntities = this.emailDao.queryAllByUid(userId);
    if (adminAccountEntities == null) {
      return null;
    }
    LinkedList<EmailBO> adminAccountBOS = new LinkedList<>();
    adminAccountEntities.forEach(emailEntity -> {
      EmailBO adminAccountBO = new EmailBO();
      BeanUtils.copyProperties(emailEntity, adminAccountBO);
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
    return this.emailDao.updateLoginPwd(id,uid,previousPwd,currentPwd);
  }
}
