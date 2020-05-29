package pwd.initializr.account.business.user;

import pwd.initializr.account.business.user.bo.UserBO;
import pwd.initializr.account.business.user.bo.UserAccountBO;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-21 15:33
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface UserAccountService {

  UserBO createAccountForUser(UserBO userBO);

  UserBO createUserAndAccount(UserBO userBO);

  UserAccountBO findAccountByLoginNameAndPassword(String loginName, String password);

  UserBO findUserByUserId(Long id);

  ObjectList<UserBO> listAccount(UserAccountBO userAccountBO, Integer offset, Integer size);

  ObjectList<UserBO> listAccountByUser(UserBO userBO, Integer offset, Integer size);

  ObjectList<UserBO> listUser(UserBO userBO, Integer offset, Integer size);
}
