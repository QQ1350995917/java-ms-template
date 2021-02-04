package pwd.initializr.account.business.user;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-04 16:56
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface UserKeyService {

    void refreshKeyPairs();

    String getPublicKey();

    String getPrivateKey();
}
