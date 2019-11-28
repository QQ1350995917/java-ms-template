package pwd.initializr.storage.business;

import java.io.InputStream;
import pwd.initializr.storage.business.bo.Storage;

/**
 * pwd.initializr.storage.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-25 19:15
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface StorageService {

  Storage uploadFile(InputStream inputStream, String filename) throws Exception;

}
