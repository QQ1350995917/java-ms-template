package pwd.initializr.account.api.admin;

import java.util.List;
import pwd.initializr.account.api.admin.vo.AdminAccountInput;
import pwd.initializr.account.api.admin.vo.AdminUserInput;
import pwd.initializr.account.api.admin.vo.CreateAdminInput;
import pwd.initializr.common.web.api.vo.PageInput;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>控制层接口：管理员信息</h1>
 *
 * date 2019-10-26 8:13
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface AdminApi {

  void create(CreateAdminInput input);

  void delAccount(List<Long> ids);

  void delUser(List<Long> ids);

  void disableAccount(List<Long> ids);

  void disableUser(List<Long> ids);

  void enableAccount(List<Long> ids);

  void enableUser(List<Long> ids);

  void listAccount(Long userId, AdminAccountInput input);

  void listUser(PageInput pageInput, AdminUserInput input);

  void updateAccount(Long id, AdminAccountInput input);

  void updateUser(Long id, AdminUserInput input);

}
