package pwd.initializr.account.business.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwd.initializr.account.business.user.bo.UserAccountBO;
import pwd.initializr.account.business.user.bo.UserUserBO;
import pwd.initializr.common.web.persistence.entity.EntityAble;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-07-28 15:51
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service("userServiceWrap")
public class UserUserServiceWrapImpl implements UserUserServiceWrap {

  @Autowired
  private UserAccountService userAccountService;

  @Autowired
  private UserUserService userUserService;

  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public Integer ableByUserId(Long userId, EntityAble entityAble) {
    Integer integer = userAccountService.ableByUserId(userId, entityAble);
    userUserService.ableById(userId, entityAble);
    return integer;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Boolean ableByUserId(List<Long> userIds, EntityAble entityAble) {
    userAccountService.ableByUserId(userIds, entityAble);
    userUserService.ableById(userIds, entityAble);
    return true;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Boolean deleteByUserId(Long userId) {
    userAccountService.deleteById(Arrays.asList(userId));
    userUserService.deleteById(userId);
    return true;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Boolean deleteByUserId(List<Long> userIds) {
    userAccountService.deleteById(userIds);
    userUserService.deleteById(userIds);
    return true;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public UserAccountBO insert(UserUserBO userUserBO, UserAccountBO userAccountBO) {
    UserUserBO userUserBOResult = userUserService.insert(userUserBO);
    userAccountBO.setUid(userUserBOResult.getId());
    UserAccountBO insert = userAccountService.insert(userAccountBO);
    return insert;
  }
}
