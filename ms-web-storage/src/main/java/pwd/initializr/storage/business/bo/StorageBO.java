package pwd.initializr.storage.business.bo;

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
 * date 2019-09-25 19:45
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@NoArgsConstructor
@Setter
@Getter
@ToString
public class StorageBO {

  private String id;
  private Long userId;
  private String filename;
  private String bucketName;
  private String objectName;
  private String url;
  private String path;
  private Integer status;
  private Long createTime;
  private Long updateTime;

  public StorageBO(String id, Long userId, String filename, String bucketName,
      String objectName, String url, String path, Integer status, Long createTime,
      Long updateTime) {
    this.id = id;
    this.userId = userId;
    this.filename = filename;
    this.bucketName = bucketName;
    this.objectName = objectName;
    this.url = url;
    this.path = path;
    this.status = status;
    this.createTime = createTime;
    this.updateTime = updateTime;
  }
}
