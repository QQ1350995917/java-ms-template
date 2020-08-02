package pwd.initializr.storage.business;

import java.io.InputStream;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.storage.business.bo.StorageBO;

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

  PageableQueryResult<StorageBO> listFile();

  StorageBO findOneByUrl(String url);

  InputStream getObject(StorageBO storageBO) throws Exception;

}
