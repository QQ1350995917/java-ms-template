package pwd.initializr.storage.business;

import java.io.InputStream;
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

  StorageBO uploadFile(String bucketName, String objectName, InputStream inputStream)
      throws Exception;

  StorageBO uploadFile(String bucketName, String objectName, InputStream inputStream,String contentType)
      throws Exception;

  StorageBO uploadHtml(String bucketName, String objectName, String html) throws Exception;

  StorageBO uploadImage(String bucketName, String objectName, InputStream inputStream)
      throws Exception;

  StorageBO uploadVideo(String bucketName, String objectName, InputStream inputStream)
      throws Exception;
}
