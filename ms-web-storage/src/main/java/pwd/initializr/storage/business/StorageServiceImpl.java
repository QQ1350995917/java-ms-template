package pwd.initializr.storage.business;

import io.minio.MinioClient;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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

  @Value("${minio.url}")
  private String minioUrl;
  @Value("${minio.access_key}")
  private String minioAccessKey;
  @Value("${minio.secret_key}")
  private String minioSecretKey;
  @Value("${minio.bucket_name}")
  private String minioBucketName;

  @Autowired
  private StorageMapper storageMapper;

  public Storage uploadImage(InputStream inputStream, String suffix) throws Exception {
    return upload(inputStream, suffix, "image/jpeg");
  }

  public Storage uploadVideo(InputStream inputStream, String suffix) throws Exception {
    return upload(inputStream, suffix, "video/mp4");
  }

  public Storage uploadFile(InputStream inputStream, String suffix) throws Exception {
    return upload(inputStream, suffix, "application/octet-stream");
  }

  public Storage uploadString(String str) throws Exception {
    if (!StringUtils.isEmpty(str)) {
      return new Storage();
    }
    InputStream inputStream = new ByteArrayInputStream(str.getBytes());
    return upload(inputStream, null, "text/html");
  }

  private Storage upload(InputStream inputStream, String suffix, String contentType)
      throws Exception {
    MinioClient minioClient = new MinioClient(minioUrl, minioAccessKey, minioSecretKey);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
    String ymd = sdf.format(new Date());
    String objectName = ymd + "/" + UUID.randomUUID().toString() + (suffix != null ? suffix : "");
    minioClient.putObject(minioBucketName, objectName, inputStream, null, null, null, contentType);
    String url = minioClient.getObjectUrl(minioBucketName, objectName);
    String path = minioBucketName + "/" + objectName;
    storageMapper.insertFile(
        new StorageEntity(null, 0L, "test", url, path,0, System.currentTimeMillis(), System.currentTimeMillis()));
    return new Storage(url, path);
  }
}
