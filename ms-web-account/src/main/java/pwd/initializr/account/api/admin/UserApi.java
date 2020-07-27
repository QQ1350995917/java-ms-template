package pwd.initializr.account.api.admin;

import java.util.List;
import pwd.initializr.account.api.admin.vo.UserAccountInput;
import pwd.initializr.account.api.admin.vo.UserUserInput;
import pwd.initializr.common.web.api.vo.PageInput;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>控制层接口：用户信息</h1>
 *
 * date 2019-09-15 09:03
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface UserApi {

    void disableUser(List<Long> ids);

    void enableUser(List<Long> ids);

    void disableAccount(List<Long> ids);

    void enableAccount(List<Long> ids);

    void delUser(List<Long> ids);

    void delAccount(List<Long> ids);

    void listUser(PageInput pageInput, UserUserInput input);

    void listAccount(Long userId, UserAccountInput input);

    void updateUser(Long id, UserUserInput input);

    void updateAccount(Long id, UserAccountInput input);

}
