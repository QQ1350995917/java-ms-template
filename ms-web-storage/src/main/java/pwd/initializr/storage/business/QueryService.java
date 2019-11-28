package pwd.initializr.storage.business;

import java.io.InputStream;
import pwd.initializr.common.web.business.bo.ObjectList;
import pwd.initializr.storage.business.bo.Storage;
import pwd.initializr.storage.persistence.dao.StorageEntity;

/**
 * pwd.initializr.storage.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-26 18:23
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface QueryService {

  ObjectList<Storage> listFile();

  Storage findOneByUrl(String url);

  InputStream getObject(Storage storage) throws Exception;

}
