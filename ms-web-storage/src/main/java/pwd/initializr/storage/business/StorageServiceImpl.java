package pwd.initializr.storage.business;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pwd.initializr.common.mw.minio.MinIOClient;
import pwd.initializr.storage.business.bo.Storage;
import pwd.initializr.storage.persistence.dao.StorageEntity;
import pwd.initializr.storage.persistence.mapper.StorageMapper;

/**
 * pwd.initializr.storage.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-25 19:15
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class StorageServiceImpl implements StorageService {

  @Value("${spring.minio.bucket_name}")
  private String minioBucketName;

  @Autowired
  private StorageMapper storageMapper;
  @Autowired
  private MongoTemplate mongoTemplate;
  @Autowired
  private MinIOClient minIOClient;

  public Storage uploadImage(InputStream inputStream, String filename) throws Exception {
    return upload(inputStream, filename, "image/jpeg");
  }

  public Storage uploadVideo(InputStream inputStream, String filename) throws Exception {
    return upload(inputStream, filename, "video/mp4");
  }

  @Override
  public Storage uploadFile(InputStream inputStream, String filename) throws Exception {
    return upload(inputStream, filename, "application/octet-stream");
  }

  public Storage uploadString(String str) throws Exception {
    if (!StringUtils.isEmpty(str)) {
      return new Storage();
    }
    InputStream inputStream = new ByteArrayInputStream(str.getBytes());
    return upload(inputStream, null, "text/html");
  }


  private Storage upload(InputStream inputStream, String filename, String contentType)
      throws Exception {
    String suffix = filename.substring(filename.lastIndexOf("."));
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String format = simpleDateFormat.format(new Date());
    String objectName = format + "/" + UUID.randomUUID().toString() + suffix;
    minIOClient.putObject(minioBucketName, objectName, inputStream, null, null, null, contentType);
    String url = minIOClient.getObjectUrl(minioBucketName, objectName);
    String path = minioBucketName + "/" + objectName;
    StorageEntity storageEntity = new StorageEntity(null, 0L, filename, url, path, 0,
        System.currentTimeMillis(), System.currentTimeMillis());
//    storageMapper.insertFile(storageEntity);
    mongoTemplate.save(storageEntity);
    return new Storage(url, filename);
  }
}
