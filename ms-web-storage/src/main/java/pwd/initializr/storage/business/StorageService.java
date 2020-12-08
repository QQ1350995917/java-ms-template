package pwd.initializr.storage.business;

import java.io.InputStream;
import java.util.List;
import pwd.initializr.common.web.business.bo.PageableQueryResult;
import pwd.initializr.storage.business.bo.ObjectDelErrorBO;
import pwd.initializr.storage.business.bo.StorageBO;

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

  void delete(String bucketName, String objectName) throws Exception;

  List<ObjectDelErrorBO> delete(String bucketName, List<String> objectNames) throws Exception;

  StorageBO uploadFile(StorageBO bo, InputStream inputStream) throws Exception;

  StorageBO uploadText(StorageBO bo, String text) throws Exception;

  PageableQueryResult<StorageBO> listFile(Integer pageIndex,Integer pageSize);

  StorageBO findOneById(String id);

  StorageBO findOneByUrl(String url);

  InputStream getObject(StorageBO storageBO) throws Exception;
}
