package pwd.initializr.account.api.admin;

/**
 * pwd.initializr.account.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-15 09:03
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface UserApi {

    void listUser();

    void detailedUser(Long id);

    void blockUser(Long id);

    void unblockUser(Long id);
}
