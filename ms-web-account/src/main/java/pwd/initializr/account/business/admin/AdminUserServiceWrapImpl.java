package pwd.initializr.account.business.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwd.initializr.account.business.admin.bo.AdminAccountBO;
import pwd.initializr.account.business.admin.bo.AdminContactBO;
import pwd.initializr.account.business.admin.bo.AdminUserBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;

/**
 * pwd.initializr.account.business.admin@ms-web-initializr
 *
 * <h1>用户账号服务包装类</h1>
 * <p>主要解决用户数据和账号数据修改时的事务问题</p>
 * date 2020-07-26 23:11
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service("adminUserServiceImplWrap")
public class AdminUserServiceWrapImpl implements AdminUserServiceWrap {

  @Autowired
  private AdminUserService adminUserService;

  @Autowired
  private AdminAccountService adminAccountService;

  @Autowired
  private AdminContactService adminContactService;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Integer ableByUserId(Long userId, EntityAble entityAble) {
    return 0;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Integer ableByUserId(List<Long> userIds, EntityAble entityAble) {
    Integer enableUser = this.adminUserService.ableById(userIds, entityAble);
    this.adminAccountService.ableByUserIds(userIds,entityAble);
    return enableUser;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Integer deleteByUserId(Long userId) {
    Integer integer = adminUserService.deleteById(userId);
    adminAccountService.deleteByUserId(userId);
    return integer;
  }

  @Override
  public Integer deleteByUserId(List<Long> userIds) {
    adminAccountService.deleteByUserId(userIds);
    Integer integer = adminUserService.deleteById(userIds);
    return integer;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Long insert(AdminUserBO adminUserBO, AdminAccountBO adminAccountBO, List<AdminContactBO> adminContactBOS) {
    Long userId = adminUserService.insert(adminUserBO);
    adminAccountBO.setUid(userId);
    adminAccountService.insert(adminAccountBO);
    adminContactBOS.stream().forEach(obj -> obj.setUid(userId));
    adminContactService.insertByBatch(adminContactBOS);
    return userId;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Long update(AdminUserBO adminUserBO, AdminAccountBO adminAccountBO, List<AdminContactBO> adminContactBOS) {
    adminUserService.update(adminUserBO);
    adminAccountBO.setUid(adminUserBO.getId());
    adminAccountService.updateLoginName(adminAccountBO.getId(),adminAccountBO.getUid(),adminAccountBO.getLoginName());
    adminContactBOS.stream().forEach(adminContactBO -> {
          adminContactBO.setUid(adminUserBO.getId());
          adminContactService.update(adminContactBO);
        }
    );
    return 1L;
  }


}
