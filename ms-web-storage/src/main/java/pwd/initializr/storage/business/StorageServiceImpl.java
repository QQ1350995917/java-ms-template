package pwd.initializr.storage.business;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pwd.initializr.common.mw.minio.MinIOClient;
import pwd.initializr.storage.business.bo.StorageBO;
import pwd.initializr.storage.persistence.dao.StorageEntity;

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
@Service
public class StorageServiceImpl implements StorageService {

  @Autowired
  private MinIOClient minIOClient;
  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public StorageBO uploadFile(String bucketName, String objectName, InputStream inputStream)
      throws Exception {
    return upload(bucketName, objectName, inputStream, "application/octet-stream");
  }

  @Override
  public StorageBO uploadFile(String bucketName, String objectName, InputStream inputStream,
      String contentType) throws Exception {
    return upload(bucketName, objectName, inputStream, contentType);
  }

  @Override
  public StorageBO uploadHtml(String bucketName, String objectName, String html) throws Exception {
    if (StringUtils.isEmpty(html)) {
      return null;
    }
    InputStream inputStream = new ByteArrayInputStream(html.getBytes());
    return upload(bucketName, objectName, inputStream, "text/html");
  }

  @Override
  public StorageBO uploadImage(String bucketName, String objectName, InputStream inputStream)
      throws Exception {
    return upload(bucketName, objectName, inputStream, "image/jpeg");
  }

  @Override
  public StorageBO uploadVideo(String bucketName, String objectName, InputStream inputStream)
      throws Exception {
    return upload(bucketName, objectName, inputStream, "video/mp4");
  }

  private StorageBO upload(String bucketName, String objectName, InputStream inputStream,
      String contentType) throws Exception {
    minIOClient.putObject(bucketName, objectName, inputStream, null, null, null, contentType);
    String url = minIOClient.getObjectUrl(bucketName, objectName);
    StorageEntity storageEntity = new StorageEntity(null, 0L, objectName, bucketName,
        objectName, url, url, 0, System.currentTimeMillis(), System.currentTimeMillis());
    mongoTemplate.save(storageEntity);
    StorageBO storageBO = new StorageBO();
    BeanUtils.copyProperties(storageEntity, storageBO);
    return storageBO;
  }

}
