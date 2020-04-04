package pwd.initializr.common.mw.minio;

import io.minio.MinioClient;
import io.minio.Result;
import io.minio.ServerSideEncryption;
import io.minio.errors.MinioException;
import io.minio.messages.DeleteError;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * pwd.initializr.common.mw.minio@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-25 20:27
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Component
//@PropertySource("classpath:redis.properties")
@Slf4j
@ConfigurationProperties("spring.minio")
@Setter
@Getter
@ToString
public class MinIOClient {

  private static MinioClient minioClient = null;
  private String accessKey;
  private String secretKey;
  private String url;

  public InputStream getObject(String bucketName, String objectName) throws Exception {
    return getMinioClientInstance().getObject(bucketName, objectName);
  }

  public String getObjectUrl(String bucketName, String objectName)
      throws Exception {
    return getMinioClientInstance().getObjectUrl(bucketName, objectName);
  }

  public void putObject(String bucketName, String objectName, InputStream stream, Long size,
      Map<String, String> headerMap, ServerSideEncryption sse, String contentType)
      throws Exception {
    getMinioClientInstance()
        .putObject(bucketName, objectName, stream, size, headerMap, sse, contentType);
  }

  private MinioClient getMinioClientInstance() throws MinioException {
    if (minioClient == null) {
      synchronized (this) {
        if (minioClient == null) {
          minioClient = new MinioClient(url, accessKey, secretKey);
        }
      }
    }
    return minioClient;
  }

  public void removeObject(String bucketName, String objectName) throws Exception {
    getMinioClientInstance().removeObject(bucketName, objectName);
  }

  public Iterable<Result<DeleteError>> removeObjects(String bucketName, List<String> objectNames)
      throws Exception {
    return getMinioClientInstance().removeObjects(bucketName, objectNames);
  }


}
