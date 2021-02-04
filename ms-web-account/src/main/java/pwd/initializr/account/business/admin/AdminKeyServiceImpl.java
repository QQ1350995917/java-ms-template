package pwd.initializr.account.business.admin;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import pwd.initializr.common.web.util.ResourceUtil;

/**
 * pwd.initializr.account.business.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-04 16:57
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service("adminKeyService")
public class AdminKeyServiceImpl implements AdminKeyService {

    @Value("${account.admin.key.public.name}")
    private String keyPublicName;
    @Value("${account.admin.key.private.name}")
    private String keyPrivateName;

    @Resource
    private ResourceLoader resourceLoader;

    private String keyPublic;
    private String keyPrivate;

    @Override
    public void refreshKeyPairs() {
        init();
    }

    @Override
    public String getPublicKey() {
        return keyPublic;
    }

    @Override
    public String getPrivateKey() {
        return keyPrivate;
    }

    @PostConstruct
    private void init() {
        this.keyPublic = ResourceUtil.readResourceFileToString(resourceLoader, keyPublicName);
        this.keyPrivate = ResourceUtil.readResourceFileToString(resourceLoader, keyPrivateName);
    }
}
