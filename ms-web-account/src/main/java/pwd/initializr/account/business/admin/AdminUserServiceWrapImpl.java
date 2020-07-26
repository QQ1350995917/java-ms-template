package pwd.initializr.account.business.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwd.initializr.account.business.admin.bo.AdminAccountBO;
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

  @Override
  @Transactional(rollbackFor = Exception.class)
  public boolean ableByUserId(Long userId, EntityAble entityAble) {
    return false;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public boolean ableByUserId(List<Long> userIds, EntityAble entityAble) {
    return false;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public boolean deleteByUserId(Long userId) {
    return adminUserService.deleteById(userId) && adminAccountService.deleteByUserId(userId);
  }

  @Override
  public boolean deleteByUserId(List<Long> userIds) {
    return adminUserService.deleteById(userIds) && adminAccountService.deleteByUserId(userIds);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public AdminUserBO insert(AdminUserBO adminUserBO, AdminAccountBO adminAccountBO) {
    AdminUserBO insertAdminUserBoResult = adminUserService.insert(adminUserBO);
    adminAccountBO.setUid(insertAdminUserBoResult.getId());
    adminAccountService.insert(adminAccountBO);
    return insertAdminUserBoResult;
  }
}
