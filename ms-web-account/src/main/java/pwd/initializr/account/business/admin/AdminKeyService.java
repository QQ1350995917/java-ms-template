package pwd.initializr.account.business.admin;

/**
 * pwd.initializr.account.business.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-04 16:54
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface AdminKeyService {

    void refreshKeyPairs();

    String getPublicKey();

    String getPrivateKey();
}
