package pwd.initializr.storage.business.bo;

import com.google.api.client.util.Key;
import io.minio.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.storage.business.bo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-04-05 10:18
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ObjectDelErrorBO {
  protected String code;
  protected String message;
  protected String bucketName;
  protected String objectName;
  protected String resource;
  protected String requestId;
  protected String hostId;
  protected String errorCode;
}
